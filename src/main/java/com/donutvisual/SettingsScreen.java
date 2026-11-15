package com.donutvisual;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class SettingsScreen extends Screen {
    private final Module module;
    private final Screen parent;

    public SettingsScreen(Module module, Screen parent) {
        super(Text.literal(module.name + " settings"));
        this.module = module; this.parent = parent;
    }

    private static class SettingSlider extends SliderWidget {
        private final Setting s;
        SettingSlider(int x, int y, int w, int h, Setting s) {
            super(x, y, w, h, Text.empty(), (s.value - s.min) / (s.max - s.min));
            this.s = s; updateMessage();
        }
        @Override protected void updateMessage() { if (s != null) setMessage(Text.literal(s.name + ": " + Math.round(s.value))); }
        @Override protected void applyValue() { s.value = Math.round(s.min + (float) value * (s.max - s.min)); }
    }

    private static Text modeLabel(Setting s) { return Text.literal(s.name + ": " + s.options[s.i()]); }

    @Override
    protected void init() {
        int n = module.settings.size();
        int cols = n > 7 ? 2 : 1, w = 150, h = 20;
        int startX = width / 2 - (cols * w + (cols - 1) * 8) / 2;
        for (int i = 0; i < n; i++) {
            Setting s = module.settings.get(i);
            int cx = startX + (i % cols) * (w + 8), cy = 45 + (i / cols) * 24;
            if (s.isMode()) {
                addDrawableChild(ButtonWidget.builder(modeLabel(s), b -> {
                    s.value = (s.i() + 1) % s.options.length; b.setMessage(modeLabel(s));
                }).dimensions(cx, cy, w, h).build());
            } else {
                addDrawableChild(new SettingSlider(cx, cy, w, h, s));
            }
        }
        addDrawableChild(ButtonWidget.builder(Text.literal("Done"), b -> close())
            .dimensions(width / 2 - 50, height - 28, 100, 20).build());
    }

    @Override public void render(DrawContext ctx, int mx, int my, float delta) {
        super.render(ctx, mx, my, delta);
        ctx.drawCenteredTextWithShadow(textRenderer, "§6§l" + module.name, width / 2, 20, 0xFFFFFF);
        ctx.drawCenteredTextWithShadow(textRenderer, module.description, width / 2, 32, 0xAAAAAA);
    }

    @Override public void close() { client.setScreen(parent); }
    @Override public void removed() { Config.save(); }
}
