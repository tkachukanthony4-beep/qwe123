package com.donutvisual.modules;
import com.donutvisual.DonutVisualClient;
import com.donutvisual.Module;
import com.donutvisual.Setting;
public class ZoomModule extends Module {
    public final Setting level = add(new Setting("Zoom level", 2, 30, 4));
    public final Setting smooth = add(Setting.bool("Smooth", true));
    public final Setting scroll = add(Setting.bool("Scroll to adjust", true));
    public final Setting cinematic = add(Setting.bool("Cinematic camera", false));
    private float current = 1f, scrollOffset = 0f;
    private long last = System.nanoTime();
    private boolean setCine;

    public ZoomModule() { super("Zoom", "Hold C to zoom. Scroll while zooming to adjust.", true, 0, 0); }

    public boolean isActive() { return isEnabled() && DonutVisualClient.ZOOM_KEY != null && DonutVisualClient.ZOOM_KEY.isPressed(); }

    /** Called once per frame from the FOV mixin; eases toward the target. */
    public float factor() {
        long now = System.nanoTime();
        float dt = Math.min(0.1f, (now - last) / 1e9f);
        last = now;
        float target = isActive() ? Math.max(1.1f, Math.min(50f, level.f() + scrollOffset)) : 1f;
        if (smooth.b()) current += (target - current) * (1f - (float) Math.exp(-dt * 12f));
        else current = target;
        if (Math.abs(current - 1f) < 0.005f && target == 1f) current = 1f;
        return current;
    }

    public boolean scroll(double v) {
        if (!isActive() || !scroll.b()) return false;
        scrollOffset += (float) v * (level.f() + scrollOffset) * 0.15f;
        scrollOffset = Math.max(1.1f - level.f(), Math.min(50f - level.f(), scrollOffset));
        return true;
    }

    @Override public void onTick() {
        if (isActive() && cinematic.b()) { mc.options.smoothCameraEnabled = true; setCine = true; }
        else if (setCine) { mc.options.smoothCameraEnabled = false; setCine = false; }
        if (!isActive()) scrollOffset = 0f;
    }
    @Override public void onDisable() { if (setCine) { mc.options.smoothCameraEnabled = false; setCine = false; } }
}
