package host.plas.justrules;

import host.plas.bou.BetterPlugin;
import host.plas.justrules.commands.RulesCMD;
import host.plas.justrules.config.MainConfig;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public final class JustRules extends BetterPlugin {
    @Getter @Setter
    private static JustRules instance;
    @Getter @Setter
    private static MainConfig mainConfig;

    @Getter @Setter
    private static RulesCMD rulesCMD;

    public JustRules() {
        super();
    }

    @Override
    public void onBaseEnabled() {
        // Plugin startup logic
        setInstance(this);

        setMainConfig(new MainConfig());

        setRulesCMD(new RulesCMD());
    }

    @Override
    public void onBaseDisable() {
        // Plugin shutdown logic
    }
}
