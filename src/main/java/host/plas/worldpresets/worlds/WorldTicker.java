package host.plas.worldpresets.worlds;

import host.plas.bou.scheduling.BaseRunnable;

public class WorldTicker extends BaseRunnable {
    public WorldTicker() {
        super(0, 10);
    }

    @Override
    public void run() {
        WorldManager.tickWorlds();
    }
}
