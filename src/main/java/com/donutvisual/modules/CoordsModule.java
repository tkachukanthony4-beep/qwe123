package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
public class CoordsModule extends Module {
    public CoordsModule() { super("Coords", "XYZ and facing direction", true, 6, 22); }
    @Override public void onHud(DrawContext ctx) {
        var p = mc.player;
        label(ctx, String.format("XYZ: %.0f %.0f %.0f (%s)", p.getX(), p.getY(), p.getZ(), p.getHorizontalFacing().asString()));
    }
}
