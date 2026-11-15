package com.KaminoYoruneko.lots_of_food_reborn.mixin;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Mixin(LootPool.class)
public abstract class LootPoolMixin {

    @Shadow
    private List<LootPoolEntryContainer> entries;

    /**
     * 向现有 LootPool 追加条目。1.21 的 entries 是 List，优先直接修改；
     * 如果列表不可变，再通过反射替换字段。
     */
    public void addEntry(LootPoolEntryContainer newEntry) {
        try {
            this.entries.add(newEntry);
        } catch (UnsupportedOperationException e) {
            try {
                Field entriesField = LootPool.class.getDeclaredField("entries");
                entriesField.setAccessible(true);
                List<LootPoolEntryContainer> updated = new ArrayList<>(this.entries);
                updated.add(newEntry);
                entriesField.set(this, updated);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }
}
