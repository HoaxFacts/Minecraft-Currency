package nl.hoaxdev.facts.uniqcurrency.uniqcurrency.listeners;

import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.UniqCurrency;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CoinListener implements Listener {

    private final UniqCurrency plugin;
    private final int coinsPerTime;

    public CoinListener(UniqCurrency plugin) {
        this.plugin = plugin;
        this.coinsPerTime = plugin.coinsPerTime;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        plugin.getCoinsScheduler().addPlayer(player, System.currentTimeMillis());

        if(!player.hasPlayedBefore()) {
            plugin.createUserSection(player.getDisplayName(), player.getUniqueId(), coinsPerTime);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        plugin.getCoinsScheduler().removePlayer(player);
    }

}
