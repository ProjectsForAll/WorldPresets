package host.plas.justrules.gui;

import host.plas.bou.gui.InventorySheet;
import host.plas.bou.gui.icons.BasicIcon;
import host.plas.bou.gui.screens.ScreenInstance;
import host.plas.justrules.JustRules;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RulesGui extends ScreenInstance {
    public RulesGui(@NotNull Player player) {
        super(player, RulesGuiType.MAIN, buildInventorySheet(player));
    }

    public static InventorySheet buildInventorySheet(Player player) {
        InventorySheet sheet = new InventorySheet(JustRules.getMainConfig().getMenuSize());

        JustRules.getMainConfig().getRulesItems().forEach(rulesItem -> {
            BasicIcon icon = new BasicIcon(rulesItem.get(player));
            sheet.addIcon(rulesItem.getSlot(), icon);
        });

        return sheet;
    }
}
