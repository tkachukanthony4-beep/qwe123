package com.donutvisual.modules;
import com.donutvisual.Module;
import com.donutvisual.Setting;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
public class BlockOverlayModule extends Module {
    public final Setting fill = add(Setting.bool("Fill", true));
    public final Setting alpha = add(new Setting("Fill opacity", 5, 60, 25));
    public final Setting outline = add(Setting.bool("Outline", true));
    public final Setting color = add(new Setting("Color", 0, "Rainbow", "Static"));
    public final Setting hue = add(new Setting("Hue", 0, 360, 200));
    public final Setting pulse = add(Setting.bool("Pulse", true));
    public final Setting hideVanilla = add(Setting.bool("Hide vanilla outline", true));

    public BlockOverlayModule() { super("BlockOverlay", "Coloured fill + outline on the block you're looking at", true, 0, 0); }

    public boolean hideVanilla() { return hideVanilla.b(); }

    public void render(WorldRenderContext ctx) {
        if (mc.world == null || ctx.matrixStack() == null || ctx.consumers() == null) return;
        if (!(mc.crosshairTarget instanceof BlockHitResult hit) || hit.getType() != HitResult.Type.BLOCK) return;
        BlockPos pos = hit.getBlockPos();
        BlockState st = mc.world.getBlockState(pos);
        VoxelShape shape = st.getOutlineShape(mc.world, pos);
        if (shape.isEmpty()) return;
        Vec3d cam = ctx.camera().getPos();
        Box box = shape.getBoundingBox().offset(pos).expand(0.002).offset(-cam.x, -cam.y, -cam.z);

        float h = color.i() == 0 ? rainbowHue(0) : hue.f();
        float[] c = rgb(h, 0.8f);
        float p = pulse.b() ? 0.75f + 0.25f * (float) Math.sin(System.currentTimeMillis() / 250.0) : 1f;

        if (fill.b())
            DebugRenderer.drawBox(ctx.matrixStack(), ctx.consumers(), box, c[0], c[1], c[2], alpha.f() / 100f * p);
        if (outline.b())
            VertexRendering.drawBox(ctx.matrixStack(), ctx.consumers().getBuffer(RenderLayer.getLines()), box, c[0], c[1], c[2], 1f);
    }
}
