package com.tarigma.ipssettings.parser.ge.container;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.tarigma.ipssettings.model.Block;

import java.util.List;

import org.junit.jupiter.api.Test;

class GEBlocksParserTest {

	private GEBlocksParser parser = new GEBlocksParser();

	@Test
	void testNoBlocks() {
		var lines = List.of("Label:Value");
		var actual = parser.parse(lines);
		assertThat(actual).isEmpty();
	}

	@Test
	void testNoParents() {
		var lines = List.of("Block 1:Label:Value");
		var actual = parser.parse(lines);
		assertThat(actual).satisfiesExactlyInAnyOrder(
				(Block block) -> {
					assertEquals("Block 1", block.getName());
					assertEquals("Block 1/", block.getBlockPath());
					assertNull(block.getParentBlockID(), "block should have no parent");
				}
		);
	}

	@Test
	void testOneParent() {
		var lines = List.of("Block 1: Block 2: Label:Value");
		var actual = parser.parse(lines);
		assertThat(actual).satisfiesExactlyInAnyOrder(
				(Block block) -> {
					assertEquals("Block 1", block.getName());
					assertEquals("Block 1/", block.getBlockPath());
					assertNull(block.getParentBlockID(), "block should have no parent");
				},
				(Block block) -> {
					assertEquals("Block 2", block.getName());
					assertEquals("Block 1/Block 2/", block.getBlockPath());
					assertNotNull(block.getParentBlockID(), "block should have parent");
				}
		);
	}

	@Test
	void testTwoParents() {
		var lines = List.of("Block 1:Block 2:Block 3:Label:Value");
		var actual = parser.parse(lines);
		assertThat(actual).satisfiesExactlyInAnyOrder(
				(Block block) -> {
					assertEquals("Block 1", block.getName());
					assertEquals("Block 1/", block.getBlockPath());
					assertNull(block.getParentBlockID(), "block should have no parent");
				},
				(Block block) -> {
					assertEquals("Block 2", block.getName());
					assertEquals("Block 1/Block 2/", block.getBlockPath());
					assertNotNull(block.getParentBlockID(), "block should have parent");
				},
				(Block block) -> {
					assertEquals("Block 3", block.getName());
					assertEquals("Block 1/Block 2/Block 3/", block.getBlockPath());
					assertNotNull(block.getParentBlockID(), "block should have parent");
				}
		);
	}

	@Test
	void testSharedAncestor() {
		var lines = List.of("Block A:Block B:Block C:Label:Value", "Block A:Block D:Block E:Label:Value");
		var actual = parser.parse(lines);
		assertThat(actual).satisfiesExactlyInAnyOrder(
				(Block block) -> {
					assertEquals("Block A", block.getName());
					assertEquals("Block A/", block.getBlockPath());
					assertNull(block.getParentBlockID(), "block should have no parent");
				},
				(Block block) -> {
					assertEquals("Block B", block.getName());
					assertEquals("Block A/Block B/", block.getBlockPath());
					assertNotNull(block.getParentBlockID(), "block should have parent");
				},
				(Block block) -> {
					assertEquals("Block C", block.getName());
					assertEquals("Block A/Block B/Block C/", block.getBlockPath());
					assertNotNull(block.getParentBlockID(), "block should have parent");
				},
				(Block block) -> {
					assertEquals("Block D", block.getName());
					assertEquals("Block A/Block D/", block.getBlockPath());
					assertNotNull(block.getParentBlockID(), "block should have parent");
				},
				(Block block) -> {
					assertEquals("Block E", block.getName());
					assertEquals("Block A/Block D/Block E/", block.getBlockPath());
					assertNotNull(block.getParentBlockID(), "block should have parent");
				}
		);
	}

}
