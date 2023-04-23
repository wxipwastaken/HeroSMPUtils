package network.geode.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RuntimeStorage {
    private static final List<UUID> placedGlowstick = new ArrayList<>();

    public static void addPlayerPlacedGlowstick(Player player) {
        addPlayerPlacedGlowstick(player.getUniqueId());
    }

    public static void addPlayerPlacedGlowstick(UUID player) {
        placedGlowstick.add(player);
    }

    public static void removePlayerPlacedGlowstick(Player player) {
        removePlayerPlacedGlowstick(player.getUniqueId());
    }

    public static void removePlayerPlacedGlowstick(UUID player) {
        placedGlowstick.remove(player);
    }

    public static boolean hasPlayerPlacedGlowstick(Player player) {
        return hasPlayerPlacedGlowstick(player.getUniqueId());
    }

    public static boolean hasPlayerPlacedGlowstick(UUID player) {
        return placedGlowstick.contains(player);
    }
}
