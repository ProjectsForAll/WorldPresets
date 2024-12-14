package host.plas.worldpresets;

import host.plas.bou.BetterPlugin;
import host.plas.worldpresets.commands.WorldPresetsCMD;
import host.plas.worldpresets.config.MainConfig;
import host.plas.worldpresets.listener.MainListener;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public final class WorldPresets extends BetterPlugin {
    @Getter @Setter
    private static WorldPresets instance;
    @Getter @Setter
    private static MainConfig mainConfig;

    @Getter @Setter
    private static MainListener mainListener;

    @Getter @Setter
    private static WorldPresetsCMD worldPresetsCMD;

    public WorldPresets() {
        super();
    }

    @Override
    public void onBaseEnabled() {
        // Plugin startup logic
        setInstance(this);

        setMainConfig(new MainConfig());

        setWorldPresetsCMD(new WorldPresetsCMD());

        setMainListener(new MainListener());
    }

    @Override
    public void onBaseDisable() {
        // Plugin shutdown logic
    }
}
