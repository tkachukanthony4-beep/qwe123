package com.donutvisual;

import com.donutvisual.modules.BlockOverlayModule;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class DonutVisualClient implements ClientModInitializer {
    public static KeyBinding OPEN_GUI, ZOOM_KEY;

    @Override
    public void onInitializeClient() {
        OPEN_GUI = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.donutvisual.gui", GLFW.GLFW_KEY_RIGHT_SHIFT, "category.donutvisual"));
        ZOOM_KEY = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.donutvisual.zoom", GLFW.GLFW_KEY_C, "category.donutvisual"));
        ModuleManager.init();
        Config.load();

        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            while (OPEN_GUI.wasPressed()) mc.setScreen(new ModuleScreen());
            if (mc.player == null) return;
            for (Module m : ModuleManager.all()) if (m.isEnabled()) m.onTick();
        });

        HudRenderCallback.EVENT.register((ctx, tickCounter) -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player == null || mc.options.hudHidden || mc.getDebugHud().shouldShowDebugHud()) return;
            for (Module m : ModuleManager.all()) if (m.isEnabled()) m.onHud(ctx);
        });

        // Block overlay (world space)
        WorldRenderEvents.AFTER_TRANSLUCENT.register(ctx -> {
            BlockOverlayModule m = ModuleManager.get(BlockOverlayModule.class);
            if (m != null && m.isEnabled()) m.render(ctx);
        });
        WorldRenderEvents.BEFORE_BLOCK_OUTLINE.register((ctx, hit) -> {
            BlockOverlayModule m = ModuleManager.get(BlockOverlayModule.class);
            return !(m != null && m.isEnabled() && m.hideVanilla());
        });

        ClientLifecycleEvents.CLIENT_STOPPING.register(c -> Config.save());
    }
}
