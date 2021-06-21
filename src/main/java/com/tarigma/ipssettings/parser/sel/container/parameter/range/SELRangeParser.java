package com.tarigma.ipssettings.parser.sel.container.parameter.range;

import com.tarigma.ipssettings.model.parameter.range.MaxValue;
import com.tarigma.ipssettings.model.parameter.range.MinValue;
import com.tarigma.ipssettings.model.parameter.range.Range;
import com.tarigma.ipssettings.parser.container.parameter.range.RangeParser;

public class SELRangeParser implements RangeParser {

    /**
     * Parses into a V given a K
     *
     * @param s k
     * @return v
     */
    @Override
    public Range parse(String s) {
        // split by "~" character
        String[] data = s.split("~");

        MinValue minValue = new MinValue(data[0]);
        MaxValue maxValue = new MaxValue(data[1]);

        return new Range()
                .setMaxValue(maxValue)
                .setMinValue(minValue);
    }
}
