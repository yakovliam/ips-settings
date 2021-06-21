package com.tarigma.ipssettings.model.parameter;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class Unit {

    /**
     * Value
     * <p>
     * Always exists as a string because that's how XML outputs it
     */
    @JacksonXmlText
    private final String value;

    /**
     * Constructs unit
     *
     * @param value value
     */
    public Unit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
