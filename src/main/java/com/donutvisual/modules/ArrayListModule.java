package com.donutvisual.modules;
import com.donutvisual.Module;
import com.donutvisual.ModuleManager;
import net.minecraft.client.gui.DrawContext;
import java.util.Comparator;
import java.util.List;
public class ArrayListModule extends Module {
    public ArrayListModule() { super("ModuleList", "Rainbow list of enabled modules (top-right)", true, 0, 4); }
    @Override public void onHud(DrawContext ctx) {
        int sw = mc.getWindow().getScaledWidth();
        List<Module> on = ModuleManager.all().stream().filter(Module::isEnabled)
            .sorted(Comparator.comparingInt((Module m) -> mc.textRenderer.getWidth(m.name)).reversed()).toList();
        int yy = y, i = 0;
        for (Module m : on) {
            int w = mc.textRenderer.getWidth(m.name);
            int px = sw - w - 6;
            box(ctx, px - 2, yy - 1, sw, yy + 10);
            ctx.fill(sw - 2, yy - 1, sw, yy + 10, rainbow(i * 20f));
            ctx.drawText(mc.textRenderer, m.name, px, yy, rainbow(i * 20f), true);
            yy += 11; i++;
        }
    }
}
