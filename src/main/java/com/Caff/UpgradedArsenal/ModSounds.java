package com.Caff.UpgradedArsenal;

import com.Caff.UpgradedArsenal.UpgradedArsenal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, UpgradedArsenal.MOD_ID);

    public static final RegistryObject<SoundEvent> WITHERSTAFF_WHISPERSTAB =
            registerSound("witherstaff_whisperstab");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        // Use the factory method instead of calling the constructor directly
        ResourceLocation loc = ResourceLocation.fromNamespaceAndPath(UpgradedArsenal.MOD_ID, name);
        return SOUND_EVENTS.register(name,
                () -> SoundEvent.createVariableRangeEvent(loc));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}