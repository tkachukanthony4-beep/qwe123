package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
public class WorldInfoModule extends Module {
    public WorldInfoModule() { super("WorldInfo", "Day count and current biome", false, 6, 214); }
    @Override public void onHud(DrawContext ctx) {
        long day = mc.world.getTimeOfDay() / 24000L;
        String biome = mc.world.getBiome(mc.player.getBlockPos()).getKey().map(k -> k.getValue().getPath()).orElse("unknown");
        label(ctx, "Day " + day);
        label(ctx, biome.replace('_', ' '), 14);
    }
}
