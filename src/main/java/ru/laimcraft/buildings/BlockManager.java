package ru.laimcraft.buildings;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.block.BaseBlock;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import ru.laimcraft.api.log.Debug;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockManager {

    public static final BaseBlock air = BukkitAdapter.adapt(Material.AIR.createBlockData()).toBaseBlock();

    public static final Set<Material> allowedBlocks = getNewAllowedBlocks();

    private static Set<Material> getNewAllowedBlocks() {
        Set<Material> blocks = new HashSet<>(512, 1.0F);
        FileConfiguration config = BuildingsPlugin.instance.getConfig();

        List<String> list = config.getStringList("allowed_blocks");

        for (String material : list) {
            try {
                blocks.add(Material.valueOf(material.toUpperCase()));
            } catch (IllegalArgumentException e) {
                Debug.error("IllegalArgumentException " + material + " не существует");
            }
        }

        for (Material material : blocks) {
            System.out.println(material.name());
            Debug.log(material.name());
        }

        return blocks;
    }
}
