package host.plas.justrules.config;

import host.plas.justrules.JustRules;
import host.plas.justrules.config.bits.RulesItem;
import org.bukkit.Material;
import tv.quaint.storage.resources.flat.simple.SimpleConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class MainConfig extends SimpleConfiguration {
    public MainConfig() {
        super("config.yml", JustRules.getInstance(), true);
    }

    @Override
    public void init() {
        getMenuTitle();
        getMenuSize();
        getRulesItems();
    }

    public String getMenuTitle() {
        reloadResource();

        return getOrSetDefault("menu.title", "Server Rules");
    }

    public int getMenuSize() {
        reloadResource();

        return getOrSetDefault("menu.size", 27);
    }

    public ConcurrentSkipListSet<RulesItem> getRulesItems() {
        reloadResource();

        ConcurrentSkipListSet<RulesItem> items = new ConcurrentSkipListSet<>();

        singleLayerKeySet("items").forEach(key -> {
            try {
                String path = "items." + key;

                String material = getOrSetDefault(path + ".material", "KNOWLEDGE_BOOK");
                boolean glowing = getOrSetDefault(path + ".glowing", false);
                String title = getOrSetDefault(path + ".title", "Rule Title");
                int slot = getOrSetDefault(path + ".slot", 0);

                Material materialType = Material.valueOf(material);

                ConcurrentSkipListMap<Integer, String> rules = new ConcurrentSkipListMap<>();

                List<String> rulesList = getOrSetDefault(path + ".lore", new ArrayList<>(List.of("Rule 1", "Rule 2")));
                for (int i = 0; i < rulesList.size(); i++) {
                    rules.put(i, rulesList.get(i));
                }

                RulesItem item = new RulesItem(key, materialType, glowing, title, rules, slot);

                items.add(item);
            } catch (Throwable e) {
                JustRules.getInstance().logWarning("Failed to load rules item: " + key, e);
            }
        });

        return items;
    }
}
