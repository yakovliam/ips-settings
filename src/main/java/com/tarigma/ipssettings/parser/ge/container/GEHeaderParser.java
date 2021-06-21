package com.tarigma.ipssettings.parser.ge.container;

import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.model.relay.RelayInfo;
import com.tarigma.ipssettings.parser.container.HeaderParser;

import java.util.List;

public class GEHeaderParser implements HeaderParser {

    @Override
    public RSEIContainer parse(List<String> strings) {
        RSEIContainer rseiContainer = new RSEIContainer();
        RelayInfo relayInfo = new RelayInfo();

        relayInfo.setManufacturer("GE");

        // TODO implement configuration default values

        for (String line : strings) {
            // split by key val (:) and parse
            String[] data = line.split(":", 2);
            String key = data[0];
            String value = data[1].trim();

            if (key.equalsIgnoreCase("Serial Number")) {
                relayInfo.setSerialNumber(value);
            } else if (key.equalsIgnoreCase("Manufacturing Date")) {
                relayInfo.setHardwareVersion(value);
            } else if (key.equalsIgnoreCase("Order Code")) {
                relayInfo.setRelayType(value);
            } // fall through
        }

        rseiContainer.setRelayInfo(relayInfo);

        return rseiContainer;
    }
}