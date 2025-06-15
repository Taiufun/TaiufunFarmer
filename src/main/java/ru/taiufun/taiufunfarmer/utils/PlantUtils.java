package ru.taiufun.taiufunfarmer.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;

import java.util.Set;

public class PlantUtils {

    private static final Set<Material> GROWABLE_PLANTS = Set.of(
            Material.WHEAT,
            Material.CARROTS,
            Material.POTATOES,
            Material.BEETROOTS,
            Material.SWEET_BERRY_BUSH,
            Material.COCOA
    );

    public static void growPlantsAround(Block center, int radius) {
        final int xx = center.getX();
        final int yy = center.getY();
        final int zz = center.getZ();

        for (int x = xx - radius; x <= xx + radius; x++) {
            for (int z = zz - radius; z <= zz + radius; z++) {
                for (int y = yy - radius; y <= yy + radius; y++) {
                    final Block block = center.getWorld().getBlockAt(x, y, z);

                    if (isGrowablePlant(block)) {
                        final Ageable data = (Ageable) block.getBlockData();
                        data.setAge(data.getMaximumAge());
                        block.setBlockData(data, true);
                    }
                }
            }
        }
    }

    private static boolean isGrowablePlant(Block block) {
        return GROWABLE_PLANTS.contains(block.getType()) && block.getBlockData() instanceof Ageable;
    }
}
