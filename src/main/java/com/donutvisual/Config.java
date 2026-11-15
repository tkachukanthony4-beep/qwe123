package com.donutvisual;

import net.fabricmc.loader.api.FabricLoader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Config {
    private static Path path() { return FabricLoader.getInstance().getConfigDir().resolve("donutvisual.properties"); }

    public static void save() {
        Properties p = new Properties();
        for (Module m : ModuleManager.all()) {
            p.setProperty(m.name + ".enabled", String.valueOf(m.isEnabled()));
            for (Setting s : m.settings) p.setProperty(m.name + "." + s.name, String.valueOf(s.value));
        }
        try (var w = Files.newBufferedWriter(path())) { p.store(w, "Donut Visual Client"); } catch (IOException ignored) {}
    }

    public static void load() {
        Path f = path();
        if (!Files.exists(f)) return;
        Properties p = new Properties();
        try (var r = Files.newBufferedReader(f)) { p.load(r); } catch (IOException e) { return; }
        for (Module m : ModuleManager.all()) {
            String en = p.getProperty(m.name + ".enabled");
            if (en != null) m.setEnabled(Boolean.parseBoolean(en));
            for (Setting s : m.settings) {
                String v = p.getProperty(m.name + "." + s.name);
                if (v == null) continue;
                try { s.value = Math.max(s.min, Math.min(s.max, Float.parseFloat(v))); } catch (NumberFormatException ignored) {}
            }
        }
    }
}
