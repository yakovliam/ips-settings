package com.tarigma.ipssettings.model;

public enum InputFileExtension {

    SET("set");

    private final String ext;

    InputFileExtension(String ext) {
        this.ext = ext;
    }

    /**
     * Returns the extension
     *
     * @return extension
     */
    public String getExt() {
        return ext;
    }
}
