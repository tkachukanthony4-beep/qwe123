package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
public class FpsModule extends Module {
    public FpsModule() { super("FPS", "Shows your frame rate", true, 6, 6); }
    @Override public void onHud(DrawContext ctx) { label(ctx, mc.getCurrentFps() + " FPS"); }
}
