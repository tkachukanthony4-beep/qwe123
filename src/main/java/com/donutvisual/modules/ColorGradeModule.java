package com.donutvisual.modules;
import com.donutvisual.Module;
import com.donutvisual.Setting;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
public class ColorGradeModule extends Module {
    // preset values: hue, tint%, brightness, vignette%
    private static final float[][] PRESETS = {
        null,
        {30, 14, 4, 25},   // Warm
        {210, 14, 0, 25},  // Cool
        {340, 18, -4, 40}, // Sunset
        {110, 16, 0, 30},  // Toxic
        {240, 24, -14, 50} // Night
    };
    public final Setting preset = add(new Setting("Preset", 1, "Custom", "Warm", "Cool", "Sunset", "Toxic", "Night"));
    public final Setting hue = add(new Setting("Tint hue", 0, 360, 30));
    public final Setting tint = add(new Setting("Tint strength", 0, 40, 12));
    public final Setting bright = add(new Setting("Brightness", -40, 40, 0));
    public final Setting vignette = add(new Setting("Vignette", 0, 80, 25));

    public ColorGradeModule() { super("ColorGrade", "Screen tint, brightness and vignette presets (overlay-based)", false, 0, 0); }

    /** Drawn behind the HUD, over the world (called from InGameHudMixin). */
    public void renderOverlay(DrawContext ctx) {
        int sw = mc.getWindow().getScaledWidth(), sh = mc.getWindow().getScaledHeight();
        float hu = hue.f(), ti = tint.f(), br = bright.f(), vi = vignette.f();
        float[] p = PRESETS[preset.i()];
        if (p != null) { hu = p[0]; ti = p[1]; br = p[2]; vi = p[3]; }

        if (ti > 0) {
            int rgb = MathHelper.hsvToRgb(hu / 360f, 1f, 1f);
            ctx.fill(0, 0, sw, sh, ((int) (ti * 2.55f) << 24) | rgb);
        }
        if (br != 0) {
            int a = (int) Math.min(120, Math.abs(br) * 2f);
            ctx.fill(0, 0, sw, sh, (a << 24) | (br > 0 ? 0xFFFFFF : 0x000000));
        }
        if (vi > 0) {
            int steps = 24;
            float edge = Math.min(sw, sh) * 0.4f;
            int strip = (int) Math.ceil(edge / steps);
            for (int i = 0; i < steps; i++) {
                float t = 1f - i / (float) steps;
                int a = (int) (vi * 2.55f * 0.8f * t * t);
                if (a <= 0) continue;
                int c = a << 24;
                int o = i * strip;
                ctx.fill(0, o, sw, o + strip, c);               // top
                ctx.fill(0, sh - o - strip, sw, sh - o, c);     // bottom
                ctx.fill(o, 0, o + strip, sh, c);               // left
                ctx.fill(sw - o - strip, 0, sw - o, sh, c);     // right
            }
        }
    }
}
