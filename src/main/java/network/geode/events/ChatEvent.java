package network.geode.events;

import io.papermc.paper.event.player.AsyncChatEvent;
import me.mattstudios.config.SettingsManager;
import net.kyori.adventure.text.TextComponent;
import network.geode.Config;
import network.geode.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@SuppressWarnings("unused")
public class ChatEvent implements Listener {
    private final SettingsManager settingsManager;
    private final MessageUtils messageUtils;

    public ChatEvent(SettingsManager settingsManager, MessageUtils messageUtils) {
        this.settingsManager = settingsManager;
        this.messageUtils = messageUtils;
    }

    @EventHandler
    public void onAsyncChatEvent(AsyncChatEvent event) {
        Player player = event.getPlayer();
        TextComponent message = (TextComponent) event.message();

        String sendMessage = settingsManager.get(Config.MESSAGE_CHAT_FORMAT)
                        .replaceAll("%player%", player.getName())
                        .replaceAll("%message%", messageUtils.color(message.content()).content());

        Bukkit.getOnlinePlayers().forEach(pl -> messageUtils.sendPlayer(pl, sendMessage));
        messageUtils.console(sendMessage, false);

        event.setCancelled(true);
    }
}
