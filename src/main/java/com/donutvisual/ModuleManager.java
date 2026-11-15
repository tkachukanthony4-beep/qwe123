package com.donutvisual;

import com.donutvisual.modules.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private static final List<Module> MODULES = new ArrayList<>();

    public static void init() {
        // HUD readouts
        MODULES.add(new FpsModule());
        MODULES.add(new CoordsModule());
        MODULES.add(new CpsModule());
        MODULES.add(new KeystrokesModule());
        MODULES.add(new ClockModule());
        MODULES.add(new SpeedModule());
        MODULES.add(new PingModule());
        MODULES.add(new MemoryModule());
        MODULES.add(new WorldInfoModule());
        MODULES.add(new NetherCoordsModule());
        // Visual overlays
        MODULES.add(new WatermarkModule());
        MODULES.add(new CompassModule());
        MODULES.add(new ArrayListModule());
        MODULES.add(new FpsGraphModule());
        MODULES.add(new TargetDotModule());
        MODULES.add(new ArmorHudModule());
        MODULES.add(new PotionHudModule());
        MODULES.add(new CountersModule());
        // Rendering
        MODULES.add(new BlockOverlayModule());
        MODULES.add(new ColorGradeModule());
        MODULES.add(new GlintModule());
        MODULES.add(new HandAuraModule());
        MODULES.add(new HandPoseModule());
        // Camera / world
        MODULES.add(new FovModule());
        MODULES.add(new ZoomModule());
        MODULES.add(new FullbrightModule());
        MODULES.add(new NoHurtCamModule());
        MODULES.add(new NoFireModule());
        MODULES.add(new ToggleSprintModule());
    }

    public static List<Module> all() { return MODULES; }

    @SuppressWarnings("unchecked")
    public static <T extends Module> T get(Class<T> c) {
        for (Module m : MODULES) if (c.isInstance(m)) return (T) m;
        return null;
    }
}
