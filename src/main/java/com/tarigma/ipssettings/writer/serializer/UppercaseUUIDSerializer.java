package com.tarigma.ipssettings.writer.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.UUID;

public class UppercaseUUIDSerializer extends JsonSerializer<UUID> {
  @Override
  public void serialize(UUID uuid, JsonGenerator jsonGenerator,
                        SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeString(uuid.toString().toUpperCase());
  }
}
