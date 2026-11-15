package com.donutvisual.modules;
import com.donutvisual.Module;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
public class FullbrightModule extends Module {
    public FullbrightModule() { super("Fullbright", "See clearly in dark areas (client-side night vision)", false, 0, 0); }
    @Override public void onTick() {
        var p = mc.player;
        var cur = p.getStatusEffect(StatusEffects.NIGHT_VISION);
        if (cur == null || cur.getDuration() < 600)
            p.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1000, 0, false, false, false));
    }
    @Override public void onDisable() { if (mc.player != null) mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION); }
}
