package com.tarigma.ipssettings.parser.sel.container.parameter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tarigma.ipssettings.model.parameter.Parameter;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;

/**
 * Tests for SELParameterParser
 */
class SELParameterParserTest {
	
	private SELParameterParser parser = new SELParameterParser(); 

	@Test
	void testParseOldStyleString() {
		String input = "RID,\"FEEDER 1\",\"\",\"\",\"\",\"Relay Identifier(30chars)\",\"\"";
		
		Parameter<ParameterDataType> actual = parser.parse(input);
		assertEquals(ParameterDataType.STRING, actual.getDataType());
		assertEquals("RID", actual.getName());
		assertEquals("FEEDER 1", actual.getValue());
		assertEquals("Relay Identifier(30chars)", actual.getDescription());
	}

	@Test
	void testParseOldStyleNumber() {
		String input = "CTR,\"120\",\"\",\"\",\"\",\"Phase (IA,IB,IC) CT Ratio(1-6000)\",\"\"";
		
		Parameter<ParameterDataType> actual = parser.parse(input);
		assertEquals(ParameterDataType.DOUBLE, actual.getDataType());
		assertEquals("CTR", actual.getName());
		assertEquals("120", actual.getValue());
		assertEquals("Phase (IA,IB,IC) CT Ratio(1-6000)", actual.getDescription());
	}

	@Test
	void testParseNewStyleString() {
		String input = "\"67G1TC\",\"1\",,S,,\"Level 1 Torque Control (SELogic Equation)\"";
		
		Parameter<ParameterDataType> actual = parser.parse(input);
		assertEquals(ParameterDataType.STRING, actual.getDataType());
		assertEquals("67G1TC", actual.getName());
		assertEquals("1", actual.getValue());
		assertEquals("Level 1 Torque Control (SELogic Equation)", actual.getDescription());
	}

	@Test
	void testParseNewStyleNumber() {
		String input = "\"TMPOFF\",1.080,-100.000000~100.000000,F,\"degrees C\",\"Temperature Offset (-100 to 100 deg C)\"";
		
		Parameter<ParameterDataType> actual = parser.parse(input);
		assertEquals(ParameterDataType.DOUBLE, actual.getDataType());
		assertEquals("TMPOFF", actual.getName());
		assertEquals("1.080", actual.getValue());
		assertEquals("-100.000000", actual.getRange().getMinValue());
		assertEquals("100.000000", actual.getRange().getMaxValue());
		assertEquals("degrees C", actual.getUnit().getValue());
		assertEquals("Temperature Offset (-100 to 100 deg C)", actual.getDescription());
	}
}
