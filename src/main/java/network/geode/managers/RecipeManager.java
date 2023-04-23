package network.geode.managers;

import me.MrGraycat.eGlow.API.Enum.EGlowColor;
import me.mattstudios.config.SettingsManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import network.geode.Config;
import network.geode.HeroSMPUtils;
import network.geode.enums.GlowColors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.EnumSet;
import java.util.HashMap;

@SuppressWarnings("unused")
public class RecipeManager {
    private final HashMap<GlowColors, ItemStack> recipe_results = new HashMap<>();
    private final HashMap<GlowColors, NamespacedKey> recipe_namespaces = new HashMap<>();
    private final EnumSet<GlowColors> allowed_colors = EnumSet.of(
            GlowColors.RED,
            GlowColors.YELLOW,
            GlowColors.GREEN,
            GlowColors.BLUE,
            GlowColors.PURPLE,
            GlowColors.PINK,
            GlowColors.WHITE,
            GlowColors.GRAY,
            GlowColors.BLACK
    );

    private final HeroSMPUtils plugin;
    private final MiniMessage miniMessage;
    private final SettingsManager settingsManager;

    public RecipeManager(HeroSMPUtils plugin, MiniMessage miniMessage, SettingsManager settingsManager) {
        this.plugin = plugin;
        this.miniMessage = miniMessage;
        this.settingsManager = settingsManager;
    }

    public void registerRecipes() {
        allowed_colors.forEach(color -> {
            final NamespacedKey namespacedKey = new NamespacedKey(plugin, "glow_recipe_" + color.toString().toLowerCase());
            ItemStack itemStack;

            switch (color) {
                case RED -> itemStack = new ItemStack(Material.RED_WOOL);
                case YELLOW -> itemStack = new ItemStack(Material.YELLOW_WOOL);
                case GREEN -> itemStack = new ItemStack(Material.GREEN_WOOL);
                case BLUE -> itemStack = new ItemStack(Material.BLUE_WOOL);
                case PURPLE -> itemStack = new ItemStack(Material.PURPLE_WOOL);
                case PINK -> itemStack = new ItemStack(Material.PINK_WOOL);
                case WHITE -> itemStack = new ItemStack(Material.WHITE_WOOL);
                case GRAY -> itemStack = new ItemStack(Material.GRAY_WOOL);
                case BLACK -> itemStack = new ItemStack(Material.BLACK_WOOL);
                default -> itemStack = null;
            }

            if (itemStack == null) return;
            ItemMeta itemMeta = itemStack.getItemMeta();

            itemMeta.addEnchant(Enchantment.LUCK, 10, true);
            itemMeta.addItemFlags(ItemFlag.values());
            itemMeta.displayName(miniMessage.deserialize(
                    settingsManager.get(Config.COLOR_DISPLAYNAME)
                            .replace("%color%", getColor(color))
            ));

            itemStack.setItemMeta(itemMeta);

            recipe_results.put(color, itemStack);
            recipe_namespaces.put(color, namespacedKey);

            ShapedRecipe shapedRecipe = new ShapedRecipe(namespacedKey, itemStack);
            shapedRecipe.shape("GGG", "OSO", "OCO");

            shapedRecipe.setIngredient('G', Material.GLASS);
            shapedRecipe.setIngredient('O', Material.OBSIDIAN);
            shapedRecipe.setIngredient('S', Material.SPECTRAL_ARROW);

            switch (color) {
                case RED -> shapedRecipe.setIngredient('C', Material.RED_DYE);
                case YELLOW -> shapedRecipe.setIngredient('C', Material.YELLOW_DYE);
                case GREEN -> shapedRecipe.setIngredient('C', Material.GREEN_DYE);
                case BLUE -> shapedRecipe.setIngredient('C', Material.BLUE_DYE);
                case PURPLE -> shapedRecipe.setIngredient('C', Material.PURPLE_DYE);
                case PINK -> shapedRecipe.setIngredient('C', Material.PINK_DYE);
                case WHITE -> shapedRecipe.setIngredient('C', Material.WHITE_DYE);
                case GRAY -> shapedRecipe.setIngredient('C', Material.GRAY_DYE);
                case BLACK -> shapedRecipe.setIngredient('C', Material.BLACK_DYE);
            }

            Bukkit.addRecipe(shapedRecipe);
        });
    }

    public NamespacedKey getNamespacedKey(GlowColors color) {
        return recipe_namespaces.get(color);
    }

    public ItemStack getItemStack(GlowColors color) {
        return recipe_results.get(color);
    }

    public EGlowColor getGlowColor(GlowColors color) {
        return switch (color) {
            case RED -> EGlowColor.RED;
            case YELLOW -> EGlowColor.YELLOW;
            case GREEN -> EGlowColor.GREEN;
            case BLUE -> EGlowColor.BLUE;
            case PURPLE -> EGlowColor.PURPLE;
            case PINK -> EGlowColor.PINK;
            case WHITE -> EGlowColor.WHITE;
            case GRAY -> EGlowColor.GRAY;
            case BLACK -> EGlowColor.BLACK;
        };
    }

    public String getColor(GlowColors color) {
        return switch (color) {
            case RED -> settingsManager.get(Config.COLOR_RED);
            case YELLOW -> settingsManager.get(Config.COLOR_YELLOW);
            case GREEN -> settingsManager.get(Config.COLOR_GREEN);
            case BLUE -> settingsManager.get(Config.COLOR_BLUE);
            case PURPLE -> settingsManager.get(Config.COLOR_PURPLE);
            case PINK -> settingsManager.get(Config.COLOR_PINK);
            case WHITE -> settingsManager.get(Config.COLOR_WHITE);
            case GRAY -> settingsManager.get(Config.COLOR_GRAY);
            case BLACK -> settingsManager.get(Config.COLOR_BLACK);
        };
    }

    public GlowColors getColorFromItem(Material item) {
        return switch (item) {
            case RED_WOOL -> GlowColors.RED;
            case YELLOW_WOOL -> GlowColors.YELLOW;
            case GREEN_WOOL -> GlowColors.GREEN;
            case BLUE_WOOL -> GlowColors.BLUE;
            case PURPLE_WOOL -> GlowColors.PURPLE;
            case PINK_WOOL -> GlowColors.PINK;
            case WHITE_WOOL -> GlowColors.WHITE;
            case GRAY_WOOL -> GlowColors.GRAY;
            case BLACK_WOOL -> GlowColors.BLACK;
            default -> null;
        };
    }
}
