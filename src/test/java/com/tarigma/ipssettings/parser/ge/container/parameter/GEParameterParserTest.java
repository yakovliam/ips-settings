package com.tarigma.ipssettings.parser.ge.container.parameter;

import static org.junit.jupiter.api.Assertions.*;
import com.tarigma.ipssettings.model.parameter.ParameterDataType;
import org.junit.jupiter.api.Test;

class GEParameterParserTest {
	
	private final GEParameterParser parser = new GEParameterParser();

	@Test
	void testParseNumberWithMultipleColon() {
		var actual = parser.parse("SETTING GROUP 5: DISTANCE: PHASE DISTANCE Z2: PHS DIST Z2 QUAD LFT BLD:  10.00");
		assertEquals("SETTING GROUP 5: DISTANCE: PHASE DISTANCE Z2: PHS DIST Z2 QUAD LFT BLD", actual.getName());
		assertEquals("10.00", actual.getValue());
		assertNull(actual.getUnit());
		assertEquals(ParameterDataType.DOUBLE, actual.getDataType());
	}

	@Test
	void testParseValueWithSpaces() {
		var actual = parser.parse("User Programmable LED 41 Operand:CONTROL PUSHBUTTON 1 ON ");
		assertEquals("User Programmable LED 41 Operand", actual.getName());
		assertEquals("CONTROL PUSHBUTTON 1 ON", actual.getValue());
		assertNull(actual.getUnit());
		assertEquals(ParameterDataType.STRING, actual.getDataType());
	}

	@Test
	void testParseValueWithUnits() {
		var actual = parser.parse("Phase CT x Primary:    1 A");
		assertEquals("Phase CT x Primary", actual.getName());
		assertEquals("1", actual.getValue());
		assertEquals("A", actual.getUnit().getValue());
		assertEquals(ParameterDataType.DOUBLE, actual.getDataType());
	}

	@Test
	void testParseTwoWordValue() {
		var actual = parser.parse("Oscillography Trigger Mode:Automatic Overwrite ");
		assertEquals("Oscillography Trigger Mode", actual.getName());
		assertEquals("Automatic Overwrite", actual.getValue());
		assertNull(actual.getUnit());
		assertEquals(ParameterDataType.STRING, actual.getDataType());
	}

	@Test
	void testParseRatioValue() {
		var actual = parser.parse("Auxiliary VT x Ratio:1.00 :1");
		assertEquals("Auxiliary VT x Ratio", actual.getName());
		assertEquals("1.00 :1", actual.getValue());
		assertNull(actual.getUnit());
		assertEquals(ParameterDataType.STRING, actual.getDataType());
	}
}
