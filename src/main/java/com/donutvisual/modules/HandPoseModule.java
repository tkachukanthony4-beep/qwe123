package com.donutvisual.modules;
import com.donutvisual.Module;
import com.donutvisual.Setting;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;
public class HandPoseModule extends Module {
    // x, y, z (hundredths of a block), rotX, rotY, rotZ (deg), scale (%)
    private static final float[][] PRESETS = {
        null,
        {0, -15, 0, 0, 0, 0, 100},     // Low
        {5, 0, 0, 10, -15, 15, 100},   // Tilted
        {10, 0, -30, 0, 0, 0, 90},     // Far
        {5, -12, -10, 0, 0, 0, 70}     // Compact
    };
    public final Setting preset = add(new Setting("Preset", 0, "Custom", "Low", "Tilted", "Far", "Compact"));
    public final Setting px = add(new Setting("Pos X", -60, 60, 0));
    public final Setting py = add(new Setting("Pos Y", -60, 60, 0));
    public final Setting pz = add(new Setting("Pos Z", -60, 60, 0));
    public final Setting rx = add(new Setting("Rot X", -90, 90, 0));
    public final Setting ry = add(new Setting("Rot Y", -90, 90, 0));
    public final Setting rz = add(new Setting("Rot Z", -90, 90, 0));
    public final Setting scale = add(new Setting("Scale %", 30, 150, 100));
    public final Setting offhand = add(Setting.bool("Affect offhand", true));

    public HandPoseModule() { super("HandPose", "Reposition, rotate and scale your first-person hands/items", false, 0, 0); }

    public void apply(MatrixStack m, boolean rightSide, boolean isOffhand) {
        if (isOffhand && !offhand.b()) return;
        float[] v = PRESETS[preset.i()];
        if (v == null) v = new float[]{px.f(), py.f(), pz.f(), rx.f(), ry.f(), rz.f(), scale.f()};
        float s = rightSide ? 1f : -1f;
        m.translate(s * v[0] / 100f, v[1] / 100f, v[2] / 100f);
        m.multiply(RotationAxis.POSITIVE_X.rotationDegrees(v[3]));
        m.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(s * v[4]));
        m.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(s * v[5]));
        float sc = v[6] / 100f;
        m.scale(sc, sc, sc);
    }
}
