package com.Caff.UpgradedArsenal.item.custom;

import com.Caff.UpgradedArsenal.ModSounds;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class WitherStaffItem extends Item {

    public WitherStaffItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        // Prevent use if on cooldown
        if (attacker instanceof Player player) {
            if (player.getCooldowns().isOnCooldown(this)) {
                return false;
            }
        }

        // Apply Wither effect
        target.addEffect(new MobEffectInstance(
                MobEffects.WITHER,
                75, // 3.75 seconds
                1 // Wither II
        ));
        // Apply Slowness effect
        target.addEffect(new MobEffectInstance(
                MobEffects.MOVEMENT_SLOWDOWN,
                60, // 3 seconds
                1)); // Slowness II

        // Aura particles around target (server → client packet)
        // Spawn mixed black + purple particles around the player
        // --- Server-safe mixed black + purple particle burst around the target ---
        if (!target.level().isClientSide()) {
            net.minecraft.server.level.ServerLevel serverLevel = (net.minecraft.server.level.ServerLevel) target
                    .level();

            for (int i = 0; i < 60; i++) { // adjust count for intensity (60 is a good default)
                double angle = Math.random() * 2 * Math.PI;
                double radius = 0.6 + Math.random() * 0.6;
                double y = target.getY() + 0.5 + (Math.random() * 0.8);

                double x = target.getX() + Math.cos(angle) * radius;
                double z = target.getZ() + Math.sin(angle) * radius;

                double velX = (Math.random() - 0.5) * 0.04;
                double velY = 0.02 + Math.random() * 0.05;
                double velZ = (Math.random() - 0.5) * 0.04;

                // 50/50 chance between black smoke and purple-y particle
                net.minecraft.core.particles.ParticleOptions particle = Math.random() < 0.5
                        ? net.minecraft.core.particles.ParticleTypes.SMOKE // black-ish
                        : net.minecraft.core.particles.ParticleTypes.PORTAL; // purple-ish

                serverLevel.sendParticles(particle, x, y, z, 1, velX, velY, velZ, 0.01);
            }
        }

        // Play sound
        target.level().playSound(
                null,
                target.getX(), target.getY(), target.getZ(),
                ModSounds.WITHERSTAFF_WHISPERSTAB.get(),
                SoundSource.PLAYERS,
                1.0F,
                1.0F);

        // Damage weapon
        stack.hurtAndBreak(1, attacker, null);

        // --- Add cooldown (30 seconds) ---
        if (attacker instanceof Player player) {
            player.getCooldowns().addCooldown(this, 100);
        }

        return true;
    }
}
