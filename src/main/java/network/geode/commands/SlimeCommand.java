package network.geode.commands;

import network.geode.Config;
import network.geode.utils.MessageUtils;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;

@SuppressWarnings("unused")
public class SlimeCommand {
    private final MessageUtils messageUtils;

    public SlimeCommand(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    @Command("slime")
    public void onCommand(Player player) {
        boolean isSlimeChunk = player.getLocation().getChunk().isSlimeChunk();

        if (isSlimeChunk) messageUtils.sendPlayer(player, Config.MESSAGE_IN_SLIME_CHUNK);
        else messageUtils.sendPlayer(player, Config.MESSAGE_NOT_IN_SLIME_CHUNK);
    }
}
