package nl.hoaxdev.facts.uniqcurrency.uniqcurrency;

import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.commands.CurrencyCommand;
import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.data.MessageConfiguration;
import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.data.UserConfiguration;
import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.listeners.CoinListener;
import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.managers.MessageManager;
import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.managers.UserManager;
import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.schedulers.CoinScheduler;
import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.utils.Util;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.UUID;

// TODO: Move all configuration stuff in a different class
public final class UniqCurrency extends JavaPlugin {

    public MessageConfiguration messageConfiguration;
    public UserConfiguration userConfiguration;

    public UserManager userManager;
    public MessageManager messageManager;

    private CoinScheduler coinsScheduler;

    YamlConfiguration yamlConfiguration = new YamlConfiguration();

    public int defaultAmount;
    public int coinsPerTime;

    @Override
    public void onEnable() {
        defaultAmount = this.getConfig().getInt("default-coin-amount");
        coinsPerTime = this.getConfig().getInt("coins-per-time");
        coinsScheduler = new CoinScheduler(this);

        try {
            loadData();
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(Util.format("&cEr is iets fout gegaan met het laden van de configuration."));
            getServer().getPluginManager().disablePlugin(this);
            e.printStackTrace();
        }

        registerEvents();
        registerCommands();

        getServer().getScheduler().scheduleSyncRepeatingTask(this, coinsScheduler, 0, CoinScheduler.ticks);
    }

    private void registerCommands() {
        registerCommand("muntstukken", new CurrencyCommand(this));
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new CoinListener(this), this);
    }

    private void loadData() {
        this.messageConfiguration = new MessageConfiguration(this);
        this.userConfiguration = new UserConfiguration(this);

        this.userManager = new UserManager(userConfiguration);
        this.messageManager = new MessageManager(messageConfiguration);

        this.userConfiguration.loadData();
    }

    public void createUserSection(String name, UUID user_id, Integer defaultCoins){
        File dataConfig = new File(getDataFolder(), "userdata.yml");
        if (!dataConfig.exists()) {
            dataConfig.getParentFile().mkdirs();
            saveResource("userdata.yml", false);
        }

        try {
            yamlConfiguration.load(dataConfig);
            yamlConfiguration.set("users.{user-id}.name".replace("{user-id}", user_id.toString()), name);
            yamlConfiguration.set("users.{user-id}.coins".replace("{user-id}", user_id.toString()), defaultCoins);
            yamlConfiguration.save(dataConfig);
            yamlConfiguration.load(dataConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerCommand(String command, CommandExecutor executor) {
        getCommand(command).setExecutor(executor);
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public MessageManager getMessageManager() {return messageManager;}

    public CoinScheduler getCoinsScheduler() {
        return coinsScheduler;
    }

}
