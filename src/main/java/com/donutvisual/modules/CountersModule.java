package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
public class CountersModule extends Module {
    private static final Item[] ITEMS = {Items.TOTEM_OF_UNDYING, Items.GOLDEN_APPLE, Items.ENDER_PEARL, Items.EXPERIENCE_BOTTLE};
    public CountersModule() { super("ItemCounters", "Totems, gapples, pearls, XP bottles beside the hotbar", true, 0, 0); }
    private int count(Item item) {
        var inv = mc.player.getInventory();
        int n = 0;
        for (ItemStack s : inv.main) if (s.isOf(item)) n += s.getCount();
        for (ItemStack s : inv.offHand) if (s.isOf(item)) n += s.getCount();
        return n;
    }
    @Override public void onHud(DrawContext ctx) {
        int sx = mc.getWindow().getScaledWidth() / 2 + 100;
        int sy = mc.getWindow().getScaledHeight() - 20;
        for (Item it : ITEMS) {
            int c = count(it);
            ctx.drawItem(new ItemStack(it), sx, sy);
            String t = String.valueOf(c);
            ctx.drawText(mc.textRenderer, t, sx + 16 - mc.textRenderer.getWidth(t), sy + 9, c == 0 ? 0xFFFF5555 : 0xFFFFFFFF, true);
            sx += 20;
        }
    }
}
