package com.tarigma.ipssettings.model.parameter.range;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class MinValue {

    /**
     * Value
     * <p>
     * Always exists as a string because that's how XML outputs it
     */
    @JacksonXmlText
    private final String value;

    /**
     * Constructs min value
     * @param value value
     */
    public MinValue(String value) {
        this.value = value;
    }
}
