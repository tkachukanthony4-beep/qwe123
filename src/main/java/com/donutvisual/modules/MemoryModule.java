package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
public class MemoryModule extends Module {
    public MemoryModule() { super("Memory", "RAM usage", false, 6, 198); }
    @Override public void onHud(DrawContext ctx) {
        Runtime r = Runtime.getRuntime();
        long used = (r.totalMemory() - r.freeMemory()) >> 20, max = r.maxMemory() >> 20;
        label(ctx, used + " / " + max + " MB");
    }
}
