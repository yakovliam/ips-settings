package com.tarigma.ipssettings.model.relay;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.UUID;

public class RelayInfo {

  /**
   * Represents `AssetID`
   */
  @JacksonXmlProperty(localName = "AssetID")
  private String assetId;

  /**
   * Represents `LocationPath`
   */
  @JacksonXmlProperty(localName = "LocationPath")
  private String locationPath;

  /**
   * Represents `Manufacturer`
   */
  @JacksonXmlProperty(localName = "Manufacturer")
  private String manufacturer;

  /**
   * Represents `RelayType`
   */
  @JacksonXmlProperty(localName = "RelayType")
  private String relayType;

  /**
   * Represents `SerialNumber`
   */
  @JacksonXmlProperty(localName = "SerialNumber")
  private String serialNumber;

  /**
   * Represents `FirmwareVersion`
   */
  @JacksonXmlProperty(localName = "FirmwareVersion")
  private String firmwareVersion;

  /**
   * Represents `HardwareVersion`
   * <p>
   * Usually empty ({@code ""})
   */
  @JacksonXmlProperty(localName = "HardwareVersion")
  private String hardwareVersion;

  /**
   * Represents `ActiveParamSetID`
   */
  @JacksonXmlProperty(localName = "ActiveParamSetID")
  private UUID activeParamSetId;

  public String getAssetId() {
    return assetId;
  }

  public RelayInfo setAssetId(String assetId) {
    this.assetId = assetId;
    return this;
  }

  public String getLocationPath() {
    return locationPath;
  }

  public RelayInfo setLocationPath(String locationPath) {
    this.locationPath = locationPath;
    return this;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public RelayInfo setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
    return this;
  }

  public String getRelayType() {
    return relayType;
  }

  public RelayInfo setRelayType(String relayType) {
    this.relayType = relayType;
    return this;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public RelayInfo setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
    return this;
  }

  public String getFirmwareVersion() {
    return firmwareVersion;
  }

  public RelayInfo setFirmwareVersion(String firmwareVersion) {
    this.firmwareVersion = firmwareVersion;
    return this;
  }

  public String getHardwareVersion() {
    return hardwareVersion;
  }

  public RelayInfo setHardwareVersion(String hardwareVersion) {
    this.hardwareVersion = hardwareVersion;
    return this;
  }

  public UUID getActiveParamSetId() {
    return activeParamSetId;
  }

  public RelayInfo setActiveParamSetId(UUID activeParamSetId) {
    this.activeParamSetId = activeParamSetId;
    return this;
  }
}
