package com.oekt.oerocks.sound;

import com.oekt.oerocks.OErocks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, OErocks.MODID);

    public static final RegistryObject<SoundEvent> SETTLEMENT_BRAEK = registerSoundEvent("settlement_break");
    public static final RegistryObject<SoundEvent> SETTLEMENT_HIT = registerSoundEvent("settlement_hit");

    public static final RegistryObject<SoundEvent> SETTLEMENT_WALK = registerSoundEvent("settlement_walk");

    public static final RegistryObject<SoundEvent> SETTLEMENT_FALL = registerSoundEvent("settlement_fall");
    public static final ForgeSoundType BLOCK_SETTLEMET_SOUNDS = new ForgeSoundType(1f, 1f, SETTLEMENT_BRAEK, SETTLEMENT_WALK, SETTLEMENT_HIT, SETTLEMENT_HIT, SETTLEMENT_FALL);
    public static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(OErocks.MODID, name)));
    }

}
