package com.Caff.UpgradedArsenal.item;

import com.Caff.UpgradedArsenal.UpgradedArsenal;
import com.Caff.UpgradedArsenal.item.custom.WitherStaffItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Moditems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, UpgradedArsenal.MOD_ID);

    public static final RegistryObject<Item> WITHERSTAFF = ITEMS.register("witherstaff",
            () -> new WitherStaffItem(
                    new Item.Properties()
                            .durability(250) // staff has durability
                            .stacksTo(1) // single item only
            ));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}