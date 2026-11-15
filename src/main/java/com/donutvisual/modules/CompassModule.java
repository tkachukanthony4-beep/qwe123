package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.MathHelper;
public class CompassModule extends Module {
    // Minecraft yaw: 0 = south, 90 = west, 180 = north, 270 = east
    private static final String[] N = {"S","SW","W","NW","N","NE","E","SE"};
    public CompassModule() { super("Compass", "Scrolling direction bar", true, 0, 18); }
    @Override public void onHud(DrawContext ctx) {
        int cx = mc.getWindow().getScaledWidth() / 2;
        float yaw = mc.player.getYaw();
        box(ctx, cx - 90, y - 2, cx + 90, y + 12);
        ctx.enableScissor(cx - 90, y - 2, cx + 90, y + 12);
        for (int i = 0; i < 8; i++) {
            float diff = MathHelper.wrapDegrees(i * 45f - yaw);
            if (Math.abs(diff) > 70) continue;
            int px = cx + (int) (diff / 60f * 90f);
            boolean cardinal = i % 2 == 0;
            int w = mc.textRenderer.getWidth(N[i]);
            int col = N[i].equals("N") ? 0xFFFF5555 : cardinal ? 0xFFFFFFFF : 0xFFAAAAAA;
            ctx.drawText(mc.textRenderer, N[i], px - w / 2, y, col, true);
        }
        ctx.disableScissor();
        ctx.fill(cx - 1, y + 12, cx + 1, y + 15, rainbow(0));
    }
}
