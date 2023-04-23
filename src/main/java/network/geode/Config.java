package network.geode;

import me.mattstudios.config.SettingsHolder;
import me.mattstudios.config.annotations.Comment;
import me.mattstudios.config.annotations.Path;
import me.mattstudios.config.properties.Property;

public class Config implements SettingsHolder {
    @Comment({
            "You can add any of the 2 prefixes to",
            "any chat message by adding \"%prefix_format%\"",
            "or \"%prefix%\".",
            "",
            "!!! NOTE !!!",
            "ALL INTERNAL MESSAGES ARE USING \"messages.prefix\" PREFIX!"
    })
    @Path("messages.prefix_format")
    public static final Property<String> PREFIX_FORMAT = Property.create("<dark_gray>•");
    @Path("messages.prefix")
    public static final Property<String> PREFIX = Property.create("[HeroSMPUtils] ");
    @Path("messages.debug")
    public static final Property<Boolean> DEBUG = Property.create(false);

    // Internal Messages
    @Path("messages.internal.initializing_start")
    public static final Property<String> MESSAGE_INIT_START = Property.create("<gray>Initializing plugin...");
    @Path("messages.internal.initializing_done")
    public static final Property<String> MESSAGE_INIT_DONE = Property.create("<gray>Plugin initialized!");
    @Path("messages.internal.loading_managers")
    public static final Property<String> MESSAGE_LOADING_MANAGERS = Property.create("<gray>Loading managers...");
    @Path("messages.internal.loading_events")
    public static final Property<String> MESSAGE_LOADING_EVENTS = Property.create("<gray>Loading events...");
    @Path("messages.internal.registering_commands")
    public static final Property<String> MESSAGE_REGISTERING_COMMANDS = Property.create("<gray>Registering commands...");

    // Feature messages
    @Path("messages.toggle_glow.on")
    public static final Property<String> MESSAGE_TOGGLE_GLOW_ON = Property.create(" %prefix_format% <gray>Tu tagad spīdi");
    @Path("messages.toggle_glow.off")
    public static final Property<String> MESSAGE_TOGGLE_GLOW_OFF = Property.create(" %prefix_format% <gray>Tu vairs nespīdi");
    @Path("messages.player_join")
    public static final Property<String> MESSAGE_PLAYER_JOIN = Property.create("<green>+ <white>%player%");
    @Path("messages.player_leave")
    public static final Property<String> MESSAGE_PLAYER_LEAVE = Property.create("<red>- <white>%player%");
    @Path("messages.chat_format")
    public static final Property<String> MESSAGE_CHAT_FORMAT = Property.create("<gray>%player% <dark_gray>• <white>%message%");
    @Path("messages.in_slime_chunk")
    public static final Property<String> MESSAGE_IN_SLIME_CHUNK = Property.create(" %prefix_format% <gray>Tu <green>esi <gray>iekš slime-chunk");
    @Path("messages.not_in_slime_chunk")
    public static final Property<String> MESSAGE_NOT_IN_SLIME_CHUNK = Property.create(" %prefix_format% <gray>Tu <red>neesi <gray>iekš slime-chunk");

    @Path("colors.display_name")
    public static final Property<String> COLOR_DISPLAYNAME = Property.create("<!i>%color% <white>glowstick");
    @Path("colors.red")
    public static final Property<String> COLOR_RED = Property.create("<b><red>Red</red></b>");
    @Path("colors.yellow")
    public static final Property<String> COLOR_YELLOW = Property.create("<b><yellow>Yellow</yellow></b>");
    @Path("colors.green")
    public static final Property<String> COLOR_GREEN = Property.create("<b><green>Green</green></b>");
    @Path("colors.blue")
    public static final Property<String> COLOR_BLUE = Property.create("<b><blue>Blue</blue></b>");
    @Path("colors.purple")
    public static final Property<String> COLOR_PURPLE = Property.create("<b><dark_purple>Purple</dark_purple></b>");
    @Path("colors.pink")
    public static final Property<String> COLOR_PINK = Property.create("<b><light_purple>Pink</light_purple></b>");
    @Path("colors.white")
    public static final Property<String> COLOR_WHITE = Property.create("<b><white>White</white></b>");
    @Path("colors.gray")
    public static final Property<String> COLOR_GRAY = Property.create("<b><gray>Gray</gray></b>");
    @Path("colors.black")
    public static final Property<String> COLOR_BLACK = Property.create("<b><black>Black</black></b>");
}
