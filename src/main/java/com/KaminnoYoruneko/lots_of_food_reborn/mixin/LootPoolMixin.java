package com.KaminnoYoruneko.lots_of_food_reborn.mixin;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Mixin(LootPool.class)
public abstract class LootPoolMixin {

    // Shadow掉LootPool类中的entries字段
    // Shadow掉LootPool类中的entries字段，允许访问
    @Shadow
    private LootPoolEntryContainer[] entries;

    // 新增方法：向LootPool中添加一个新的LootPoolEntry
    public void addEntry(LootPoolEntryContainer newEntry) {
        System.out.println("FUCKYOUENTRY");
        try {
            // 获取 LootPool 类中的 entries 字段
            Field entriesField = LootPool.class.getDeclaredField("entries");
            entriesField.setAccessible(true);  // 设置字段可访问

            // 获取当前的 entries 数组
            LootPoolEntryContainer[] currentEntries = (LootPoolEntryContainer[]) entriesField.get(this);

            // 将现有条目转成 List
            List<LootPoolEntryContainer> entryList = new ArrayList<>();
            for (LootPoolEntryContainer entry : currentEntries) {
                entryList.add(entry);
            }

            // 添加新的条目
            entryList.add(newEntry);

            // 将 List 转换回数组并重新赋值给 entries
            LootPoolEntryContainer[] updatedEntries = entryList.toArray(new LootPoolEntryContainer[0]);
            entriesField.set(this, updatedEntries);  // 重新设置字段值

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
//        // 先将现有条目转成List
//        List<LootPoolEntryContainer> entryList = new ArrayList<>();
//
//        // 将现有的entries中的条目添加到entryList
//        for (LootPoolEntryContainer entry : this.entries) {
//            entryList.add(entry);
//        }
//
//        // 添加新的条目
//        entryList.add(newEntry);
//
//        // 将List转换回数组并重新赋值给entries
//        this.entries = entryList.toArray(new LootPoolEntryContainer[0]);
    }
}