package com.tarigma.ipssettings.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.UUID;

public class Block {

    private final UUID blockID;

    private final Block parent;

    private final String name;
    
    private String description;

    public Block(String name, Block parent) {
        this.name = name;
        this.parent = parent;
        this.blockID = UUID.nameUUIDFromBytes(getBlockPath().getBytes());
    }

    @JacksonXmlProperty(isAttribute = true)
    public UUID getBlockID() {
        return blockID;
    }

    public UUID getParentBlockID() {
        return parent == null ? null : parent.getBlockID();
    }

    public String getBlockPath() {
        String suffix = name + "/";
        return parent == null ? suffix : parent.getBlockPath() + suffix;
    }

    public String getBlockPathFromID() {
        String suffix = getBlockID().toString() + "/";
        return parent == null ? suffix : parent.getBlockPathFromID() + suffix;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
