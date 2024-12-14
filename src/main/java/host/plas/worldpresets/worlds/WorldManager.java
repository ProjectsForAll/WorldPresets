package host.plas.worldpresets.worlds;

import host.plas.bou.scheduling.TaskManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;

import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.Consumer;

public class WorldManager {
    @Getter @Setter
    private static ConcurrentSkipListMap<String, ThreadLocal<World>> loadedWorlds = new ConcurrentSkipListMap<>();

    public static void addWorld(World world) {
        loadedWorlds.put(world.getName(), ThreadLocal.withInitial(() -> world));
    }

    public static void removeWorld(String name) {
        getLoadedWorlds().forEach((key, value) -> {
            withWorld(value.get(), world -> {
                if (world == null) return;

                if (world.getName().equals(name)) {
                    loadedWorlds.remove(key);
                }
            });
        });
    }

    public static void removeWorld(World world) {
        removeWorld(world.getName());
    }

    public static Optional<World> getWorld(String name) {
        return Optional.ofNullable(loadedWorlds.get(name).get());
    }

    public static Optional<World> getWorld(World world) {
        return getWorld(world.getName());
    }

    public static boolean hasWorld(String name) {
        return getWorld(name).isPresent();
    }

    public static boolean hasWorld(World world) {
        return hasWorld(world.getName());
    }

    public static void withWorld(World world, Consumer<World> consumer) {
        consumer.accept(world);
    }

    public static void handleWorld(World world) {
        if (hasWorld(world.getName())) return;

        setWorldPresets(world);
    }

    public static void setWorldPresets(World world) {
        if (! TaskManager.isThreadSync()) {
            TaskManager.runTask(() -> setWorldPresets(world));
            return;
        }

        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
//        world.setGameRule(GameRule.DO_FIRE_TICK, false);
//        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);

        addWorld(world);
    }

    public static void tickWorlds() {
        pruneLoadedWorlds();

        if (Bukkit.getWorlds().size() != getLoadedWorlds().size()) {
            Bukkit.getWorlds().forEach(WorldManager::handleWorld);
        }
    }

    public static void pruneLoadedWorlds() {
        getLoadedWorlds().forEach((key, value) -> {
            withWorld(value.get(), world -> {
                try {
                    if (world == null) {
                        loadedWorlds.remove(key);
                        return;
                    }
                    if (Bukkit.getWorld(world.getUID()) == null) {
                        loadedWorlds.remove(key);
                    }
                } catch (Throwable e) {
                    // ignore
                }
            });
        });
    }
}
