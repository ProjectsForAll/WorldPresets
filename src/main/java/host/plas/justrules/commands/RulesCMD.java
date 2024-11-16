package host.plas.justrules.commands;

import host.plas.bou.commands.CommandContext;
import host.plas.bou.commands.SimplifiedCommand;
import host.plas.justrules.JustRules;
import host.plas.justrules.gui.RulesGui;
import org.bukkit.entity.Player;

public class RulesCMD extends SimplifiedCommand {
    public RulesCMD() {
        super("rules", JustRules.getInstance());
    }

    @Override
    public boolean command(CommandContext commandContext) {
        if (commandContext.isConsole()) {
            commandContext.sendMessage("You must be a player to use this command!");
            return false;
        }
        try {
            Player player = commandContext.getPlayer().get();
            new RulesGui(player).open();
        } catch (Throwable e) {
            JustRules.getInstance().logWarning("Failed to get player from command context!", e);
            return false;
        }

        return true;
    }
}
