package ru.laimcraft.buildings;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import ru.laimcraft.api.command.PlayerCommand;
import ru.laimcraft.api.log.Debug;

public class ConstructionCommand implements PlayerCommand {

    private static final World weWorld = BuildingsPlugin.weWorld;

    @Override
    public void onCommand(Player player, String[] strings) {
        com.sk89q.worldedit.entity.Player wePlayer = BukkitAdapter.adapt(player);
        LocalSession playerSession = BuildingsPlugin.worldEditSessionManager.getIfPresent(wePlayer);
        if (playerSession == null) player.sendMessage(ChatColor.RED + "Сессия не активна");

        try {
            Debug.error("0");
            if(strings[0] == null || strings[0].isEmpty()) return;
            Debug.error("-1");
            Clipboard clipboard = Construction.getClipboard(player, strings[0]);
            Debug.error("1");
            if(clipboard == null) return;
            Debug.error("2");
            Location location = player.getLocation();
            BlockVector3 pasteLocation = BlockVector3.at(location.getBlockX(), location.getBlockY(), location.getBlockZ());

            ClipboardHolder clipboardHolder = new ClipboardHolder(clipboard);
            playerSession.setClipboard(clipboardHolder);
            Debug.error("5");
        } catch (Exception e) {
            Debug.debug(e.getMessage());
        }
    }
}
