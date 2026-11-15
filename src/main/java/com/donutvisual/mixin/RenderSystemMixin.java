package com.donutvisual.mixin;

import com.donutvisual.GlintState;
import com.mojang.blaze3d.systems.RenderSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public class RenderSystemMixin {
    // Glint's "end" action calls resetTextureMatrix; restore the shader colour there.
    @Inject(method = "resetTextureMatrix", at = @At("HEAD"))
    private static void donutvisual$glintEnd(CallbackInfo ci) { GlintState.end(); }
}
