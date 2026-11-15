package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
import java.util.ArrayDeque;
public class CpsModule extends Module {
    private final ArrayDeque<Long> left = new ArrayDeque<>(), right = new ArrayDeque<>();
    private boolean lastL, lastR;
    public CpsModule() { super("CPS", "Clicks per second", true, 6, 38); }
    @Override public void onTick() {
        boolean l = mc.options.attackKey.isPressed(), r = mc.options.useKey.isPressed();
        long now = System.currentTimeMillis();
        if (l && !lastL) left.add(now);
        if (r && !lastR) right.add(now);
        lastL = l; lastR = r;
        while (!left.isEmpty() && now - left.peek() > 1000) left.poll();
        while (!right.isEmpty() && now - right.peek() > 1000) right.poll();
    }
    @Override public void onHud(DrawContext ctx) { label(ctx, left.size() + " | " + right.size() + " CPS"); }
}
