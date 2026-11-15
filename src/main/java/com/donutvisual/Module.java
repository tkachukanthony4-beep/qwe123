package com.donutvisual;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
import java.util.ArrayList;
import java.util.List;

public abstract class Module {
    public final String name;
    public final String description;
    public final List<Setting> settings = new ArrayList<>();
    private boolean enabled;
    public int x, y; // HUD position

    protected final MinecraftClient mc = MinecraftClient.getInstance();

    protected Module(String name, String description, boolean enabledByDefault, int x, int y) {
        this.name = name; this.description = description; this.enabled = enabledByDefault; this.x = x; this.y = y;
    }

    protected Setting add(Setting s) { settings.add(s); return s; }

    public boolean isEnabled() { return enabled; }
    public void toggle() { setEnabled(!enabled); }
    public void setEnabled(boolean v) {
        if (v == enabled) return;
        enabled = v;
        if (v) onEnable(); else onDisable();
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onTick() {}
    public void onHud(DrawContext ctx) {}

    public static float rainbowHue(float offset) { return (((System.currentTimeMillis() / 15L) + (long) offset) % 360L); }

    /** Animated rainbow colour (ARGB). */
    public static int rainbow(float offset) {
        return 0xFF000000 | MathHelper.hsvToRgb(rainbowHue(offset) / 360f, 0.75f, 1f);
    }

    /** Hue (0-360) + saturation (0-1) -> {r,g,b} in 0..1. */
    public static float[] rgb(float hue, float sat) {
        int c = MathHelper.hsvToRgb((hue % 360f) / 360f, sat, 1f);
        return new float[]{((c >> 16) & 255) / 255f, ((c >> 8) & 255) / 255f, (c & 255) / 255f};
    }

    protected void box(DrawContext ctx, int x1, int y1, int x2, int y2) { ctx.fill(x1, y1, x2, y2, 0x90000000); }
    protected void label(DrawContext ctx, String text) { label(ctx, text, 0); }
    protected void label(DrawContext ctx, String text, int dy) {
        int w = mc.textRenderer.getWidth(text);
        box(ctx, x - 2, y + dy - 2, x + w + 2, y + dy + 10);
        ctx.drawText(mc.textRenderer, text, x, y + dy, 0xFFFFFFFF, true);
    }
}
