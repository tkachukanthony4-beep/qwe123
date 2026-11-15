package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
public class SpeedModule extends Module {
    private double px, pz, bps;
    public SpeedModule() { super("Speed", "Blocks per second", false, 6, 166); }
    @Override public void onTick() {
        double dx = mc.player.getX() - px, dz = mc.player.getZ() - pz;
        bps = bps * 0.7 + Math.hypot(dx, dz) * 20 * 0.3;
        px = mc.player.getX(); pz = mc.player.getZ();
    }
    @Override public void onHud(DrawContext ctx) { label(ctx, String.format("%.1f b/s", bps)); }
}
