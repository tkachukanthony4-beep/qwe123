package com.donutvisual.mixin;

import com.donutvisual.GlintState;
import net.minecraft.client.render.RenderPhase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPhase.class)
public class RenderPhaseMixin {
    @Inject(method = "setupGlintTexturing", at = @At("HEAD"))
    private static void donutvisual$glintBegin(float scale, CallbackInfo ci) { GlintState.begin(); }
}
