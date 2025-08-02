package ru.laimcraft.buildings;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.session.SessionManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class BuildingsPlugin extends JavaPlugin {

    public static BuildingsPlugin instance;
    public static World world;
    public static com.sk89q.worldedit.world.World weWorld;
    public static WorldEdit worldEdit;
    public static SessionManager worldEditSessionManager;

    @Override
    public void onEnable() {
        instance = this;
        world = Bukkit.getWorld("world");
        weWorld = BukkitAdapter.adapt(world);
        worldEdit = WorldEdit.getInstance();
        worldEditSessionManager = worldEdit.getSessionManager();

        saveDefaultConfig();

        getCommand("construction").setExecutor(new ConstructionCommand());
    }
}
