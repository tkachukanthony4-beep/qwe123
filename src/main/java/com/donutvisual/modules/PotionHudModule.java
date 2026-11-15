package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import java.util.ArrayList;
public class PotionHudModule extends Module {
    public PotionHudModule() { super("PotionHUD", "Active effects with timers (bottom-right)", true, 0, 0); }
    @Override public void onHud(DrawContext ctx) {
        int sw = mc.getWindow().getScaledWidth(), sh = mc.getWindow().getScaledHeight();
        int yy = sh - 40;
        for (StatusEffectInstance e : new ArrayList<>(mc.player.getStatusEffects())) {
            String name = e.getEffectType().value().getName().getString() + (e.getAmplifier() > 0 ? " " + (e.getAmplifier() + 1) : "");
            int secs = e.getDuration() / 20;
            String time = e.isInfinite() ? "∞" : String.format("%d:%02d", secs / 60, secs % 60);
            String s = name + "  " + time;
            int w = mc.textRenderer.getWidth(s);
            int col = (!e.isInfinite() && secs < 10) ? 0xFFFF5555 : 0xFFFFFFFF;
            box(ctx, sw - w - 8, yy - 2, sw - 2, yy + 10);
            ctx.drawText(mc.textRenderer, s, sw - w - 5, yy, col, true);
            yy -= 12;
        }
    }
}
