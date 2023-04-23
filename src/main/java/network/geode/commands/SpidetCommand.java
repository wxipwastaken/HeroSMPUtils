package network.geode.commands;

import me.MrGraycat.eGlow.API.EGlowAPI;
import me.MrGraycat.eGlow.EGlow;
import me.MrGraycat.eGlow.Manager.Interface.IEGlowPlayer;
import me.mattstudios.config.SettingsManager;
import network.geode.Config;
import network.geode.utils.MessageUtils;
import org.bukkit.entity.Player;
import revxrsal.commands.annotation.Command;

@SuppressWarnings("unused")
public class SpidetCommand {
    private final MessageUtils messageUtils;
    private final SettingsManager settingsManager;

    public SpidetCommand(MessageUtils messageUtils, SettingsManager settingsManager) {
        this.messageUtils = messageUtils;
        this.settingsManager = settingsManager;
    }

    @Command("spidet")
    public void onCommand(Player player) {
        EGlowAPI eGlowAPI = EGlow.getAPI();
        IEGlowPlayer glowPlayer = eGlowAPI.getEGlowPlayer(player);
        boolean isGlowing = glowPlayer.isGlowing();

        if (isGlowing) {
            glowPlayer.disableGlow(false);
            messageUtils.sendPlayer(player, settingsManager.get(Config.MESSAGE_TOGGLE_GLOW_OFF));
        } else {
            glowPlayer.activateGlow();
            messageUtils.sendPlayer(player, settingsManager.get(Config.MESSAGE_TOGGLE_GLOW_ON));
        }
    }
}
