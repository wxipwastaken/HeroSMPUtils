package network.geode.events;

import me.mattstudios.config.SettingsManager;
import network.geode.Config;
import network.geode.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings("unused")
public class LeaveEvent implements Listener {
    private final SettingsManager settingsManager;
    private final MessageUtils messageUtils;

    public LeaveEvent(SettingsManager settingsManager, MessageUtils messageUtils) {
        this.settingsManager = settingsManager;
        this.messageUtils = messageUtils;
    }

    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent event) {
        event.quitMessage(null);

        String message = settingsManager.get(Config.MESSAGE_PLAYER_LEAVE)
                .replaceAll("%player%", event.getPlayer().getName());

        Bukkit.getOnlinePlayers().forEach(player -> messageUtils.sendPlayer(player, message));
        messageUtils.console(message, false);
    }
}
