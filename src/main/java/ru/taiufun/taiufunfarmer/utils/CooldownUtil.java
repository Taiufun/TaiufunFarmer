package ru.taiufun.taiufunfarmer.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownUtil {

    private static final Map<UUID, Long> cooldowns = new HashMap<>();

    public static boolean hasCooldown(Player player) {
        long now = System.currentTimeMillis();
        long expireAt = cooldowns.getOrDefault(player.getUniqueId(), 0L);
        return expireAt > now;
    }

    public static void startCooldown(Player player, long cooldownMillis) {
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + cooldownMillis);
    }

    public static long getCooldownLeft(Player player) {
        long now = System.currentTimeMillis();
        long expireAt = cooldowns.getOrDefault(player.getUniqueId(), 0L);
        return Math.max(0, expireAt - now);
    }
}
