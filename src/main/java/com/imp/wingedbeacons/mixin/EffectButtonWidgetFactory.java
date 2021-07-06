package com.imp.wingedbeacons.mixin;

import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BeaconScreen.EffectButtonWidget.class)
@SuppressWarnings("InvokerTarget")
public interface EffectButtonWidgetFactory {
    @Invoker("<init>")
    static BeaconScreen.EffectButtonWidget newEffectButtonWidget(BeaconScreen screen, int x, int y, StatusEffect statusEffect, boolean primary, int i) {
        throw new AssertionError();
    }
}