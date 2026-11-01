package xyz.srnyx.pl3xmapsupervanish;

import de.myzelyam.api.vanish.VanishAPI;
import net.pl3x.map.core.Pl3xMap;
import net.pl3x.map.core.player.Player;
import net.pl3x.map.core.player.PlayerRegistry;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Pl3xMapSuperVanish extends JavaPlugin implements Listener {
    private PlayerListener listener;

    @Override
    public void onEnable() {
        if (!isVanishPluginAvailable()) {
            getLogger().severe("SuperVanish or PremiumVanish was not found! Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        this.listener = new PlayerListener(this);
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this.listener, this);
        pm.registerEvents(this, this);

        syncOnlinePlayers();
        getLogger().info("Pl3xMap-SuperVanish enabled successfully.");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll((Plugin) this);
        if (this.listener != null) {
            HandlerList.unregisterAll(this.listener);
            this.listener = null;
        }
        getLogger().info("Pl3xMap-SuperVanish disabled.");
    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
        syncOnlinePlayers();
    }

    public boolean isVanishPluginAvailable() {
        PluginManager manager = Bukkit.getPluginManager();
        return manager.isPluginEnabled("SuperVanish") || manager.isPluginEnabled("PremiumVanish");
    }

    public void syncOnlinePlayers() {
        try {
            PlayerRegistry registry = Pl3xMap.api().getPlayerRegistry();
            if (registry == null) {
                return;
            }
            boolean vanishAvailable = isVanishPluginAvailable();
            for (org.bukkit.entity.Player bukkitPlayer : Bukkit.getOnlinePlayers()) {
                if (vanishAvailable) {
                    Player mapPlayer = registry.get(bukkitPlayer.getUniqueId());
                    if (mapPlayer != null) {
                        mapPlayer.setHidden(VanishAPI.isInvisible(bukkitPlayer), false);
                    }
                }
            }
        } catch (Throwable t) {
            getLogger().warning("Could not sync online players with Pl3xMap: " + t.getMessage());
        }
    }
}
