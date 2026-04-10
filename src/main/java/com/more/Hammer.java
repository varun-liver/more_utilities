package com.more;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.*;

public class Hammer extends PickaxeItem {

    public Hammer(Item.Properties properties, String type) {
        super(getTier(type), calcDamage(type), -2.8F, properties);
    }

    // 🔥 Choose tier based on type
    private static Tier getTier(String type) {
        return switch (type) {
            case "wooden" -> Tiers.WOOD;
            case "stone" -> Tiers.STONE;
            case "iron" -> Tiers.IRON;
            case "gold" -> Tiers.GOLD;
            case "diamond" -> Tiers.DIAMOND;
            case "netherite" -> Tiers.NETHERITE;
            default -> Tiers.WOOD;
        };
    }

    // ⚔️ Damage scaling
    private static int calcDamage(String type) {
        return switch (type) {
            case "wooden" -> 2;
            case "stone" -> 3;
            case "iron" -> 4;
            case "copper" -> 5;
            case "gold" -> 6;
            case "diamond" -> 7;
            case "netherite" -> 8;
            default -> 1;
        };
    }

}