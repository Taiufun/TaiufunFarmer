
package ru.taiufun.taiufunfarmer;

import ru.taiufun.taiufunfarmer.commands.FarmerCommand;
import ru.taiufun.taiufunfarmer.config.FarmerConfig;
import ru.taiufun.taiufunfarmer.listeners.FarmerPlaceListener;
import ru.taiufun.taiufunfarmer.listeners.FarmerUseListener;
import org.bukkit.plugin.java.JavaPlugin;

public class TaiufunFarmer extends JavaPlugin {
    private FarmerConfig farmerConfig;

    @Override
    public void onEnable() {

        this.farmerConfig = new FarmerConfig(this);

        getCommand("taiufunfarmer").setExecutor(new FarmerCommand(this, farmerConfig));
        getServer().getPluginManager().registerEvents(new FarmerUseListener(this, farmerConfig), this);
        getServer().getPluginManager().registerEvents(new FarmerPlaceListener(this, farmerConfig), this);

        getLogger().info("TaiufunFarmer enabled.");
    }


    @Override
    public void onDisable() {
        getLogger().info("TaiufunFarmer disabled.");
    }

}
