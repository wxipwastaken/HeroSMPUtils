package network.geode.utils;

import me.mattstudios.config.SettingsManager;
import me.mattstudios.config.properties.Property;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import network.geode.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class MessageUtils {
    private final MiniMessage miniMessage;
    private final SettingsManager settingsManager;

    public MessageUtils(MiniMessage miniMessage, SettingsManager settingsManager) {
        this.miniMessage = miniMessage;
        this.settingsManager = settingsManager;
    }

    public void sendPlayer(Player player, Property<?> message) {
        sendPlayer(player, (String) settingsManager.get(message));
    }

    public void sendPlayer(Player player, String message) {
        player.sendMessage(miniMessage.deserialize(
                message.replaceAll("%prefix_format%", settingsManager.get(Config.PREFIX_FORMAT))
                        .replaceAll("%prefix%", settingsManager.get(Config.PREFIX))
        ));
    }

    public void console(Property<?> message) {
        console((String) settingsManager.get(message), true);
    }
    public void console(Property<?> message, boolean prefix) {
        console((String) settingsManager.get(message), prefix);
    }

    public void console(String message) {
        console(message, true);
    }

    public void console(String message, boolean prefix) {
        Bukkit.getConsoleSender().sendMessage(miniMessage.deserialize(
                (prefix)
                        ? settingsManager.get(Config.PREFIX) + message
                        : message
        ));
    }

    public void debug(Property<?> message) {
        debug((String) settingsManager.get(message));
    }

    public void debug(String message) {
        if (settingsManager.get(Config.DEBUG)) console(" [DEBUG] " + message);
    }

    public TextComponent color(String message) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(message);
    }
}
