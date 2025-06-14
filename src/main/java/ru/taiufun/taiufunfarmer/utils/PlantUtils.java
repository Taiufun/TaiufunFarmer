// com/taiufunfarmer/utils/PlantUtils.java
package ru.taiufun.taiufunfarmer.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;

public class PlantUtils {

    public static void growPlantsAround(Block center, int radius) {
        int cx = center.getX();
        int cy = center.getY();
        int cz = center.getZ();

        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int y = cy - radius; y <= cy + radius; y++) {
                for (int z = cz - radius; z <= cz + radius; z++) {
                    Block block = center.getWorld().getBlockAt(x, y, z);
                    if (block == null) continue;

                    if (isGrowablePlant(block)) {
                        Ageable ageable = (Ageable) block.getBlockData();
                        ageable.setAge(ageable.getMaximumAge());
                        block.setBlockData(ageable, true);
                    }
                }
            }
        }
    }

    private static boolean isGrowablePlant(Block block) {
        Material mat = block.getType();
        switch (mat) {
            case WHEAT:
            case CARROTS:
            case POTATOES:
            case BEETROOTS:
            case SWEET_BERRY_BUSH:
            case COCOA:
                return block.getBlockData() instanceof Ageable;
            default:
                return false;
        }
    }
}
