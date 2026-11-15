package com.donutvisual.mixin;

import com.donutvisual.ModuleManager;
import com.donutvisual.modules.ZoomModule;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {
    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void donutvisual$zoomScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        ZoomModule z = ModuleManager.get(ZoomModule.class);
        if (z != null && z.isEnabled() && z.scroll(vertical)) ci.cancel();
    }
}
