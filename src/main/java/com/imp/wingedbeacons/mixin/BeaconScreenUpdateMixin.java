package com.imp.wingedbeacons.mixin;

import com.imp.wingedbeacons.WingedBeacons;
import net.minecraft.client.gui.screen.ingame.BeaconScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.BeaconScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * Mixin that manages creating & adding the flight button
 * to beacons
 */
@Mixin(BeaconScreen.class)
public abstract class BeaconScreenUpdateMixin extends HandledScreen<BeaconScreenHandler> {
    @Shadow @Final private List<BeaconScreen.class_6392> field_33832;

    public BeaconScreenUpdateMixin(BeaconScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(method = "init()V", at = @At("TAIL"))
    private void addButton(CallbackInfo info) {
        BeaconScreen.EffectButtonWidget effectButton = EffectButtonWidgetFactory.newEffectButtonWidget((BeaconScreen) (Object) this, this.x + 156, this.y + 72, WingedBeacons.FLIGHT_EFFECT, false, 3);
        effectButton.active = false;

        this.addDrawableChild(effectButton);
        this.field_33832.add(effectButton);

    }
}
