package com.tarigma.ipssettings.model;

import java.util.UUID;

public class Block {

	private UUID blockID;
	
	private UUID parentBlockID;
	
	private String blockPath;
	
	private String blockPathFromID;
	
	private String name;
	
	private String description;

	public UUID getBlockID() {
		return blockID;
	}

	public void setBlockID(UUID blockID) {
		this.blockID = blockID;
	}

	public UUID getParentBlockID() {
		return parentBlockID;
	}

	public void setParentBlockID(UUID parentBlockID) {
		this.parentBlockID = parentBlockID;
	}

	public String getBlockPath() {
		return blockPath;
	}

	public void setBlockPath(String blockPath) {
		this.blockPath = blockPath;
	}

	public String getBlockPathFromID() {
		return blockPathFromID;
	}

	public void setBlockPathFromID(String blockPathFromID) {
		this.blockPathFromID = blockPathFromID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
