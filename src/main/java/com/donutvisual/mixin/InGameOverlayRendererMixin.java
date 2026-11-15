package com.donutvisual.mixin;

import com.donutvisual.ModuleManager;
import com.donutvisual.modules.NoFireModule;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void donutvisual$noFire(MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        NoFireModule m = ModuleManager.get(NoFireModule.class);
        if (m != null && m.isEnabled()) ci.cancel();
    }
}
