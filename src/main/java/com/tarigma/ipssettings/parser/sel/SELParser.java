package com.tarigma.ipssettings.parser.sel;

import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.parser.RSEIParser;
import com.tarigma.ipssettings.parser.sel.container.SELHeaderParser;
import com.tarigma.ipssettings.parser.sel.container.parameter.SELParameterSetParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Parses SEL format input (colon separated) into the RSEI class structure
 */
public class SELParser implements RSEIParser {

    /**
     * Ignore block pattern
     * <p>
     * Example: {@code [CLASSES]}
     */
    private static final Pattern IGNORE_BLOCK_PATTERN = Pattern.compile("\\[.*\\]");

    /**
     * Info block header
     */
    private static final Pattern INFO_BLOCK_HEADER = Pattern.compile("\\[INFO\\]");

    /**
     * P1 block header
     */
    private static final Pattern P1_BLOCK_HEADER = Pattern.compile("\\[P1\\]");

    @Override
    public RSEIContainer parse(List<String> strings) {
        RSEIContainer rseiContainer = new RSEIContainer();

        // current index
        int index = 0;
        // iterate
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String current = iterator.next();
            index++;

            // match info block header
            if (current.matches(INFO_BLOCK_HEADER.pattern())) {
                List<String> headerData = new ArrayList<>();
                boolean done = false;
                // loop / iterate until end of info block
                while (iterator.hasNext()) {
                    String curr2 = iterator.next();
                    if (curr2.matches(IGNORE_BLOCK_PATTERN.pattern())) {
                        break;
                    } else {
                        headerData.add(curr2);
                    }
                }

                // parse header data
                rseiContainer = new SELHeaderParser().parse(headerData);
            }

            // keep iterating until p1 block header
            if (current.matches(P1_BLOCK_HEADER.pattern())) {
                // this means everything after the current block is parsable data. so let's use the current index to
                // grab that from the string list and parse it
                break;
            }
        }

        List<String> parameterData = strings.subList(index, strings.size());

        // parse parameter data
        ParameterSet parameterSet = new SELParameterSetParser().parse(parameterData);

        // compile object
        rseiContainer.setParameterSet(parameterSet);

        return rseiContainer;
    }
}
