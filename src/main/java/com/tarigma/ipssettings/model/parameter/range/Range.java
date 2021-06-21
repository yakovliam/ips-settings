package com.tarigma.ipssettings.model.parameter.range;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Range {

    /**
     * Represents `MinValue`
     */
    @JacksonXmlProperty(localName = "MinValue")
    private MinValue minValue;

    /**
     * Represents `MaxValue`
     */
    @JacksonXmlProperty(localName = "MaxValue")
    private MaxValue maxValue;

    /**
     * Represents `Step`
     */
    @JacksonXmlProperty(localName = "Step")
    private Step step;

    public MaxValue getMaxValue() {
        return maxValue;
    }

    public Range setMaxValue(MaxValue maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public MinValue getMinValue() {
        return minValue;
    }

    public Range setMinValue(MinValue minValue) {
        this.minValue = minValue;
        return this;
    }

    public Step getStep() {
        return step;
    }

    public Range setStep(Step step) {
        this.step = step;
        return this;
    }
}
