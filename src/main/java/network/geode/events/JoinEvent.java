package network.geode.events;

import me.mattstudios.config.SettingsManager;
import network.geode.Config;
import network.geode.enums.GlowColors;
import network.geode.managers.RecipeManager;
import network.geode.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@SuppressWarnings("unused")
public class JoinEvent implements Listener {
    private final RecipeManager recipeManager;
    private final SettingsManager settingsManager;
    private final MessageUtils messageUtils;

    public JoinEvent(RecipeManager recipeManager, SettingsManager settingsManager, MessageUtils messageUtils) {
        this.recipeManager = recipeManager;
        this.settingsManager = settingsManager;
        this.messageUtils = messageUtils;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        event.joinMessage(null);
        Player player = event.getPlayer();

        String message = settingsManager.get(Config.MESSAGE_PLAYER_JOIN)
                .replaceAll("%player%", player.getName());

        Bukkit.getOnlinePlayers().forEach(pl -> messageUtils.sendPlayer(pl, message));
        messageUtils.console(message, false);

        for (GlowColors color : GlowColors.values()) {
            NamespacedKey namespacedKey = recipeManager.getNamespacedKey(color);

            if (player.hasDiscoveredRecipe(namespacedKey)) return;
            player.discoverRecipe(namespacedKey);
        }
    }
}
