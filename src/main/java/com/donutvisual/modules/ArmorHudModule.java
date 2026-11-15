package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
public class ArmorHudModule extends Module {
    private static final EquipmentSlot[] SLOTS = {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};
    public ArmorHudModule() { super("ArmorHUD", "Armor + hand durability", true, 0, 0); }
    @Override public void onHud(DrawContext ctx) {
        int sx = mc.getWindow().getScaledWidth() - 70;
        int sy = mc.getWindow().getScaledHeight() / 2 - 60;
        for (EquipmentSlot s : SLOTS) {
            ItemStack st = mc.player.getEquippedStack(s);
            if (st.isEmpty()) continue;
            ctx.drawItem(st, sx, sy);
            if (st.isDamageable()) {
                int left = st.getMaxDamage() - st.getDamage();
                float pct = left / (float) st.getMaxDamage();
                int col = pct > 0.5f ? 0xFF55FF55 : pct > 0.2f ? 0xFFFFFF55 : 0xFFFF5555;
                ctx.drawText(mc.textRenderer, String.valueOf(left), sx + 20, sy + 4, col, true);
            } else if (st.getCount() > 1) {
                ctx.drawText(mc.textRenderer, "x" + st.getCount(), sx + 20, sy + 4, 0xFFFFFFFF, true);
            }
            sy += 20;
        }
    }
}
