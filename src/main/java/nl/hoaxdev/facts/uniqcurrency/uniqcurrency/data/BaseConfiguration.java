package nl.hoaxdev.facts.uniqcurrency.uniqcurrency.data;

import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.UniqCurrency;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public abstract class BaseConfiguration extends YamlConfiguration {

    protected final File file;
    protected final UniqCurrency plugin;
    protected final String fileName;

    public BaseConfiguration(UniqCurrency plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.file = new File(plugin.getDataFolder(), fileName);

        try {
            load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            this.load(this.file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() throws Exception {
        if (!this.file.exists()) {
            this.file.getParentFile().mkdirs();
            this.plugin.saveResource(this.fileName, false);
        }

        this.load(this.file);
    }

    public void save() throws Exception {
        this.save(this.file);
    }
}
