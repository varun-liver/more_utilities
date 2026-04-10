package com.more;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

import static com.more.utilities.ITEMS;

public class ModHammers {
    public static final RegistryObject<Item> WOODEN_HAMMER = ITEMS.register("wooden_hammer", () -> new Hammer(new Item.Properties(),"wooden"));
    public static final RegistryObject<Item> STONE_HAMMER = ITEMS.register("stone_hammer", () -> new Hammer(new Item.Properties(),"stone"));
    public static final RegistryObject<Item> IRON_HAMMER = ITEMS.register("iron_hammer", () -> new Hammer(new Item.Properties(),"iron"));
    public static void register(IEventBus eventBus) {
        // Forces class loading
    }
}
