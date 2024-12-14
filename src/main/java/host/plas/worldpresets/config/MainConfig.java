package host.plas.worldpresets.config;

import host.plas.worldpresets.WorldPresets;
import org.bukkit.Material;
import tv.quaint.storage.resources.flat.simple.SimpleConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class MainConfig extends SimpleConfiguration {
    public MainConfig() {
        super("config.yml", WorldPresets.getInstance(), true);
    }

    @Override
    public void init() {

    }
}
