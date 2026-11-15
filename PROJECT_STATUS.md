# Lots of Food Rebirthed — 项目现状与 1.21 移植评估

> 生成时间：2026-11-15
> 本文档基于对工作区、git 历史、`build/` 产物和 `run/logs` 的只读分析，尚未修改任何已有文件。
> Java 环境：`D:\.Environments\Java`（本机已有 21 / 24 / 25，移植固定使用 **Java 21**）。

---

## 0. 移植进展（2026-11-15）

- 分支 `1.21.1-neoforge` 已从 `origin/1.19.2-new` 创建。
- 构建环境：ModDevGradle `2.0.107` + NeoForge `21.1.266` + Java 21；`maven.neoforged.net` 已重定向到 `neoforged.forgecdn.net` 镜像。
  - 升级到 21.1.266 是因为 JEI 19.70 要求 NeoForge ≥ 21.1.238。
- `./gradlew compileJava` 通过。
- 客户端可以启动到主菜单/进入世界；贴图目录迁移到 `textures/item` / `textures/block` 后不再出现 Missing textures。
- JEI `19.70.0.469` 已接入并成功加载（1856 个 ingredients），可在游戏内查看配方。
- 专用服务器可以启动：`Done`，加载 1472 个配方，无注册表/战利品表解析错误。
- 已修复：
  - `RegistryObject`/`DeferredRegister`/`BuiltInRegistries` 等 1.21 注册 API。
  - 创造模式标签页改为注册表 + `displayItems`。
  - 1.21 数据驱动世界生成：棕榈树改为 `worldgen/configured_feature` + `worldgen/placed_feature` JSON，自定义 `FoliagePlacerType`。
  - 数据包目录单数化、配方 `result.id`、`forge:` 标签迁移到 `c:`、语言 JSON、贴图目录。
  - 之前“删除世界闪退”的直接原因 `palm_tree_placed` Holder 未绑定已消除。
  - 厨师帽：物品图标改为独立的 `item/cheff_hat` 平面贴图；头部用 `ChefHatArmorModel`（移植自 1.12 `CheffHat$CheffHatModel` 的两个 box）+ `cheff_hat_layer_1.png` 渲染，已通过游戏内截图验证。
  - 玉米：调整骨粉顺序为“下层长到 3 → 长出上层 → 优先把下层催到 4 → 再催上层”；骨粉不再触发右键收割。
- 尚未完成/待游戏内验收：
  - 冰激凌机仍禁用（配方移到 `recipe_disabled`）。
  - 盘子、野餐篮、作物右键收割、钓鱼掉落、棕榈树生成需要在游戏内逐项点验。
  - 玉米的生长顺序已按上述逻辑修改，但仍建议在游戏里用骨粉完整点验一次。
- 注意：`run/server.properties` 的测试端口临时改为 `25566`（`run/` 不入库）；需要时改回 `25565`。

---

## 1. 一句话结论

当前工作区 `master` 是 **1.19.2 Forge 43.4.0 的旧快照**；真正最新的 1.19.2 代码在远程分支 `origin/1.19.2-new`；远程 `main` 上已经有一套**能跑起来的 NeoForge 1.21.1（21.1.209）+ ModDevGradle 2.0.107** 模板环境。

建议的移植路线是：**以 `origin/1.19.2-new` 为代码基线，以 `main` 的 1.21.1 构建环境为底座，新建 `1.21.1-neoforge` 分支**，先让已有内容编译、注册、进游戏，再逐项修运行时问题。

这不是“改几行 Gradle 就能跑”的升级：代码里约有 **291 处 `RegistryObject`、40 个文件 import `net.minecraftforge`**；资源里有 **183 个配方、21 个 loot table、28 个 blockstate、177 个 item model**。属于一次中等规模移植。

---

## 2. 仓库与分支现状

| 分支 | 最新提交 | 内容 | 备注 |
|---|---|---|---|
| `master`（当前工作区） | `8d4d06a` | 1.19.2 旧代码快照 | 本地 HEAD；比最新 1.19.2 代码旧 |
| `origin/master` | `8d4d06a` | 同上 | 与本地一致 |
| `origin/1.19.2` | `3bcb10e` | “重启项目前的备份” | 旧的 1.19.2 备份 |
| `origin/1.19.2-new` | `cc357bf` | **最新、最完整的 1.19.2 代码** | 比 `master` 多：盘子方块、右键收割、3×3 野餐篮、蛋糕/木板/羊毛/野餐篮标签、玉米生长优化 |
| `main` / `origin/main` | `d1e46e7` | 1.21.1 NeoForge 模板 | 所有旧模组代码被模板代码替换；构建环境可启动 |

### 当前工作区未提交改动

- `build.gradle`：加了一段 `repositories { libraries.minecraft.net / neoforged.forgecdn.net }`（为 1.21 环境准备的镜像，但当前 `build.gradle` 仍是 ForgeGradle 1.19.2，二者并不配套）。
- `gradle/wrapper/gradle-wrapper.properties`：Gradle 8.14.3，下载地址改成了阿里云镜像。
- `.idea/shelf/...`：IDE 在切换分支前保存的同一批构建文件改动。

这些改动建议保留（国内网络需要镜像），但不要直接混进 `master` 的历史；移植时在新分支上整理。

### `origin/1.19.2-new` 相对 `master` 的新增内容（值得作为基线）

- `blocks/DishBlock.java` + `blocks/entity/DishBlockEntity.java` + `client/renderer/DishBlockEntityRenderer.java` + `BlockEntityRegister`。
- `WorldEventHandler` 增加右键收割（可配置范围/产物进背包）。
- `PicnicBasketMenu` 改为 3×3、按标签过滤可放入物品；`PicnicBasketScreen` 改成发射器式界面。
- 新增 `data/forge/tags/items/cakes.json`、`picnic_basket_allowed.json`、`minecraft/tags/items/planks.json`、`wool.json`。
- 玉米 `CornBush` 生长/骨粉逻辑重写。
- 包名从 `com.KaminnoYoruneko` 改为 `com.KaminoYoruneko`（少一个 n）。移植时需要统一。

---

## 3. 构建配置现状

### 3.1 `master`（当前 `build.gradle`）

- ForgeGradle `net.minecraftforge.gradle [6.0,6.2)`，Minecraft `1.19.2`，Forge `43.4.0`，Java 17。
- `jcenter()`（已停服）、`mixingradle 0.7-SNAPSHOT`、`curse.maven` 的 AppleSkin / JEI 1.19.2 依赖。
- `repositories` 块重复出现两次；`processResources` 只处理 `META-INF/mods.toml` 和 `pack.mcmeta`。
- Gradle wrapper 被改成 8.14.3；ForgeGradle 6 的兼容区间偏旧，这个组合本身就有风险。
- `git status` 显示 `build.gradle`、`gradle-wrapper.properties` 处于未提交状态。

### 3.2 `main`（1.21.1 模板，已验证可启动）

- ModDevGradle `net.neoforged.moddev` `2.0.107`。
- NeoForge `21.1.209`，Minecraft `1.21.1`，Parchment `2024.11.17`，Java 21。
- Gradle 8.14.3；`org.gradle.configuration-cache=true`。
- `src/main/templates/META-INF/neoforge.mods.toml` + `generateModMetadata` 任务。
- `build/moddev/artifacts/` 中已有 `neoforge-21.1.209-sources.jar`（本次分析大量 API 就是从这里核对的）。

### 3.3 `run/logs/latest.log` 说明

2026-11-15 13:14–13:17 有一次 NeoForge 21.1.209 / MC 1.21.1 的启动记录：

- 模组列表：`Lots of Food Rebirthed 1.0.0 (lots_of_food_reborn)`、Minecraft 1.21.1、NeoForge 21.1.209。
- 进入主菜单、创建世界、进入世界、退出，整体成功。
- 非致命错误：`models/armor/helmet_cheff_hat.json` 的 `from` 坐标 `(-5,-18,-5)` 超出 1.21 方块模型允许范围（最低 -16）。
- 这次运行只能说明 **1.21.1 环境本身可用**；当时 `build/classes` 里只有模板的 3 个类，并不代表旧模组代码已经移植成功。

---

## 4. 代码结构

```
src/main/java/com/KaminnoYoruneko/lots_of_food_reborn/   (master)
src/main/java/com/KaminoYoruneko/lots_of_food_reborn/    (origin/1.19.2-new)
├── LOFR.java                 模组入口、注册、客户端初始化
├── Config.java               ForgeConfigSpec（6 个配置项）
├── register/                 物品/方块/作物/实体/菜单/特征/战利品注册
├── custom/Custom.java        大量物品/方块注册与行为工厂（约 800 行）
├── blocks/                   方块、作物、自定义方块
├── items/                    食物、饮料、方块物品、野餐篮、厨师帽
├── entities/                 投掷椰子
├── menu/                     野餐篮菜单/界面
├── trees/                    棕榈树 Feature/Trunk/Foliage/Grower
├── world/gen/                旧版结构/棕榈树生成（基本未使用）
├── mixin/                    TreeFeature 覆写、LootPool 注入、HumanoidArmorLayer（空实现）
├── renderer/                 厨师帽渲染（旧 API，基本未使用）
├── handler/WorldEventHandler 战利品表、生物掉落、树叶掉落、右键收割
└── proxy/, tab/, util/, notImportant/
```

### 代码量概览

| 项目 | 数量 |
|---|---|
| `RegistryObject` 出现次数 | 约 291（含注释） |
| import `net.minecraftforge` 的文件 | 40 |
| `.tab(...)` 调用 | 33 |
| `saturationMod` 调用 | 16 |
| 配方 JSON | 183 |
| Loot table JSON | 21 |
| Blockstate | 28 |
| Block model | 108 |
| Item model | 177 |
| 物品纹理 / 方块纹理 | 154 / 85 |

---

## 5. 已实现功能（按玩家可见行为）

### 5.1 物品与食物

- 大量食物：肉类、鱼类、水果、甜点、正餐、汤/意面、糖果（16 色）、幸运饼干等。
- 饮品：8 种果汁、4 种酒、3 种茶；玻璃杯/马克杯饮用后返还容器；酒有生命恢复+反胃效果；茶有力量/水下呼吸/抗火效果。
- 特殊行为：
  - 辣椒食用后着火 3 秒。
  - 至尊烤金鱼回满血、EPIC 稀有度、附魔光效。
  - 幸运饼干随机发送 19 条消息之一。
  - 糖果随机给 6 种效果之一。
  - 饥饿蛋糕恢复负饥饿值。
  - 汤/意面返还碗，冰棍返还木棍。
  - 饮品/冰激凌返还玻璃杯，茶/热饮返还马克杯。

### 5.2 方块

- 焦糖块、巧克力块、糖砖块及各自楼梯/栅栏（栅栏之间可互相连接）。
- 6 种蛋糕（巧克力、苹果、芝士、胡萝卜、草莓、黑森林），切片逻辑基于 `CakeBlock`。
- 椰子方块（继承 `LeavesBlock`，可被投掷椰子命中/自然生成）。
- 棕榈树苗（只能种在沙子上）。
- 8 种作物：辣椒、番茄、咖啡、葡萄、草莓、香草、海藻（水下）、玉米（双层）。
- 盘子方块（仅 `origin/1.19.2-new`）：可放/取/潜行右键进食，带 BlockEntity 渲染。

### 5.3 世界生成

- 棕榈树：
  - 代码注册 `palm_tree` ConfiguredFeature、`palm_tree_checked`、`palm_tree_spawn`、`palm_tree_placed`。
  - 通过 `data/lots_of_food_reborn/forge/biome_modifier/add_palm.json` 加入沙滩。
  - 自定义 `PalmTreeFoliagePlacer` 硬编码生成树干/树叶/椰子；靠 `@Overwrite TreeFeature.doPlace` 的 Mixin 触发。
- 糖果屋：
  - `sweet_house` / `sweet_house_sand` 两个 jigsaw 结构、template pool、NBT、structure set、biome tag。
  - 生成时结构 JSON 中带 witch 生成覆盖。
  - 旧版 `WordGenSweetHouse` 代码已整段注释，实际依赖数据包。

### 5.4 战利品与掉落

- 21 个方块 loot table（作物成熟掉落、方块掉落等）。
- 钓鱼：`WorldEventHandler.onLootTableLoad` 在 `minecraft:gameplay/fishing/fish` 主池里追加 5 种鱼（通过反射调用 `LootPoolMixin` 增加的 `addEntry`）。
- 生物死亡：马掉生/熟马肉，鱿鱼掉生/熟鱿鱼；考虑抢夺附魔。
- 树叶：白桦掉樱桃，丛林掉香蕉；考虑时运。
- 作物右键收割（仅 `origin/1.19.2-new`，可配置）。

### 5.5 交互/界面/实体

- 野餐篮：3×3 容器（最新分支），按可食用/蛋糕标签/特殊标签过滤，Shift 快速转移，界面使用发射器贴图。
- 投掷椰子：`CoconutEntity`（`ThrowableProjectile` + `ItemSupplier`），命中方块/生物后销毁并掉落 2 个打开椰子；有对应 `ThrownItemRenderer`。
- 厨师帽：可装备到头部（`Item.canEquip` 覆写），但头部渲染 Mixin 实际是空实现；模型 JSON 在 1.21 下越界。

### 5.6 配置

`Config.java` 现有 7 个配置项：

- `logDirtBlock`、`magicNumber`、`magicNumberIntroduction`、`items`（模板遗留）。
- `allowRightClickHarvest`、`harvestDropsToInventory`、`harvestScope`（最新分支新增）。

---

## 6. 未实现 / 半实现 / 已知问题

### 6.1 明确未实现

| 内容 | 现状 |
|---|---|
| **冰激凌机** | 有 blockstate、block model、item model、配方 JSON，但**没有方块/BlockEntity/容器/GUI/加工逻辑**；配方的产物 `lots_of_food_reborn:ice_cream_machine` 未注册，配方加载会报错。 |
| 野生作物生成 | 1.12.2 前身有 `WorldGenAlgues`、`WorldGenCoffee`、`WorldGenCorn`、`WorldGenFraisier` 等；当前只有棕榈树和糖果屋。 |
| 厨师帽头部渲染 | `MixinHumanoidArmorLayer` 是空实现；`CheffHatItemRenderer` 使用 1.19 的 `ItemTransforms.TransformType`，且模型坐标在 1.21 越界。 |
| PDA / Thirst 兼容 | 1.12.2 前身里有 `PDA`、`PDA$ThirstModStats` 等；当前未移植（大概率可不管）。 |
| `dog_cookie` / `cheese_bucket` | 配方和模型/语言存在，但 Java 里没有注册对应物品；属于小缺口。 |

### 6.2 只存在于 `origin/1.19.2-new`、`master` 没有的

- 盘子方块及 BlockEntity/渲染器。
- 右键收割功能与相关配置。
- 3×3 野餐篮界面与标签过滤。
- 蛋糕/木板/羊毛/野餐篮标签。
- 玉米生长优化。

### 6.3 已知技术债 / 风险点

- `PalmTreeFoliagePlacer` 大量硬编码，依赖 `@Overwrite TreeFeature.doPlace`；1.21.1 中 `doPlace` 的参数已变成 `(WorldGenLevel, RandomSource, BlockPos, BiConsumer, BiConsumer, FoliagePlacer.FoliageSetter, TreeConfiguration)`，Mixin 必须同步修改，否者启动即崩。
- `PalmTreeGrower` 继承的 `AbstractTreeGrower` 在 1.21.1 已不存在；`SaplingBlock` 改用 final 的 `TreeGrower`（基于 `ResourceKey<ConfiguredFeature>`）。
- `MixinHumanoidArmorLayer` 被放在 `mixins`（common）列表，但它是客户端类；1.21.1 应放到 `client` 列表或移除。
- `LootPoolMixin` 假设 `LootPool.entries` 是数组；1.21.1 已改为 `List<LootPoolEntryContainer>`。
- `PicnicBasketMenu` 用 `ItemStack.getOrCreateTag()` 存物品；1.20.5+ 已移除 NBT 标签 API，需改用 `DataComponents.CUSTOM_DATA` / `CustomData`。
- `DishBlockEntity` 使用 `LazyOptional`/`IItemHandler`（Forge capability）；NeoForge 1.21 已移除 `LazyOptional`。
- `DishBlockEntityRenderer` 使用 `Vector3f`、`ItemTransforms.TransformType`、旧 `ItemRenderer.renderStatic` 签名。
- `PicnicBasketScreen` 使用 `PoseStack` + `RenderSystem`；1.20.5+ 改为 `GuiGraphics`。
- `CheffHatItemRenderer` 使用旧 `ItemRenderer` 构造器和 `TransformType`。
- `notImportant/addCutOutToJson.java` import 了 `javax.json.*`，但项目没有该依赖；1.19.2 下大概率也无法编译（属于废弃工具类）。
- `WorldGenPalm` 中 `world.getBlockEntity(pos).equals(Blocks.SAND)` 逻辑错误且未被注册。
- `LOFR.commonSetup` 里还有模板遗留的 `Config.items` 日志。
- `assets/lotsoffoodrev` 是一个空文件（合并 main 时留下的杂物）。
- `data/lots_of_food_reborn/loot_tables/gameplay/fishing/my_fish.json` 是空文件。
- 语言文件：`en_us.lang`、`es_mx.lang`、`zh_cn.lang` 是 1.12 时代的 `.lang` 格式；只有 `zh_cn.json` 是 JSON。1.21 不读取 `.lang`。
- 配方格式：1.19.2 的 `result.item`、`forge:*` 标签、`data/.../recipes`、`loot_tables`、`tags/items` 等目录/字段在 1.21 都要改。
- 厨师帽模型 `from` 的 Y=-18 超出 1.21 允许范围，运行时会有模型加载错误（不致命）。

---

## 7. reference 说明

`src/refference/` 下是两个前身：

| 目录 | 版本 | 作者 | 内容 |
|---|---|---|---|
| `Lot'sOfFoodRev` | 1.12.2，v1.5 | Dav729 | 资源 + **163 个 .class**（反编译前），无 Java 源码 |
| `更多食物老版` | 1.7，v1.10.2 | pifou92000 | 资源 + **.class**，无 Java 源码 |

只能作为**行为/资源参考**，不能直接编译。1.12.2 版本里能看到的未移植功能包括：

- `IceCreamMachine` / `BlockMachineGlaces` / `TileEntityIceCreamMachine` / `GuiIceCreamMachine` / `ContainerMachineGlaces`。
- `BlockDish` / `TileEntityDish` / `TileEntityDishRenderer`（当前最新分支已实现）。
- `PicnicBasket` / `PicnicContainer` / `GuiPicnicBasket`（当前已实现）。
- `ItemChapeauChef` / `ModelChapeauChef`（当前半实现）。
- `WorldGenAlgues`、`WorldGenCoffee`、`WorldGenCorn`、`WorldGenFraisier` 等野生作物生成（当前未实现）。
- `PDA` / Thirst 兼容（当前未实现）。

---

## 8. 1.21.1 / NeoForge 21.1.209 关键差异（已在本项目 `neoforge-21.1.209-sources.jar` 中核对）

### 8.1 构建/加载

- NeoForge 用 `net.neoforged.moddev`（ModDevGradle），Java 21，NeoForge 21.1.x。
- `mods.toml` → `neoforge.mods.toml`；依赖项用 `type="required"` 而不是 `mandatory=true`；mixin 配置用 `[[mixins]]`。
- `@Mod` 构造器可注入 `IEventBus` / `ModContainer`；`ModContainer.registerConfig(...)` 注册配置。
- `@Mod.EventBusSubscriber` → `net.neoforged.fml.common.EventBusSubscriber`。
- `FMLJavaModLoadingContext` / `ModLoadingContext` 不再是推荐入口。
- `net.minecraftforge.api.distmarker` → `net.neoforged.api.distmarker`。

### 8.2 注册 API

- `RegistryObject` 已移除 → `DeferredHolder` / `DeferredItem` / `DeferredBlock`。
- `DeferredRegister.create(ForgeRegistries.X, MODID)` → `DeferredRegister.create(BuiltInRegistries.X, MODID)` 或 `DeferredRegister.create(Registries.X, MODID)`。
- `ForgeRegistries.ITEMS/BLOCKS/...` → `BuiltInRegistries.ITEM/BLOCK/...`；自定义注册表用 `NeoForgeRegistries`。
- `ResourceLocation` 构造器不再推荐 → `ResourceLocation.parse(...)` / `fromNamespaceAndPath(...)`。
- `CreativeModeTab` 不能再 `new CreativeModeTab("...")`；改为注册 `CreativeModeTab` + `displayItems` / `BuildCreativeModeTabContentsEvent`，`Item.Properties.tab(...)` 已移除。

### 8.3 物品/食物

- `FoodProperties.Builder.saturationMod` → `saturationModifier`；`alwaysEat` → `alwaysEdible`。
- 食物是 `DataComponents.FOOD` 组件；`ItemStack.getFoodProperties(entity)` 取。
- `appendHoverText` 第二参数 `Level` → `Item.TooltipContext`。
- `Item.canEquip` 不存在；可装备物品实现 `Equipable`（`getEquipmentSlot()`）或继承 `ArmorItem`。
- `ItemStack` 的 NBT API（`getOrCreateTag`/`hasTag`/`getTag`）移除 → `DataComponents.CUSTOM_DATA` / `CustomData`。
- `ItemStack.getEnchantmentLevel(Enchantments.X)` → 基于 `Holder<Enchantment>` 的查询。
- `Entity.level` → `entity.level()`；`setSecondsOnFire` → `igniteForSeconds`。
- 自定义伤害类型需要 `data/<ns>/damage_type/*.json`，`DamageSource` 构造依赖 DamageType Holder。

### 8.4 方块/世界生成

- `Material` 已移除；`Properties.of(Material)` → `Properties.of()`；`Properties.copy(...)` → `ofFullCopy(...)`。
- `BaseEntityBlock` 是抽象类，子类必须实现 `codec()`（`simpleCodec(...)`）。
- `CropBlock.getAgeProperty()` 在 1.21.1 是 `protected`；跨包访问需要在自己的作物类里公开或提供辅助方法。
- `CropBlock.isMaxAge` 是 `final`。
- `isValidBonemealTarget` 签名改为 `(LevelReader, BlockPos, BlockState)`。
- `ForgeHooks.onCropsGrowPre/Post` → `CommonHooks.canCropGrow` / `fireCropGrowPost`（或 `CropGrowEvent`）。
- `SaplingBlock` 接受 final 的 `TreeGrower`，`TreeGrower` 通过 `ResourceKey<ConfiguredFeature>` 查表；`AbstractTreeGrower` 已不存在。
- `FoliagePlacer.createFoliage` 使用 `FoliagePlacer.FoliageSetter`（不再是 `BiConsumer`）。
- `TreeFeature.doPlace` 的签名已变化（第 6 个参数是 `FoliagePlacer.FoliageSetter`）。
- `BlockEntity.load/saveAdditional` 改为带 `HolderLookup.Provider` 的新签名；`getUpdateTag(Provider)`。

### 8.5 菜单/实体/渲染

- `IForgeMenuType` → `IMenuTypeExtension.create(...)`，数据用 `RegistryFriendlyByteBuf`。
- `NetworkHooks.openScreen` → `player.openMenu(provider, Consumer<RegistryFriendlyByteBuf>)`；`ItemStack` 用 `ItemStack.STREAM_CODEC` 编解码。
- `LazyOptional` 移除；能力系统改为 NeoForge capability 注册。
- `ItemStackHandler` 仍在，但 `serializeNBT/deserializeNBT` 需要 `HolderLookup.Provider`。
- GUI 渲染 `PoseStack` → `GuiGraphics`（`renderBg(GuiGraphics, ...)`、`graphics.blit(...)`）。
- `ItemTransforms.TransformType` → `ItemDisplayContext`；`Vector3f`/`Matrix3f` → JOML `org.joml.*` / `Axis`。
- `ThrownItemRenderer` 仍可用（1 参构造器保留）。
- `EntityType.Builder.build(String)` 仍可用。

### 8.6 数据包/资源格式

- 数据目录单数化：`recipes` → `recipe`、`loot_tables` → `loot_table`、`tags/items` → `tags/item`、`tags/blocks` → `tags/block`、`structures` → `structure`、`advancements` → `advancement`。
- 配方产物 `result.item` → `result.id`；配方 key 里 `forge:*` 标签改为 `c:*` 或本项目标签。
- `data/forge/tags/items/*` → `data/c/tags/item/*`（NeoForge 通用标签）。
- 生物群系修改器：`data/<ns>/forge/biome_modifier`、`forge:add_features` → `neoforge:add_features`（数据路径按 NeoForge 1.21 约定为 `neoforge/biome_modifier`）。
- `pack.mcmeta`：1.21.1 资源包格式 34、数据包格式 48；NeoForge 自己的 `pack.mcmeta` 使用 `"pack_format": 48` + `"supported_formats": [0, 2147483647]`。模板分支没有 `pack.mcmeta`，移植时需确认 NeoForge 是否自动补齐（建议显式提供）。
- 语言文件必须是 `assets/<ns>/lang/en_us.json` 等 JSON；`.lang` 不加载。
- 模型坐标限制：方块模型元素必须在 `[-16, 32]`，厨师帽模型 Y=-18 需要修正。
- `LootPool.entries` 是 `List<LootPoolEntryContainer>`；`LootTable.getPool` / `addPool` 仍存在；`LootTableLoadEvent` 仍存在。

---

## 9. 建议的移植步骤（待确认后执行）

1. **确认基线**：从 `origin/1.19.2-new` 取代码（推荐），或从当前 `master` 取旧代码。
2. **建分支**：从 `main` 建 `1.21.1-neoforge`，保留已可用的 ModDevGradle/NeoForge 21.1.209 构建环境；把 `origin/1.19.2-new` 的 `src/main/java`、`src/main/resources` 取过来，逐步迁移。
3. **构建环境**：确认 Java 21（`D:\.Environments\Java`）、Gradle 8.14.3、镜像仓库、Mixin 配置；先让空模板 `compileJava` 通过。
4. **注册层**：`DeferredRegister`/`DeferredHolder`/`BuiltInRegistries`、创造模式标签页、配置、事件总线。
5. **内容层**：方块/物品/食物/装备/作物/菜单/实体/BlockEntity，按编译错误逐类修。
6. **世界生成**：修正 `TreeGrower`、`FoliageSetter`、`TreeFeature.doPlace` Mixin；验证棕榈树和糖果屋。
7. **资源迁移**：目录单数化、配方 `result.id`、标签 `c:`、biome modifier、`pack.mcmeta`、lang JSON、厨师帽模型。
8. **收尾**：处理未实现内容的坏配方（冰激凌机等），保留资源但不让数据包报错。
9. **验证**：`compileJava` → `runClient` → 进入世界 → 检查注册/配方/战利品/模型日志 → `runServer`。

---

## 10. 风险与待确认项

1. **代码基线**：`origin/1.19.2-new`（推荐，含盘子和右键收割）还是当前 `master`（旧）？
2. **目标版本**：1.21.1 + NeoForge 21.1.209（推荐，环境已验证）还是更新的 1.21.x？
3. **分支名**：`1.21.1-neoforge`（推荐）还是 `br/1.21` / `1.21-neoforge`？
4. **未实现内容处理**：
   - 冰激凌机：保留资源、暂时禁用其配方，还是这次一起实现？
   - `dog_cookie`、`cheese_bucket`：补两个简单物品，还是禁用相关配方？
   - 厨师帽：修模型 + 实现 `Equipable`（能戴），头部渲染是否要一并做？
5. **提交策略**：是否允许在新分支上分阶段本地提交（不 push）？
6. **Mixin 策略**：棕榈树继续沿用 `@Overwrite TreeFeature.doPlace`，还是重写成自定义 Feature 以降低升级风险？

---

## 11. 参考命令（移植阶段使用）

```bash
# Java 21（按实际目录名调整）
D:/.Environments/Java/<jdk-21>/bin/java -version

# 编译
./gradlew compileJava

# 启动客户端（验证注册/资源/世界生成）
./gradlew runClient

# 启动服务端（验证数据包/战利品/结构）
./gradlew runServer
```
