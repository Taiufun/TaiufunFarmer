package ru.taiufun.taiufunfarmer.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import ru.taiufun.taiufunfarmer.TaiufunFarmer;
import ru.taiufun.taiufunfarmer.config.FarmerConfig;
import ru.taiufun.taiufunfarmer.utils.HeadUtils;

public class FarmerPlaceListener implements Listener {

    private final TaiufunFarmer plugin;


    public FarmerPlaceListener(TaiufunFarmer plugin, FarmerConfig farmerConfig) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();

        if (HeadUtils.isFarmerHead(plugin, item)) {
            event.setCancelled(true);
        }
    }
}
