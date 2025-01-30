package com.tarigma.ipssettings.parser.abb.container;


import static com.tarigma.ipssettings.util.UnknownRelayInfoUtil.UNKNOWN_FIRMWARE_VERSION;
import static com.tarigma.ipssettings.util.UnknownRelayInfoUtil.UNKNOWN_HARDWARE_VERSION;
import static com.tarigma.ipssettings.util.UnknownRelayInfoUtil.UNKNOWN_RELAY_TYPE;
import static com.tarigma.ipssettings.util.UnknownRelayInfoUtil.UNKNOWN_SERIAL_NUMBER;

import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.model.relay.RelayInfo;
import com.tarigma.ipssettings.parser.container.HeaderParser;
import java.util.List;

public class ABBHeaderParser implements HeaderParser {

  @Override
  public RSEIContainer parse(List<String> strings) {
    RSEIContainer rseiContainer = new RSEIContainer();
    RelayInfo relayInfo = new RelayInfo();

    relayInfo.setManufacturer("ABB");

    relayInfo.setRelayType(UNKNOWN_RELAY_TYPE);
    relayInfo.setFirmwareVersion(UNKNOWN_FIRMWARE_VERSION);
    relayInfo.setHardwareVersion(UNKNOWN_HARDWARE_VERSION);
    relayInfo.setSerialNumber(UNKNOWN_SERIAL_NUMBER);

    rseiContainer.setRelayInfo(relayInfo);

    return rseiContainer;
  }
}
