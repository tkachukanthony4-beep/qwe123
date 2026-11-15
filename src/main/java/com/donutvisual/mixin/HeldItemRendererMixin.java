package com.donutvisual.mixin;

import com.donutvisual.ModuleManager;
import com.donutvisual.modules.HandPoseModule;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {
    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"))
    private void donutvisual$pose(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand,
                                  float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices,
                                  VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        HandPoseModule m = ModuleManager.get(HandPoseModule.class);
        if (m == null || !m.isEnabled()) return;
        boolean right = (hand == Hand.MAIN_HAND) == (player.getMainArm() == Arm.RIGHT);
        m.apply(matrices, right, hand == Hand.OFF_HAND);
    }
}
