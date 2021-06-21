package com.tarigma.ipssettings.model.parameter.range;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class MaxValue {


    /**
     * Value
     * <p>
     * Always exists as a string because that's how XML outputs it
     */
    @JacksonXmlText
    private final String value;

    /**
     * Constructs max value
     * @param value value
     */
    public MaxValue(String value) {
        this.value = value;
    }
}
