package network.geode;

import io.papermc.lib.PaperLib;
import me.mattstudios.config.SettingsManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import network.geode.commands.*;
import network.geode.events.*;
import network.geode.managers.RecipeManager;
import network.geode.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.bukkit.BukkitCommandHandler;

import java.io.File;
import java.util.logging.Level;

public class HeroSMPUtils extends JavaPlugin {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final SettingsManager settingsManager = SettingsManager
            .from(new File("plugins/" + getName() + "/config.yml"))
            .configurationData(Config.class)
            .create();
    private final MessageUtils messageUtils = new MessageUtils(miniMessage, settingsManager);

    private BukkitCommandHandler commandManager;
    private PluginManager pluginManager;
    private RecipeManager recipeManager;

    @Override
    public void onEnable() {
        messageUtils.console(settingsManager.get(Config.MESSAGE_INIT_START));

        PaperLib.suggestPaper(this, Level.SEVERE);
        if (!PaperLib.isPaper()) pluginManager.disablePlugin(this);

        messageUtils.console(settingsManager.get(Config.MESSAGE_LOADING_MANAGERS));
        loadManagers();

        messageUtils.console(settingsManager.get(Config.MESSAGE_LOADING_EVENTS));
        loadEvents();

        messageUtils.console(settingsManager.get(Config.MESSAGE_REGISTERING_COMMANDS));
        registerCommands();

        messageUtils.console(settingsManager.get(Config.MESSAGE_INIT_DONE));
    }

    private void loadManagers() {
        commandManager = BukkitCommandHandler.create(this);
        pluginManager = Bukkit.getPluginManager();
        recipeManager = new RecipeManager(this, miniMessage, settingsManager);
        recipeManager.registerRecipes();
    }

    private void loadEvents() {
        pluginManager.registerEvents(new BlockEvent(), this);
        pluginManager.registerEvents(new ChatEvent(settingsManager, messageUtils), this);
        pluginManager.registerEvents(new InteractEvent(recipeManager), this);
        pluginManager.registerEvents(new JoinEvent(recipeManager, settingsManager, messageUtils), this);
        pluginManager.registerEvents(new LeaveEvent(settingsManager, messageUtils), this);
    }

    private void registerCommands() {
        commandManager.register(new GuletCommand());
        commandManager.register(new RapotCommand());
        commandManager.register(new SedetCommand());
        commandManager.register(new SlimeCommand(messageUtils));
        commandManager.register(new SpidetCommand(messageUtils, settingsManager));
    }
}
