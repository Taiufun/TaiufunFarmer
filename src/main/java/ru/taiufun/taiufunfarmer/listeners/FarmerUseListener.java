package ru.taiufun.taiufunfarmer.listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import ru.taiufun.taiufunfarmer.TaiufunFarmer;
import ru.taiufun.taiufunfarmer.config.FarmerConfig;
import ru.taiufun.taiufunfarmer.utils.CooldownUtil;
import ru.taiufun.taiufunfarmer.utils.HeadUtils;
import ru.taiufun.taiufunfarmer.utils.PlantUtils;


public class FarmerUseListener implements Listener {

    private final TaiufunFarmer plugin;
    private final FarmerConfig farmerConfig;

    public FarmerUseListener(TaiufunFarmer plugin, FarmerConfig farmerConfig) {
        this.plugin = plugin;
        this.farmerConfig = farmerConfig;
    }

    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        final String COOLDOWN_KEY = "growPlants";
        Player player = event.getPlayer();

        if (!event.isSneaking()) return;

        ItemStack offHandItem = player.getInventory().getItemInOffHand();

        if (!HeadUtils.isFarmerHead(plugin, offHandItem)) {
            return;
        }

        // Проверка пермишена обхода кулдауна
        if (!player.hasPermission("taiufunfarmer.bypasscooldown")) {
            if (CooldownUtil.hasCooldown(player, COOLDOWN_KEY)) {
                long leftMillis = CooldownUtil.getCooldownLeft(player, COOLDOWN_KEY);
                long leftSeconds = (leftMillis + 999) / 1000;
                String rawMessage = farmerConfig.getCooldownWaitMessage();
                String formattedMessage = rawMessage.replace("{time}", String.valueOf(leftSeconds));
                player.sendMessage(formattedMessage);
                return;
            }
        }

        Block blockUnder = player.getLocation().subtract(0, 1, 0).getBlock();

        int radius = farmerConfig.getGrowRadius();
        PlantUtils.growPlantsAround(blockUnder, radius);

        int cooldownSeconds = farmerConfig.getGrowCooldownSeconds();
        CooldownUtil.startCooldown(player, COOLDOWN_KEY, cooldownSeconds * 1000L);
    }


}
