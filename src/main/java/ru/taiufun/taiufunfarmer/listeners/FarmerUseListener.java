package ru.taiufun.taiufunfarmer.listeners;

import lombok.AllArgsConstructor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import ru.taiufun.taiufunfarmer.config.FarmerConfig;
import ru.taiufun.taiufunfarmer.utils.CooldownUtil;
import ru.taiufun.taiufunfarmer.utils.HeadUtils;
import ru.taiufun.taiufunfarmer.utils.PlantUtils;

@AllArgsConstructor
public class FarmerUseListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    private void on(PlayerToggleSneakEvent event) {
        final Player player = event.getPlayer();

        if (!event.isSneaking()) return;
        if (!HeadUtils.isFarmerHead(player.getInventory().getItemInOffHand())) return;

        if (!player.hasPermission("taiufunfarmer.bypasscooldown") && CooldownUtil.hasCooldown(player)) {
            final long leftMillis = CooldownUtil.getCooldownLeft(player);
            final long secondsLeft = (leftMillis + 999) / 1000;

            final String message = FarmerConfig.cooldownWaitMessage.replace("{time}", String.valueOf(secondsLeft));

            player.sendMessage(message);
            return;
        }

        final Block blockBelowPlayer = player.getLocation().subtract(0, 1, 0).getBlock();
        PlantUtils.growPlantsAround(blockBelowPlayer, FarmerConfig.FARMER.growRadius);

        CooldownUtil.startCooldown(player, FarmerConfig.FARMER.growCooldownSeconds * 1000L);
    }
}
