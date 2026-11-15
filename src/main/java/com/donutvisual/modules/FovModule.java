package com.donutvisual.modules;
import com.donutvisual.Module;
import com.donutvisual.Setting;
public class FovModule extends Module {
    public final Setting fov = add(new Setting("FOV", 30, 140, 90));
    public final Setting dynamic = add(Setting.bool("Keep sprint FOV", true));
    public FovModule() { super("CustomFOV", "Set any FOV from 30 to 140 (beyond vanilla's slider)", false, 0, 0); }
    public float apply(float f) {
        double base = mc.options.getFov().getValue();
        return dynamic.b() ? (float) (f * (fov.f() / base)) : fov.f();
    }
}
