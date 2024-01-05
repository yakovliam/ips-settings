package com.tarigma.ipssettings.parser.sel.container;

import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.model.relay.RelayInfo;
import com.tarigma.ipssettings.parser.container.HeaderParser;
import java.util.Iterator;
import java.util.List;

public class SELHeaderParser implements HeaderParser {

  @Override
  public RSEIContainer parse(List<String> strings) {
    RSEIContainer rseiContainer = new RSEIContainer();
    RelayInfo relayInfo = new RelayInfo();

    relayInfo.setManufacturer("SEL");

    // TODO implement configuration default values

    Iterator<String> iterator = strings.iterator();
    while (iterator.hasNext()) {
      String current = iterator.next();

      // split by key val (=) and parse
      String[] data = current.split("=", 2);

      // ignore lines that don't match the pattern
      if (data.length < 2) {
        continue;
      }

      String key = data[0];
      String value = data[1];

      if (key.equalsIgnoreCase("RELAYTYPE")) {
        relayInfo.setRelayType(value);
      } else if (key.equalsIgnoreCase("FID")) {
        relayInfo.setFirmwareVersion(value);
      } else if (key.equalsIgnoreCase("BFID")) {
        relayInfo.setHardwareVersion(value);
      } else if (key.equalsIgnoreCase("PARTNO")) {
        relayInfo.setSerialNumber(value);
      } // fall through
    }

    rseiContainer.setRelayInfo(relayInfo);

    return rseiContainer;
  }
}
