package com.donutvisual.modules;
import com.donutvisual.Module;
import com.donutvisual.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.Perspective;
public class HandAuraModule extends Module {
    public final Setting size = add(new Setting("Size", 20, 120, 70));
    public final Setting intensity = add(new Setting("Intensity", 5, 60, 28));
    public final Setting color = add(new Setting("Color", 0, "Rainbow", "Static"));
    public final Setting hue = add(new Setting("Hue", 0, 360, 280));
    public final Setting pulse = add(Setting.bool("Pulse", true));
    public HandAuraModule() { super("HandAura", "Soft glow around your first-person hand", false, 0, 0); }

    @Override public void onHud(DrawContext ctx) {
        if (mc.options.getPerspective() != Perspective.FIRST_PERSON) return;
        int sw = mc.getWindow().getScaledWidth(), sh = mc.getWindow().getScaledHeight();
        int cx = (int) (sw * 0.78f), cy = (int) (sh * 0.86f);
        float[] c = rgb(color.i() == 0 ? rainbowHue(0) : hue.f(), 0.85f);
        int rgb = ((int) (c[0] * 255) << 16) | ((int) (c[1] * 255) << 8) | (int) (c[2] * 255);
        float p = pulse.b() ? 0.8f + 0.2f * (float) Math.sin(System.currentTimeMillis() / 300.0) : 1f;
        int rings = 9;
        int a = Math.max(1, (int) (intensity.f() * 2.55f / rings * p));
        for (int i = 0; i < rings; i++) {
            int r = (int) (size.f() * (1f - i / (float) rings));
            disc(ctx, cx, cy, r, (a << 24) | rgb);
        }
    }

    private static void disc(DrawContext ctx, int cx, int cy, int r, int argb) {
        for (int dy = -r; dy < r; dy += 2) {
            int half = (int) Math.sqrt(Math.max(0, (double) r * r - (double) dy * dy));
            ctx.fill(cx - half, cy + dy, cx + half, cy + dy + 2, argb);
        }
    }
}
