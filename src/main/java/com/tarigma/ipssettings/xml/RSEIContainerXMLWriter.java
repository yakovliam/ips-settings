package com.tarigma.ipssettings.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.tarigma.ipssettings.model.RSEIContainer;

public class RSEIContainerXMLWriter implements XMLWriter<RSEIContainer, String> {

    /**
     * Writes an rsei container to an xml document
     *
     * @param rseiContainer rsei container
     * @return xml document
     */
    @Override
    public String write(RSEIContainer rseiContainer) throws JsonProcessingException {
        XmlMapper objectMapper = new XmlMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        objectMapper.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);

        return objectMapper.writeValueAsString(rseiContainer);
    }
}
