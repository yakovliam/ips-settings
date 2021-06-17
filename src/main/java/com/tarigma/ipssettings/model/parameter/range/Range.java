package com.tarigma.ipssettings.model.parameter.range;

public class Range {

    /**
     * Represents `MaxValue`
     */
    private MaxValue maxValue;

    /**
     * Represents `MinValue`
     */
    private MinValue minValue;

    /**
     * Represents `Step`
     */
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
