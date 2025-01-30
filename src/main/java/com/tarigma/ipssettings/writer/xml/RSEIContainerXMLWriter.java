package com.tarigma.ipssettings.writer.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.writer.Writer;
import com.tarigma.ipssettings.writer.serializer.UppercaseUUIDSerializer;
import java.util.UUID;

public class RSEIContainerXMLWriter implements Writer<RSEIContainer, String> {

  public static XmlMapper XML_MAPPER = new XmlMapper();

  static {
    XML_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    XML_MAPPER.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    XML_MAPPER.enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
    XML_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);

    // register the UppercaseUUIDSerializer
    XML_MAPPER.registerModule(
        new SimpleModule().addSerializer(UUID.class, new UppercaseUUIDSerializer()));
  }

  /**
   * Writes an rsei container to an xml document
   *
   * @param rseiContainer rsei container
   * @return xml document
   */
  @Override
  public String write(RSEIContainer rseiContainer) throws JsonProcessingException {
    return XML_MAPPER.writeValueAsString(rseiContainer);
  }
}
