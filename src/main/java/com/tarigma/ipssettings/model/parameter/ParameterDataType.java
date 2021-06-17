package com.tarigma.ipssettings.model.parameter;

public enum ParameterDataType {

    DOUBLE("Double"),
    STRING("String"),
    ENUM("Enum");

    /**
     * The handle or human readable name
     */
    private final String handle;

    /**
     * Construct data type
     *
     * @param handle handle
     */
    ParameterDataType(String handle) {
        this.handle = handle;
    }

    /**
     * Returns the handle
     *
     * @return handle
     */
    public String getHandle() {
        return handle;
    }
}
