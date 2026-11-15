package com.donutvisual.modules;
import com.donutvisual.Module;
import com.donutvisual.Setting;
public class GlintModule extends Module {
    public final Setting mode = add(new Setting("Mode", 0, "Static", "Rainbow"));
    public final Setting hue = add(new Setting("Hue", 0, 360, 275));
    public final Setting sat = add(new Setting("Saturation", 0, 100, 75));
    public GlintModule() { super("CustomGlint", "Recolour the enchantment glint (items + armor)", false, 0, 0); }

    public static final float[] DEFAULT = rgb(275f, 0.75f);

    public float[] color() {
        return rgb(mode.i() == 1 ? rainbowHue(0) : hue.f(), sat.f() / 100f);
    }
}
