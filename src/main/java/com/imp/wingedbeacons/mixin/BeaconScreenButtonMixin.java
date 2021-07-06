package com.imp.wingedbeacons.mixin;

import com.imp.wingedbeacons.WingedBeacons;
import com.imp.wingedbeacons.WingedBeaconsClient;
import com.imp.wingedbeacons.WingedBeaconsConfig;
import com.imp.wingedbeacons.event.AdvancementEventHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin that manages setting the visibility
 * of the flight button on beacons
 */
@Mixin(BeaconScreen.EffectButtonWidget.class)
public abstract class BeaconScreenButtonMixin extends BeaconScreen.BaseButtonWidget {

    @Shadow private StatusEffect effect;

    protected BeaconScreenButtonMixin(int x, int y) { super(x, y); }

    @Inject(method = "method_37080(I)V", at = @At("TAIL"))
    private void updateState(int i, CallbackInfo info) {
        if (this.effect == WingedBeacons.FLIGHT_EFFECT) {
            if (MinecraftClient.getInstance().player != null) {
                if (MinecraftClient.getInstance().player.experienceLevel >= WingedBeaconsConfig.INSTANCE.xpLevelRequired) {
                    ClientPlayNetworking.send(WingedBeacons.ADVANCEMENT_C2S_PACKET, PacketByteBufs.empty());

                    if (WingedBeaconsClient.ADVANCEMENT_EVENT_HANDLER.playerList.contains(MinecraftClient.getInstance().player.getUuid())) {
                        this.visible = true;
                    } else {
                        this.visible = false;
                    }

                } else {
                    this.visible = false;
                }
            } else {
                this.visible = false;
            }
        }
    }
}
