package nl.hoaxdev.facts.uniqcurrency.uniqcurrency.managers;

import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.data.MessageConfiguration;
import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.utils.Util;

// TODO: No hard coding
public class MessageManager {

    private final MessageConfiguration configuration;

    public MessageManager(MessageConfiguration config) {
        this.configuration = config;
    }

    private final String consoleCannotExecute = Util.format("Alleen een speler kan deze commando uitvoeren!");
    private final String noPermissions = Util.format("Je hebt geen permissions om deze commando uit te voeren!");
    private final String invalidArguments = Util.format("Je hebt niet genoeg argumenten meegegeven! Probeer &f/uc help &4om het help menu te zien!");

    private final String receivedCoins = Util.format("&7Je bent &a1 uur &7online geweest! &eJe hebt &f{amount} &ecoins(s) ontvangen");
    private final String onlineForNotLongEnough = Util.format("&4Je bent niet lang genoeg online om een coin te ontvangen!");

    private final String configReload = Util.format("&cConfiguratie &fsuccesvol &cGeherladen!");
    private final String coinsAdded = Util.format("&cJe hebt succesvol &f{amount} &caan &f{target} &cgegeven!");
    private final String coinsRemoved = Util.format("&cJe hebt succesvol &f{amount} &cweggehaald bij: &f{target}");
    private final String checkCoins = Util.format("&a{target} &7heeft &a{amount} &7coins");

    public String getConsoleCannotExecute() {
        return consoleCannotExecute;
    }

    public String getNoPermissions() {
        return noPermissions;
    }

    public String getInvalidArguments() {
        return invalidArguments;
    }

    public String getReceivedCoins() {
        return receivedCoins;
    }

    public String getOnlineForNotLongEnough() {
        return onlineForNotLongEnough;
    }

    public String getConfigReload() {
        return configReload;
    }

    public String getCoinsAdded() {
        return coinsAdded;
    }

    public String getCoinsRemoved() {
        return coinsRemoved;
    }

    public String getCheckCoins() {
        return checkCoins;
    }

}
