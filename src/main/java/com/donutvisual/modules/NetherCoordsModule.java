package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.world.World;
public class NetherCoordsModule extends Module {
    public NetherCoordsModule() { super("NetherCoords", "Overworld <-> Nether coordinate conversion", false, 6, 244); }
    @Override public void onHud(DrawContext ctx) {
        boolean nether = mc.world.getRegistryKey() == World.NETHER;
        double f = nether ? 8.0 : 0.125;
        label(ctx, String.format("%s: %.0f %.0f", nether ? "Overworld" : "Nether", mc.player.getX() * f, mc.player.getZ() * f));
    }
}
