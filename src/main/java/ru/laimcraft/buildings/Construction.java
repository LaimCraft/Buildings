package ru.laimcraft.buildings;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.block.BlockState;
import org.bukkit.Bukkit;
import ru.laimcraft.api.log.Debug;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ru.laimcraft.buildings.BlockManager.air;
import static ru.laimcraft.buildings.BlockManager.allowedBlocks;

public class Construction {

    public static final WorldEdit worldEdit = WorldEdit.getInstance();

    private Clipboard clipboard;

    public static Clipboard getClipboard(String schematicName) {

        File file = new File(Bukkit.getPluginsFolder(), "WorldEdit\\schematics\\" + schematicName);

        try (ClipboardReader clipboardReader = ClipboardFormats.findByFile(file).getReader(new FileInputStream(file))) {
            Clipboard oldClipboard = clipboardReader.read();

            CuboidRegion region = oldClipboard.getRegion().getBoundingBox();
            Clipboard newClipboard = new BlockArrayClipboard(oldClipboard.getRegion());
            newClipboard.setOrigin(oldClipboard.getOrigin());

            BlockVector3 min = region.getMinimumPoint();
            BlockVector3 max = region.getMaximumPoint();

            for (int x = min.x(); x <= max.x(); x++) {
                for (int y = min.y(); y <= max.y(); y++) {
                    for (int z = min.z(); z <= max.z(); z++) {
                        BlockVector3 pos = BlockVector3.at(x, y, z);
                        BlockState blockState = oldClipboard.getBlock(pos);
                        try {
                            if (allowedBlocks.contains(BukkitAdapter.adapt(blockState.getBlockType().getItemType()))) {
                                newClipboard.setBlock(pos, blockState.toBaseBlock());
                            } else {
                                newClipboard.setBlock(pos, air);
                            }
                        } catch (WorldEditException e) {
                            Debug.debug(e.getMessage());
                        }
                    }
                }
            }

            return newClipboard;
        } catch (FileNotFoundException e) {
            Debug.error(e.getMessage());
            return null;
        } catch (IOException e) {
            Debug.error(e.getMessage());
            return null;
        }
    }
}
