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
}
