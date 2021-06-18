package com.tarigma.ipssettings.parser.rsei;

import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.parser.Parser;

import java.util.List;

public class RSEIParser implements Parser<List<String>, RSEIContainer> {

    /**
     * Parses the lines from the input into an RSEI objcet
     * @param lines lines
     * @return RSEI container
     */
    @Override
    public RSEIContainer parse(List<String> lines) {
        throw new RuntimeException("Not implemented yet!");
    }
}
