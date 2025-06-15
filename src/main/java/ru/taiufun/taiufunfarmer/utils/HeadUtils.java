package ru.taiufun.taiufunfarmer.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import ru.taiufun.taiufunfarmer.TaiufunFarmer;
import ru.taiufun.taiufunfarmer.config.FarmerConfig;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class HeadUtils {

    private static final NamespacedKey KEY_ITEM = new NamespacedKey("taiufunfarmer", "taiufun-head-farmer-nbt");

    public static ItemStack STACK;

    public static void init() {
        final ItemStack head = new ItemStack(org.bukkit.Material.PLAYER_HEAD);
        final SkullMeta meta = (SkullMeta) head.getItemMeta();

        final GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", FarmerConfig.FARMER.texture));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        meta.getPersistentDataContainer().set(KEY_ITEM, PersistentDataType.BYTE, (byte) 1);

        if (FarmerConfig.FARMER.display != null) meta.setDisplayName(FarmerConfig.FARMER.display);
        if (FarmerConfig.FARMER.lore != null && !FarmerConfig.FARMER.lore.isEmpty()) meta.setLore(FarmerConfig.FARMER.lore);

        head.setItemMeta(meta);

        STACK = head;
    }

    public static boolean isFarmerHead(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        if (!(item.getItemMeta() instanceof SkullMeta)) return false;

        final PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        return container.has(KEY_ITEM, PersistentDataType.BYTE);
    }
}
