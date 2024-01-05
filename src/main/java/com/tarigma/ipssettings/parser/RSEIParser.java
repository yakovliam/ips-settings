package com.tarigma.ipssettings.parser;

import com.tarigma.ipssettings.model.RSEIContainer;
import java.util.List;

/**
 * Parses the lines of a file (List of strings) into an RSEI container
 */
public interface RSEIParser extends Parser<List<String>, RSEIContainer> {
}
