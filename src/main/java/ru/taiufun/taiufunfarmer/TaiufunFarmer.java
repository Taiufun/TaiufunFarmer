package ru.taiufun.taiufunfarmer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import ru.taiufun.taiufunfarmer.commands.FarmerCommand;
import ru.taiufun.taiufunfarmer.config.FarmerConfig;
import ru.taiufun.taiufunfarmer.listeners.FarmerPlaceListener;
import ru.taiufun.taiufunfarmer.listeners.FarmerUseListener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.taiufun.taiufunfarmer.utils.HeadUtils;

import java.util.logging.Logger;

public class TaiufunFarmer extends JavaPlugin {

    private final Logger logger = Bukkit.getLogger();

    @Override
    public void onEnable() {
        super.saveDefaultConfig();
        FarmerConfig.load(super.getConfig());

        HeadUtils.init();
        super.getCommand("taiufunfarmer").setExecutor(new FarmerCommand());

        final PluginManager pluginManager = super.getServer().getPluginManager();
        pluginManager.registerEvents(new FarmerUseListener(), this);
        pluginManager.registerEvents(new FarmerPlaceListener(), this);

        logger.info("TaiufunFarmer enabled.");
    }


    @Override
    public void onDisable() {
        logger.info("TaiufunFarmer disabled.");
    }
}
// Брат, пожалуйста, не пиши код с гпт

