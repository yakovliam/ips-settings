package com.tarigma.ipssettings.model.parameter;

import com.tarigma.ipssettings.model.parameter.address.Address;

import java.util.UUID;

/**
 * A parameter
 * <p>
 * Takes T, which is the datatype associated with the value field
 *
 * @param <T> datatype
 */
public class Parameter<T extends Enum<ParameterDataType>> {

    /**
     * Represents `BlockID`
     */
    private UUID blockId;

    /**
     * Represents `Address`
     */
    private Address address;

    /**
     * Represents `Name`
     */
    private String name;

    /**
     * Represents `Description`
     */
    private String description;

    /**
     * Represents `DataType`
     */
    private ParameterDataType dataType;

    /**
     * Represents `Value`
     * <p>
     * Always a string, because that's how XML uses it
     */
    private String value;

    public UUID getBlockId() {
        return blockId;
    }

    public Parameter<T> setBlockId(UUID blockId) {
        this.blockId = blockId;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Parameter<T> setAddress(Address address) {
        this.address = address;
        return this;
    }

    public String getName() {
        return name;
    }

    public Parameter<T> setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Parameter<T> setDescription(String description) {
        this.description = description;
        return this;
    }

    public ParameterDataType getDataType() {
        return dataType;
    }

    public Parameter<T> setDataType(ParameterDataType dataType) {
        this.dataType = dataType;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Parameter<T> setValue(String value) {
        this.value = value;
        return this;
    }

    public static <E2 extends Enum<ParameterDataType>> Parameter<E2> with(E2 item) {
        return new Parameter<E2>();
    }
}
