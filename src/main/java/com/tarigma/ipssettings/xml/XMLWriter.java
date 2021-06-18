package com.tarigma.ipssettings.xml;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface XMLWriter<K, V> {

    /**
     * Writes a V from a K
     *
     * @param k k
     * @return v
     */
    V write(K k) throws JsonProcessingException;
}
