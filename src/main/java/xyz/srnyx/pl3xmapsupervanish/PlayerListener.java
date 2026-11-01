package xyz.srnyx.pl3xmapsupervanish;

import de.myzelyam.api.vanish.PlayerHideEvent;
import de.myzelyam.api.vanish.PlayerShowEvent;
import de.myzelyam.api.vanish.VanishAPI;
import net.pl3x.map.core.Pl3xMap;
import net.pl3x.map.core.player.Player;
import net.pl3x.map.core.player.PlayerRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerListener implements Listener {
    @NotNull private final Pl3xMapSuperVanish plugin;

    public PlayerListener(@NotNull Pl3xMapSuperVanish plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerHide(@NotNull PlayerHideEvent event) {
        setPlayerHidden(event.getPlayer(), true);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerShow(@NotNull PlayerShowEvent event) {
        setPlayerHidden(event.getPlayer(), false);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        org.bukkit.entity.Player player = event.getPlayer();
        if (plugin.isVanishPluginAvailable()) {
            setPlayerHidden(player, VanishAPI.isInvisible(player));
        }
    }

    public void setPlayerHidden(@NotNull org.bukkit.entity.Player bukkitPlayer, boolean hidden) {
        try {
            PlayerRegistry registry = Pl3xMap.api().getPlayerRegistry();
            if (registry == null) {
                return;
            }
            Player mapPlayer = registry.get(bukkitPlayer.getUniqueId());
            if (mapPlayer != null) {
                mapPlayer.setHidden(hidden, false);
            }
        } catch (Throwable t) {
            plugin.getLogger().warning("Failed to update visibility for " + bukkitPlayer.getName() + ": " + t.getMessage());
        }
    }
}
