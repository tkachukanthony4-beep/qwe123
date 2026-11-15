package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
public class WatermarkModule extends Module {
    public WatermarkModule() { super("Watermark", "Rainbow client title at top-center", true, 0, 4); }
    @Override public void onHud(DrawContext ctx) {
        String t = "Donut Visual";
        int w = mc.textRenderer.getWidth(t);
        int cx = (mc.getWindow().getScaledWidth() - w) / 2;
        box(ctx, cx - 4, y - 2, cx + w + 4, y + 10);
        for (int i = 0; i < t.length(); i++) {
            String c = String.valueOf(t.charAt(i));
            ctx.drawText(mc.textRenderer, c, cx, y, rainbow(i * 14f), true);
            cx += mc.textRenderer.getWidth(c);
        }
    }
}
