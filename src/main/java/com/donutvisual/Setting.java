package com.donutvisual;

/** Either a numeric slider (min..max) or a cycle-through-options button. */
public class Setting {
    public final String name;
    public final float min, max;
    public final String[] options; // null => slider
    public float value;

    public Setting(String name, float min, float max, float def) {
        this.name = name; this.min = min; this.max = max; this.value = def; this.options = null;
    }
    public Setting(String name, int def, String... options) {
        this.name = name; this.min = 0; this.max = options.length - 1; this.value = def; this.options = options;
    }
    public static Setting bool(String name, boolean def) { return new Setting(name, def ? 1 : 0, "OFF", "ON"); }

    public boolean isMode() { return options != null; }
    public float f() { return value; }
    public int i() { return Math.round(value); }
    public boolean b() { return value >= 0.5f; }
}
