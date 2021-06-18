package com.tarigma.ipssettings.detector;

import java.util.List;

public class InputTypeDetector {

    /**
     * Returns the expected input type relation based on the file data input
     * <p>
     * Each corresponding string to enum key represents what the expected file
     * will start with. For example, an SEL file is expected to start with the text {@code [INFO]} while
     * a GE file is expected to start with the text {@code Serial Number:}.
     *
     * @param fileData file data
     * @return input type
     */
    public static InputTypeRelation findInputType(List<String> fileData) {
        return fileData.get(0).startsWith(InputTypeRelation.GE.getStartLineRelation()) ? InputTypeRelation.GE : InputTypeRelation.SEL;
    }
}
