package nl.hoaxdev.facts.uniqcurrency.uniqcurrency.data;

import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.UniqCurrency;

public class UserConfiguration extends BaseConfiguration {

    public UserConfiguration(UniqCurrency plugin) {
        super(plugin, "userdata.yml");
    }

    public void reload() {
        try {
            load(this.file);
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadData() { }

}
