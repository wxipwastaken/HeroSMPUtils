package network.geode.events;

import me.MrGraycat.eGlow.API.EGlowAPI;
import me.MrGraycat.eGlow.EGlow;
import network.geode.enums.GlowColors;
import network.geode.managers.RecipeManager;
import network.geode.utils.RuntimeStorage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.EnumSet;

@SuppressWarnings("unused")
public class InteractEvent implements Listener {
    private final RecipeManager recipeManager;

    private final EnumSet<Material> wool = EnumSet.of(
            Material.RED_WOOL,
            Material.YELLOW_WOOL,
            Material.GREEN_WOOL,
            Material.BLUE_WOOL,
            Material.PURPLE_WOOL,
            Material.PINK_WOOL,
            Material.WHITE_WOOL,
            Material.GRAY_WOOL,
            Material.BLACK_WOOL
    );

    public InteractEvent(RecipeManager recipeManager) {
        this.recipeManager = recipeManager;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        EGlowAPI eGlowAPI = EGlow.getAPI();
        ItemStack item = event.getItem();

        if (item == null) return;
        if (!wool.contains(item.getType())) return;
        if (!item.getItemMeta().hasEnchant(Enchantment.LUCK)) return;

        event.setCancelled(true);

        GlowColors color = recipeManager.getColorFromItem(item.getType());
        RuntimeStorage.addPlayerPlacedGlowstick(player);

        eGlowAPI.enableGlow(
                eGlowAPI.getEGlowPlayer(player),
                recipeManager.getGlowColor(color)
        );

        if (item.getAmount() == 1) {
            player.getInventory().remove(item);
        } else item.setAmount(item.getAmount() - 1);
    }
}
