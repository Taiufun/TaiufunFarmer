package ru.taiufun.taiufunfarmer.config;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import ru.taiufun.taiufunfarmer.TaiufunFarmer;
import ru.taiufun.taiufunfarmer.utils.Colorizer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FarmerConfig {

    private static FileConfiguration config;

    public static void load(FileConfiguration file) {
        config = file;

        cooldownWaitMessage = Colorizer.use(config.getString("messages.cooldown"));

        parseFarmer();
    }

    public static String cooldownWaitMessage;

    private static void parseFarmer() {
        final ConfigurationSection section = config.getConfigurationSection("farmer");

        FARMER.display = Colorizer.use(section.getString("display"));
        FARMER.texture = section.getString("texture");
        FARMER.growRadius = section.getInt("growRadius");
        FARMER.growCooldownSeconds = section.getInt("growCooldownSeconds");

        final List<?> rawLore = section.getList("lore");
        if (rawLore == null || rawLore.isEmpty()) return;

        FARMER.lore = rawLore.stream()
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Colorizer::use)
                .toList();
    }

    public static class FARMER {
        public static int growCooldownSeconds;
        public static int growRadius;
        public static String display;
        public static String texture;
        public static List<String> lore;
    }

}
