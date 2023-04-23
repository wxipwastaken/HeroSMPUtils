package network.geode.commands;

import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;

@SuppressWarnings("unused")
public class GuletCommand {
    @Command("gulet")
    public void onCommand(Player player) {
        player.performCommand("lay");
    }
}
