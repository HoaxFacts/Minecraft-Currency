package nl.hoaxdev.facts.uniqcurrency.uniqcurrency.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public final class Util {

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> format(List<String> messages) {
        return messages.stream().map(Util::format).collect(Collectors.toList());
    }
}
