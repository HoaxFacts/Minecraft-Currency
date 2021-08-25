package nl.hoaxdev.facts.uniqcurrency.uniqcurrency.schedulers;

import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.UniqCurrency;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;

public class CoinScheduler implements Runnable {

    public static int ticks = 3600 * 20;

    private final HashMap<UUID, Long> players;
    private final UniqCurrency plugin;

    public CoinScheduler(UniqCurrency plugin) {
        this.players = new HashMap<>();
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            if (!isOnlineFor(ticks, player)) {
                player.sendMessage("Je bent niet lang genoeg online: Actie geweigerd");
                return;
            }

            player.sendMessage(plugin.getMessageManager().getReceivedCoins()
                    .replace("{amount}", String.valueOf(plugin.coinsPerTime)));

            plugin.getUserManager().addCoins(player.getUniqueId(), plugin.coinsPerTime);
            plugin.getLogger().info("Muntstukken zijn gegeven.");
        }
    }

    private boolean isOnlineFor(int ticks, Player player){
        long playerOnlineTime = players.getOrDefault(player.getUniqueId(), System.currentTimeMillis());
        return (currentTimeMillis() - ticks) - playerOnlineTime >= 0;
    }

    public void addPlayer(Player player, long millis) {
        players.put(player.getUniqueId(), millis);
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
    }

}
