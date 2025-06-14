package ru.taiufun.taiufunfarmer.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownUtil {

    private static final Map<UUID, Map<String, Long>> cooldowns = new HashMap<>();


    public static boolean hasCooldown(Player player, String key) {
        UUID uuid = player.getUniqueId();
        if (!cooldowns.containsKey(uuid)) return false;

        Map<String, Long> playerCooldowns = cooldowns.get(uuid);
        if (!playerCooldowns.containsKey(key)) return false;

        long expireTime = playerCooldowns.get(key);
        return System.currentTimeMillis() < expireTime;
    }


    public static void startCooldown(Player player, String key, long cooldownMillis) {
        cooldowns.computeIfAbsent(player.getUniqueId(), k -> new HashMap<>())
                .put(key, System.currentTimeMillis() + cooldownMillis);
    }

    public static long getCooldownLeft(Player player, String key) {
        UUID uuid = player.getUniqueId();
        if (!cooldowns.containsKey(uuid)) return 0;

        Map<String, Long> playerCooldowns = cooldowns.get(uuid);
        if (!playerCooldowns.containsKey(key)) return 0;

        long expireTime = playerCooldowns.get(key);
        long left = expireTime - System.currentTimeMillis();
        return left > 0 ? left : 0;
    }
}
