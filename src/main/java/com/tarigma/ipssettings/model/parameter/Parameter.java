package com.tarigma.ipssettings.model.parameter;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.tarigma.ipssettings.model.parameter.address.Address;
import com.tarigma.ipssettings.model.parameter.range.Range;
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
  @JacksonXmlProperty(localName = "BlockID")
  private UUID blockId;

  /**
   * Represents `Address`
   */
  @JacksonXmlProperty(localName = "Address")
  private Address address;

  /**
   * Represents `Name`
   */
  @JacksonXmlProperty(localName = "Name")
  private String name;

  /**
   * Represents `Description`
   */
  @JacksonXmlProperty(localName = "Description")
  private String description;

  /**
   * Represents `DataType`
   */
  @JacksonXmlProperty(localName = "DataType")
  private ParameterDataType dataType;

  /**
   * Represents `Unit`
   */
  @JacksonXmlProperty(localName = "Unit")
  private Unit unit;

  /**
   * Represents `Value`
   * <p>
   * Always a string, because that's how XML uses it
   */
  @JacksonXmlProperty(localName = "Value")
  private String value;

  /**
   * Represents `Range`
   */
  @JacksonXmlProperty(localName = "Range")
  private Range range;

  /**
   * Represents `Localization`
   */
//    @JacksonXmlProperty(localName = "Localization")
//    @JacksonXmlElementWrapper(useWrapping = false)
//    private Localization localization;
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

//    public Localization getLocalization() {
//        return localization;
//    }

//    public Parameter<T> setLocalization(Localization localization) {
//        this.localization = localization;
//        return this;
//    }

  public Unit getUnit() {
    return unit;
  }

  public Parameter<T> setUnit(Unit unit) {
    this.unit = unit;
    return this;
  }

  public Range getRange() {
    return range;
  }

  public Parameter<T> setRange(Range range) {
    this.range = range;
    return this;
  }

  public static Parameter<ParameterDataType> with(ParameterDataType parameterDataType) {
    return new Parameter<ParameterDataType>().setDataType(parameterDataType);
  }
}
