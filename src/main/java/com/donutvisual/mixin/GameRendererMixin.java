package com.donutvisual.mixin;

import com.donutvisual.ModuleManager;
import com.donutvisual.modules.FovModule;
import com.donutvisual.modules.NoHurtCamModule;
import com.donutvisual.modules.ZoomModule;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    // changingFov == true is the world view; false is the hand's FOV, which we leave alone.
    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void donutvisual$fov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        if (!changingFov) return;
        float f = cir.getReturnValue();
        FovModule fm = ModuleManager.get(FovModule.class);
        if (fm != null && fm.isEnabled()) f = fm.apply(f);
        ZoomModule z = ModuleManager.get(ZoomModule.class);
        if (z != null && z.isEnabled()) f /= z.factor();
        cir.setReturnValue(f);
    }

    @Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void donutvisual$noHurtCam(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        NoHurtCamModule m = ModuleManager.get(NoHurtCamModule.class);
        if (m != null && m.isEnabled()) ci.cancel();
    }
}
