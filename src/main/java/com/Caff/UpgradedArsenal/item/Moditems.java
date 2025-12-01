package com.Caff.UpgradedArsenal.item;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import com.Caff.UpgradedArsenal.UpgradedArsenal;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Moditems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
            UpgradedArsenal.MOD_ID);

    public static final RegistryObject<Item> WITHERSTAFF = ITEMS.register("witherstaff",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
