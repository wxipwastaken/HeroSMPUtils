package network.geode.events;

import network.geode.utils.RuntimeStorage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

@SuppressWarnings("unused")
public class BlockEvent implements Listener {
    @EventHandler
    public void onBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!RuntimeStorage.hasPlayerPlacedGlowstick(player)) return;

        event.setCancelled(true);
        RuntimeStorage.removePlayerPlacedGlowstick(player);
    }
}
