package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class ClockModule extends Module {
    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("h:mm a");
    public ClockModule() { super("Clock", "Real-world time", false, 6, 150); }
    @Override public void onHud(DrawContext ctx) { label(ctx, LocalTime.now().format(F)); }
}
