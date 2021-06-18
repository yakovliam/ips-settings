package com.tarigma.ipssettings.parser.sel.container;

import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.parser.container.HeaderParser;

import java.util.Iterator;
import java.util.List;

public class SELHeaderParser implements HeaderParser {

    @Override
    public RSEIContainer parse(List<String> strings) {
        RSEIContainer rseiContainer = new RSEIContainer();

        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String current = iterator.next();
        }

        return rseiContainer;
    }
}
