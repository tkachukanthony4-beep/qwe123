package com.donutvisual.mixin;

import com.donutvisual.ModuleManager;
import com.donutvisual.modules.ColorGradeModule;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At("HEAD"))
    private void donutvisual$grade(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        ColorGradeModule m = ModuleManager.get(ColorGradeModule.class);
        if (m != null && m.isEnabled()) m.renderOverlay(context);
    }
}
