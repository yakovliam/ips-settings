package com.tarigma.ipssettings.model.relay;

import java.util.UUID;

public class RelayInfo {

    /**
     * Represents `AssetID`
     */
    private UUID assetId;

    /**
     * Represents `LocationPath`
     */
    private String locationPath;

    /**
     * Represents `Manufacturer`
     */
    private String manufacturer;

    /**
     * Represents `RelayType`
     */
    private String relayType;

    /**
     * Represents `SerialNumber`
     */
    private String serialNumber;

    /**
     * Represents `FirmwareVersion`
     */
    private String firmwareVersion;

    /**
     * Represents `HardwareVersion`
     *
     * Usually empty ({@code ""})
     */
    private String hardwareVersion;

    /**
     * Represents `ActiveParamSetID`
     */
    private UUID activeParamSetId;

    public UUID getAssetId() {
        return assetId;
    }

    public RelayInfo setAssetId(UUID assetId) {
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
