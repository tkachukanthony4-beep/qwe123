package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.KeyBinding;
public class KeystrokesModule extends Module {
    public KeystrokesModule() { super("Keystrokes", "WASD + mouse overlay", true, 6, 60); }
    private void key(DrawContext ctx, int kx, int ky, int w, String text, KeyBinding k) {
        ctx.fill(kx, ky, kx + w, ky + 20, k.isPressed() ? 0xB0FFFFFF : 0x90000000);
        int tw = mc.textRenderer.getWidth(text);
        ctx.drawText(mc.textRenderer, text, kx + (w - tw) / 2, ky + 6, k.isPressed() ? 0xFF000000 : 0xFFFFFFFF, false);
    }
    @Override public void onHud(DrawContext ctx) {
        var o = mc.options;
        key(ctx, x + 22, y, 20, "W", o.forwardKey);
        key(ctx, x, y + 22, 20, "A", o.leftKey);
        key(ctx, x + 22, y + 22, 20, "S", o.backKey);
        key(ctx, x + 44, y + 22, 20, "D", o.rightKey);
        key(ctx, x, y + 44, 31, "LMB", o.attackKey);
        key(ctx, x + 33, y + 44, 31, "RMB", o.useKey);
        key(ctx, x, y + 66, 64, "SPACE", o.jumpKey);
    }
}
