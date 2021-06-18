package com.tarigma.ipssettings.parser;

public interface Parser<K, V> {

    /**
     * Parses into a V given a K
     *
     * @param k k
     * @return v
     */
    V parse(K k);

}
