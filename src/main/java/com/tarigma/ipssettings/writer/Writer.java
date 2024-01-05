package com.tarigma.ipssettings.writer;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Writer<K, V> {

  /**
   * Writes a V from a K
   *
   * @param k k
   * @return v
   */
  V write(K k) throws JsonProcessingException;
}
