package ru.taiufun.taiufunfarmer.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import ru.taiufun.taiufunfarmer.TaiufunFarmer;
import ru.taiufun.taiufunfarmer.config.FarmerConfig;
import ru.taiufun.taiufunfarmer.utils.HeadUtils;

public class FarmerPlaceListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    private void onBlockPlace(BlockPlaceEvent event) {
        if (HeadUtils.isFarmerHead(event.getItemInHand())) event.setCancelled(true);
    }
}
