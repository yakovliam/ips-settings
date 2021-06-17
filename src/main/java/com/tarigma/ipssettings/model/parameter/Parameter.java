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
public class Parameter<T extends ParameterDataType> {

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
     */
    private T value;

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

    public T getValue() {
        return value;
    }

    public Parameter<T> setValue(T value) {
        this.value = value;
        return this;
    }
}
