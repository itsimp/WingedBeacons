package com.imp.wingedbeacons.effect;

import com.imp.wingedbeacons.WingedBeaconsConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FlightEffect extends StatusEffect {

    public FlightEffect() { super(StatusEffectType.BENEFICIAL, 0xe1f2fc); }

    private final ArrayList<PlayerEntity> playerList = new ArrayList<>();

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            player.getAbilities().allowFlying = true;
            player.sendAbilitiesUpdate();
        }
    }

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            playerList.remove(player);
        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            if (!player.isCreative() && !player.isSpectator()) {
                playerList.add(player);

                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if (playerList.contains(player)) {
                            playerList.remove(player);

                            player.getAbilities().allowFlying = false;
                            player.getAbilities().flying = false;

                            if (WingedBeaconsConfig.INSTANCE.slowFallingTime >= 1)
                                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, WingedBeaconsConfig.INSTANCE.slowFallingTime * 20, 1, false, false));
                        } else {
                            player.getAbilities().allowFlying = true;
                        }
                        player.sendAbilitiesUpdate();
                    }
                };

                Timer timer = new Timer("PlayerBeaconFlightEffectTimer");
                timer.schedule(task, 2000L);
            }
        }
    }
}
