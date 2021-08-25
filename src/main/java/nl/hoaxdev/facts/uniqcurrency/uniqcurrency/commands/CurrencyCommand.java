package nl.hoaxdev.facts.uniqcurrency.uniqcurrency.commands;

import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.UniqCurrency;
import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.enums.CurrencyOptions;
import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.enums.UniqKDMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CurrencyCommand implements CommandExecutor {

    private final UniqCurrency plugin;

    public CurrencyCommand(UniqCurrency plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(UniqKDMessages.DANGER.getCode() + plugin.getMessageManager().getConsoleCannotExecute());
            return false;
        }

        Player player = (Player) sender;
        if (args.length <= 0) {
            player.sendMessage(plugin.getMessageManager().getInvalidArguments());
            return false;
        }

        CurrencyOptions option = CurrencyOptions.getOption(args[0].toLowerCase());
        if (option != null) {
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                player.sendMessage("Player not found!");
                return true;
            }

            if (!option.checkPermission(player)) {
                // Handle no permissions
                return false;
            }

            // Ignore duplication as they all will get a different message anyway ¯\_(ツ)_/¯
            int amount = Integer.parseInt(args[2]);
            switch(option) {
                case ADD:
                    plugin.getUserManager().addCoins(player.getUniqueId(), amount);
                    player.sendMessage(plugin.getMessageManager().getCoinsAdded()
                            .replace("{target}", target.getDisplayName())
                            .replace("{amount}", String.valueOf(amount)));
                    break;

                case REMOVE:
                    plugin.getUserManager().removeCoins(player.getUniqueId(), amount);
                    player.sendMessage(plugin.getMessageManager().getCoinsRemoved()
                            .replace("{target}", target.getDisplayName())
                            .replace("{amount}", String.valueOf(amount)));
                    break;

                case CHECK:
                    int coins = plugin.getUserManager().getCoins(player.getUniqueId());
                    player.sendMessage(plugin.getMessageManager().getCheckCoins()
                            .replace("{target}", target.getDisplayName())
                            .replace("{amount}", String.valueOf(coins)));
                    break;
            }
        }

        return false;
    }

}
