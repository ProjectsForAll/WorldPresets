package host.plas.worldpresets.listener;

import host.plas.bou.events.ListenerConglomerate;
import host.plas.worldpresets.WorldPresets;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.WorldLoadEvent;
import tv.quaint.events.BaseEventHandler;

public class MainListener implements ListenerConglomerate {
    public MainListener() {
        Bukkit.getPluginManager().registerEvents(this, WorldPresets.getInstance());
        BaseEventHandler.bake(this, WorldPresets.getInstance());
    }

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) return;

        ThreadLocal<World> world = ThreadLocal.withInitial(entity::getWorld);
        if (world.get() == null) return;

        // do some checks...

        event.setCancelled(true);
    }
}
