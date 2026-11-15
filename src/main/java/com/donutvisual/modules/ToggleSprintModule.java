package com.donutvisual.modules;
import com.donutvisual.Module;
public class ToggleSprintModule extends Module {
    public ToggleSprintModule() { super("ToggleSprint", "Auto-sprint while moving forward", false, 0, 0); }
    @Override public void onTick() {
        var p = mc.player;
        if (p.input.movementForward > 0 && !p.isSneaking() && !p.horizontalCollision
                && !p.isUsingItem() && p.getHungerManager().getFoodLevel() > 6)
            p.setSprinting(true);
    }
}
