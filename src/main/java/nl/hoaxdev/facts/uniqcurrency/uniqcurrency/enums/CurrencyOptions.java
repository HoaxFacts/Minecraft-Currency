package nl.hoaxdev.facts.uniqcurrency.uniqcurrency.enums;

import org.bukkit.entity.Player;

public enum CurrencyOptions {

    ADD("add", "uc.add"),
    REMOVE("remove", "uc.remove"),
    CHECK("check", "uc.check");

    private final String meaning;
    private final String permission;

    CurrencyOptions(String meaning, String permission){
        this.meaning = meaning;
        this.permission = permission;
    }

    public boolean checkPermission(Player p) {
        return p.hasPermission("uc.admin.*") || p.hasPermission("uc.*") || p.hasPermission(this.permission);
    }

    public static CurrencyOptions getOption(String optionStr) {
        try {
            return valueOf(optionStr);
        } catch(Exception e) {
            return null;
        }
    }

    public String getMeaning(){
        return this.meaning;
    }

}
