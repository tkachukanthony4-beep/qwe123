package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
public class FpsGraphModule extends Module {
    private final int[] samples = new int[60];
    private int idx;
    public FpsGraphModule() { super("FPSGraph", "Frame-rate history graph (bottom-left)", true, 6, 0); }
    @Override public void onTick() { samples[idx] = mc.getCurrentFps(); idx = (idx + 1) % samples.length; }
    @Override public void onHud(DrawContext ctx) {
        int h = 30, w = samples.length;
        int by = mc.getWindow().getScaledHeight() - 50;
        int max = 60;
        for (int s : samples) max = Math.max(max, s);
        box(ctx, x - 2, by - h - 2, x + w + 2, by + 2);
        for (int i = 0; i < w; i++) {
            int v = samples[(idx + i) % w];
            int bh = Math.max(1, v * h / max);
            int col = v >= 100 ? 0xFF55FF55 : v >= 40 ? 0xFFFFFF55 : 0xFFFF5555;
            ctx.fill(x + i, by - bh, x + i + 1, by, col);
        }
    }
}
