package com.imp.wingedbeacons.mixin;

import com.imp.wingedbeacons.WingedBeacons;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(BeaconBlockEntity.class)
public class BeaconBlockEntityMixin {
    @Shadow @Final private static Set<StatusEffect> EFFECTS;

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void updateEffectsList(BlockPos pos, BlockState state, CallbackInfo info) {
        EFFECTS.add(WingedBeacons.FLIGHT_EFFECT);
    }
}
