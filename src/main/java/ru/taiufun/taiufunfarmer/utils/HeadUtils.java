package ru.taiufun.taiufunfarmer.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import ru.taiufun.taiufunfarmer.TaiufunFarmer;
import ru.taiufun.taiufunfarmer.config.FarmerConfig;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class HeadUtils {

    private static final String NBT_KEY = "taiufun-head-farmer-nbt";



    public static ItemStack create(TaiufunFarmer plugin, String base64Skin, String displayName, List<String> lore) {
        ItemStack head = new ItemStack(org.bukkit.Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", base64Skin));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        NamespacedKey key = new NamespacedKey(plugin, NBT_KEY);
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, (byte) 1);

        if (displayName != null) meta.setDisplayName(displayName);
        if (lore != null && !lore.isEmpty()) meta.setLore(lore);

        head.setItemMeta(meta);
        return head;
    }

    public static boolean isFarmerHead(TaiufunFarmer plugin, ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;
        if (!(item.getItemMeta() instanceof SkullMeta)) return false;

        NamespacedKey key = new NamespacedKey(plugin, NBT_KEY);
        Byte tag = item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.BYTE);

        return tag != null && tag == (byte) 1;
    }
}
