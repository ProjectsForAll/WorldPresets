package host.plas.justrules.gui;

import host.plas.bou.gui.GuiType;
import host.plas.justrules.JustRules;
import lombok.Getter;

@Getter
public enum RulesGuiType implements GuiType {
    MAIN("main")
    ;
    
    private final String identifier;
    
    RulesGuiType(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getTitle() {
        // TODO: check the identifier
        return JustRules.getMainConfig().getMenuTitle();
    }
}
