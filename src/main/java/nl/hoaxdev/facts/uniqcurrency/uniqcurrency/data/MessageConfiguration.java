package nl.hoaxdev.facts.uniqcurrency.uniqcurrency.data;

import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.UniqCurrency;
import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.utils.Util;

public final class MessageConfiguration extends BaseConfiguration {

    public MessageConfiguration(UniqCurrency plugin) {
        super(plugin, "messages.yml");
    }

    public String getMessage(String key, String defaultMessage) {
        String message = defaultMessage;
        if (this.contains(key)) {
            message = this.getString(key);
        }

        return Util.format(message);
    }

    public void reload() {
        try {
            this.load(this.file);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}