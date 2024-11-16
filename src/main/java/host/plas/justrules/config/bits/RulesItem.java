package host.plas.justrules.config.bits;

import host.plas.bou.compat.CompatManager;
import host.plas.bou.compat.CompatibilityManager;
import host.plas.bou.compat.papi.PAPIHeld;
import host.plas.bou.utils.ColorUtils;
import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tv.quaint.objects.Identifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

@Getter @Setter
public class RulesItem implements Identifiable {
    private String identifier;

    private Material material;
    private int slot;
    private boolean glowing;
    private String title;
    private ConcurrentSkipListMap<Integer, String> rules;

    public RulesItem(String identifier, Material material, boolean glowing, String title, ConcurrentSkipListMap<Integer, String> rules, int slot) {
        this.identifier = identifier;
        this.material = material;
        this.glowing = glowing;
        this.title = title;
        this.rules = rules;
        this.slot = slot;
    }

    public ItemStack get(OfflinePlayer viewer) {
        ItemStack stack = new ItemStack(getMaterial());
        if (isGlowing()) {
            stack.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        }
        ItemMeta meta = stack.getItemMeta();
        if (meta != null) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            meta.setDisplayName(deserializeDisplayName(viewer));

            meta.setLore(deserializeLore(viewer));

            stack.setItemMeta(meta);
        }

        return stack;
    }

    public String deserializeDisplayName(OfflinePlayer viewer) {
        return ColorUtils.colorizeHard(getWithPAPI(viewer, getTitle()));
    }

    public List<String> deserializeLore(OfflinePlayer viewer) {
        List<String> lore = new ArrayList<>();

        for (int i : getRules().keySet()) {
            lore.add(ColorUtils.colorizeHard(getWithPAPI(viewer, getRules().get(i))));
        }

        return lore;
    }

    public String getWithPAPI(OfflinePlayer viewer, String input) {
        if (CompatManager.isEnabled(CompatManager.PAPI_IDENTIFIER)) {
            PAPIHeld papi = (PAPIHeld) CompatibilityManager.getHolder(CompatManager.PAPI_IDENTIFIER);
            if (papi.isEnabled()) {
                return PlaceholderAPI.setPlaceholders(viewer, input);
            }
        }

        return input;
    }
}
