package com.KaminoYoruneko.lots_of_food_reborn.register;

/**
 * 旧版这里试图把 LootTable 当作注册表对象注册，这在 1.19/1.21 都不成立。
 * 战利品表通过数据包加载，实际逻辑在 {@link com.KaminoYoruneko.lots_of_food_reborn.handler.WorldEventHandler}。
 */
public final class LootRegister {
    private LootRegister() {
    }
}
