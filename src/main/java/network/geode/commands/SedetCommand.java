package network.geode.commands;

import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;

@SuppressWarnings("unused")
public class SedetCommand {
    @Command("sedet")
    public void onCommand(Player player) {
        player.performCommand("sit");
    }
}
