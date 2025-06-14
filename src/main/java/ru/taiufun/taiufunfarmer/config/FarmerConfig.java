package ru.taiufun.taiufunfarmer.config;


import org.bukkit.configuration.file.FileConfiguration;
import ru.taiufun.taiufunfarmer.TaiufunFarmer;

import java.util.List;
import java.util.stream.Collectors;

public class FarmerConfig {

    private final TaiufunFarmer plugin;

    public FarmerConfig(TaiufunFarmer plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
    }

    private String colorize(String message) {
        return message == null ? "" : message.replace('&', '§');
    }
    public String getCooldownWaitMessage() {
        String msg = plugin.getConfig().getString("messages.cooldown.wait");
        return colorize(msg != null ? msg : "&cПожалуйста, подождите {time} секунд перед повторным использованием.");
    }

    public int getGrowCooldownSeconds() {
        return plugin.getConfig().getInt("farmer.growCooldownSeconds", 30);
    }


    public String getFarmerHeadDisplayName() {
        String name = plugin.getConfig().getString("farmerHead.displayName");
        return colorize(name);
    }
    public int getGrowRadius() {
        return plugin.getConfig().getInt("farmer.growRadius", 5);
    }


    public List<String> getFarmerHeadLore() {
        if (!plugin.getConfig().contains("farmerHead.lore")) {
            return null;
        }

        List<String> lore = plugin.getConfig().getStringList("farmerHead.lore");

        boolean hasMeaningfulLore = lore.stream()
                .anyMatch(line -> line != null && !line.trim().isEmpty());

        if (!hasMeaningfulLore) {
            return null;
        }

        return lore.stream()
                .map(this::colorize)
                .collect(Collectors.toList());
    }
}
