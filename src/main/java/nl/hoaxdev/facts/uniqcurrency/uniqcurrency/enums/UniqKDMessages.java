package nl.hoaxdev.facts.uniqcurrency.uniqcurrency.enums;

import nl.hoaxdev.facts.uniqcurrency.uniqcurrency.utils.Util;

// TODO: Custom logger
public enum UniqKDMessages {
    PREFIX(Util.format("&8Uniq&aKD")),
    OK(Util.format("&a")),
    DANGER(Util.format("&c")),
    ALERT(Util.format("&4"));

    private final String code;

    UniqKDMessages(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
