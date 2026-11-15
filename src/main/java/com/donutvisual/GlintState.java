package com.donutvisual;

import com.donutvisual.modules.GlintModule;
import com.mojang.blaze3d.systems.RenderSystem;

public class GlintState {
    private static float[] saved;
    private static boolean active;

    public static void begin() {
        GlintModule m = ModuleManager.get(GlintModule.class);
        float[] c = (m != null && m.isEnabled()) ? m.color() : GlintModule.DEFAULT;
        saved = RenderSystem.getShaderColor();
        RenderSystem.setShaderColor(c[0], c[1], c[2], saved[3]);
        active = true;
    }

    public static void end() {
        if (!active) return;
        RenderSystem.setShaderColor(saved[0], saved[1], saved[2], saved[3]);
        active = false;
    }
}
