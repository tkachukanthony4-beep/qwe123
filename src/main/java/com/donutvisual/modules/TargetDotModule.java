package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.hit.HitResult;
public class TargetDotModule extends Module {
    public TargetDotModule() { super("TargetDot", "Center dot: red = entity, green = block", true, 0, 0); }
    @Override public void onHud(DrawContext ctx) {
        int cx = mc.getWindow().getScaledWidth() / 2, cy = mc.getWindow().getScaledHeight() / 2;
        HitResult h = mc.crosshairTarget;
        int col = 0xFFFFFFFF;
        if (h != null) {
            if (h.getType() == HitResult.Type.ENTITY) col = 0xFFFF4444;
            else if (h.getType() == HitResult.Type.BLOCK) col = 0xFF44FF44;
        }
        ctx.fill(cx - 1, cy - 1, cx + 1, cy + 1, col);
    }
}
