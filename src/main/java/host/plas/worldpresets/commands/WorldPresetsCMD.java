package host.plas.worldpresets.commands;

import host.plas.bou.commands.CommandContext;
import host.plas.bou.commands.SimplifiedCommand;
import host.plas.worldpresets.WorldPresets;

public class WorldPresetsCMD extends SimplifiedCommand {
    public WorldPresetsCMD() {
        super("worldpresets", WorldPresets.getInstance());
    }

    @Override
    public boolean command(CommandContext commandContext) {
        return false;
    }
}
