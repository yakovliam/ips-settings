package com.tarigma.ipssettings.model.parameter;

public enum ParameterDataType {

    DOUBLE("Double", "D", "Float", "F"),
    STRING("String", "S"),
    ENUM("Enum");

    /**
     * The handle or human readable name
     * <p>
     * The first element in the array is the commonly accepted or 'Main' handle
     */
    private final String[] handle;

    /**
     * Construct data type
     *
     * @param handle handle
     */
    ParameterDataType(String... handle) {
        this.handle = handle;
    }

    /**
     * Returns the handle
     *
     * @return handle
     */
    public String[] getHandle() {
        return handle;
    }

    @Override
    public String toString() {
        return getHandle()[0];
    }

    /**
     * Determines the parameter data type of the given value
     *
     * @param value value
     * @return data type
     */
    public static ParameterDataType determineDataTypeByValue(String value) {
        try {
            Double.parseDouble(value);
            return ParameterDataType.DOUBLE;
        } catch (NumberFormatException ignored) {
        }

        try {
            Integer.parseInt(value);
            return ParameterDataType.DOUBLE;
        } catch (NumberFormatException ignored) {
        }

        return ParameterDataType.STRING;
    }
}
