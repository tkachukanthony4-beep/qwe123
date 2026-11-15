package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
public class PingModule extends Module {
    public PingModule() { super("Ping", "Server latency", false, 6, 182); }
    @Override public void onHud(DrawContext ctx) {
        var h = mc.getNetworkHandler();
        var e = h == null ? null : h.getPlayerListEntry(mc.player.getUuid());
        int ms = e == null ? 0 : e.getLatency();
        label(ctx, ms + " ms");
    }
}
