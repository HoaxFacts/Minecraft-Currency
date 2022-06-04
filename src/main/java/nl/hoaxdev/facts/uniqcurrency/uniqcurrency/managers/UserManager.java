package nl.hoaxdev.facts.uniqcurrency.uniqcurrency.managers;


import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.data.UserConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.UUID;

public class UserManager {

    private final UserConfiguration configuration;

    public UserManager(UserConfiguration config) {
        this.configuration = config;
    }

    public int addCoins(UUID user_id, int amount) {
        String path = String.format("users.%s.coins", user_id.toString());
        int coins = configuration.getInt(path);
        coins += amount;
        configuration.set(path, coins);

        try {
            configuration.save();
            configuration.loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return coins;
    }

    public int removeCoins(UUID user_id, int amount) {
        String path = String.format("users.%s.coins", user_id.toString());
        int coins = configuration.getInt(path);
        coins -= amount;

        if(coins <= 0){
            return 0;
        }

        configuration.set(path, coins);

        try {
            configuration.save();
            configuration.loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return coins;
    }

    public int getCoins(UUID user_id) {
        String path = String.format("users.%s.coins", user_id.toString());
        return configuration.getInt(path);
    }
}
