package com.donutvisual;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ModuleScreen extends Screen {
    public ModuleScreen() { super(Text.literal("Donut Visual Client")); }

    private static Text label(Module m) { return Text.literal(m.name + ": " + (m.isEnabled() ? "§aON" : "§cOFF")); }

    @Override
    protected void init() {
        int w = 110, h = 20, gap = 5;
        int cols = Math.max(2, Math.min(5, (width - 20) / (w + gap)));
        int startX = width / 2 - (cols * w + (cols - 1) * gap) / 2;
        int startY = 48, i = 0;
        for (Module m : ModuleManager.all()) {
            int cx = startX + (i % cols) * (w + gap), cy = startY + (i / cols) * (h + gap);
            boolean hasSettings = !m.settings.isEmpty();
            addDrawableChild(ButtonWidget.builder(label(m), b -> { m.toggle(); b.setMessage(label(m)); })
                .dimensions(cx, cy, hasSettings ? w - 22 : w, h)
                .tooltip(Tooltip.of(Text.literal(m.description))).build());
            if (hasSettings)
                addDrawableChild(ButtonWidget.builder(Text.literal("⚙"), b -> client.setScreen(new SettingsScreen(m, this)))
                    .dimensions(cx + w - 20, cy, 20, h).tooltip(Tooltip.of(Text.literal("Settings"))).build());
            i++;
        }
    }

    @Override public void render(DrawContext ctx, int mx, int my, float delta) {
        super.render(ctx, mx, my, delta);
        ctx.drawCenteredTextWithShadow(textRenderer, "§6§lDonut Visual Client", width / 2, 22, 0xFFFFFF);
        ctx.drawCenteredTextWithShadow(textRenderer, "Click to toggle  •  ⚙ opens settings", width / 2, 34, 0xAAAAAA);
    }

    @Override public void removed() { Config.save(); }
}
