package com.tarigma.ipssettings.model.parameter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class ParameterSet {

    /**
     * Represents `ParamSetID`
     */
    private UUID id;

    /**
     * Represents `SetName`
     */
    private String name;

    /**
     * The set of parameters
     * <p>
     * Each parameter begins and ends with {@code <Parameter></Parameter>}
     */
    private List<Parameter<?>> parameterSet;

    /**
     * Construct parameter set
     */
    public ParameterSet() {
        this.parameterSet = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public ParameterSet setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ParameterSet setName(String name) {
        this.name = name;
        return this;
    }

    public List<Parameter<?>> getParameterSet() {
        return parameterSet;
    }

    public ParameterSet setParameterSet(List<Parameter<?>> parameterSet) {
        this.parameterSet = parameterSet;
        return this;
    }

    public ParameterSet addToParameterSet(Parameter<?> parameter) {
        this.parameterSet.add(parameter);
        return this;
    }
}
