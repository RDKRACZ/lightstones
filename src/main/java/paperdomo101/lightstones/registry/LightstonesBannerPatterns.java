package paperdomo101.lightstones.registry;

import net.minecraft.util.registry.Registry;
import paperdomo101.lightstones.Lightstones;
import paperdomo101.lightstones.api.banner.loom.LoomPattern;
import paperdomo101.lightstones.api.banner.loom.LoomPatterns;

public class LightstonesBannerPatterns {

    public static final LoomPattern SKRALL = new LoomPattern(true);
    public static final LoomPattern VIRTUES = new LoomPattern(true);
    public static final LoomPattern RAHKSHI = new LoomPattern(true);

    public static void init() {
        Registry.register(LoomPatterns.REGISTRY, Lightstones.id("skrall"), SKRALL);
        Registry.register(LoomPatterns.REGISTRY, Lightstones.id("virtues"), VIRTUES);
        Registry.register(LoomPatterns.REGISTRY, Lightstones.id("rahkshi"), RAHKSHI);
    }
}
