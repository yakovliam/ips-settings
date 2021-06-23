package com.tarigma.ipssettings.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.tarigma.ipssettings.model.parameter.ParameterSet;
import com.tarigma.ipssettings.model.relay.RelayInfo;

@JacksonXmlRootElement(localName = "RSEI")
public class RSEIContainer {

    private static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";

    /**
     * Represents `Comment`
     */
    @JacksonXmlProperty(localName = "Comment")
    private String comment;

    /**
     * Represents `VersionRSEI`
     */
    @JacksonXmlProperty(localName = "VersionRSEI")
    private String versionRSEI;

    /**
     * Represents `SoftwareSource`
     */
    @JacksonXmlProperty(localName = "SoftwareSource")
    private String softwareSource;

    /**
     * Represents `MachineName`
     */
    @JacksonXmlProperty(localName = "MachineName")
    private String machineName;

    /**
     * Represents `UserDomainName`
     */
    @JacksonXmlProperty(localName = "UserDomainName")
    private String userDomainName;

    /**
     * Represents `UserName`
     */
    @JacksonXmlProperty(localName = "UserName")
    private String userName;

    /**
     * Represents `DataSource`
     */
    @JacksonXmlProperty(localName = "DataSource")
    private String dataSource;

    /**
     * Represents `Database`
     */
    @JacksonXmlProperty(localName = "Database")
    private String database;

    /**
     * Represents `SqlServerVersion`
     */
    @JacksonXmlProperty(localName = "SqlServerVersion")
    private String sqlServerVersion;

    /**
     * Represents `DateTimeFormat`
     */
    @JacksonXmlProperty(localName = "DateTimeFormat")
    private String dateTimeFormat = DATE_TIME_FORMAT;

    /***
     * Represents `DateTime`
     */
    @JacksonXmlProperty(localName = "DateTime")
    private String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));

    /**
     * Represents `LanguageUI`
     */
    @JacksonXmlProperty(localName = "LanguageUI")
    private String languageUI;

    /**
     * Represents `FileStamp`
     */
    @JacksonXmlProperty(localName = "FileStamp")
    private UUID fileStamp;

    /**
     * Represents `RelayInfo`
     */
    @JacksonXmlProperty(localName = "RelayInfo")
    private RelayInfo relayInfo;

    /**
     * Represents `ParameterSet`
     */
    @JacksonXmlProperty(localName = "ParameterSet")
    private ParameterSet parameterSet;
    
    @JacksonXmlElementWrapper(localName = "Blocks")
    @JacksonXmlProperty(localName = "Block")
    private List<Block> blocks;
    
    private Object enums;

    public String getComment() {
        return comment;
    }

    public RSEIContainer setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getVersionRSEI() {
        return versionRSEI;
    }

    public RSEIContainer setVersionRSEI(String versionRSEI) {
        this.versionRSEI = versionRSEI;
        return this;
    }

    public String getSoftwareSource() {
        return softwareSource;
    }

    public RSEIContainer setSoftwareSource(String softwareSource) {
        this.softwareSource = softwareSource;
        return this;
    }

    public String getMachineName() {
        return machineName;
    }

    public RSEIContainer setMachineName(String machineName) {
        this.machineName = machineName;
        return this;
    }

    public String getUserDomainName() {
        return userDomainName;
    }

    public RSEIContainer setUserDomainName(String userDomainName) {
        this.userDomainName = userDomainName;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public RSEIContainer setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getDataSource() {
        return dataSource;
    }

    public RSEIContainer setDataSource(String dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public String getDatabase() {
        return database;
    }

    public RSEIContainer setDatabase(String database) {
        this.database = database;
        return this;
    }

    public String getSqlServerVersion() {
        return sqlServerVersion;
    }

    public RSEIContainer setSqlServerVersion(String sqlServerVersion) {
        this.sqlServerVersion = sqlServerVersion;
        return this;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }

//    public RSEIContainer setDateTimeFormat(String dateTimeFormat) {
//        this.dateTimeFormat = dateTimeFormat;
//        return this;
//    }

    public String getDateTime() {
        return dateTime;
    }

//    public RSEIContainer setDateTime(String dateTime) {
//        this.dateTime = dateTime;
//        return this;
//    }

    public String getLanguageUI() {
        return languageUI;
    }

    public RSEIContainer setLanguageUI(String languageUI) {
        this.languageUI = languageUI;
        return this;
    }

    public UUID getFileStamp() {
        return fileStamp;
    }

    public RSEIContainer setFileStamp(UUID fileStamp) {
        this.fileStamp = fileStamp;
        return this;
    }

    public RelayInfo getRelayInfo() {
        return relayInfo;
    }

    public RSEIContainer setRelayInfo(RelayInfo relayInfo) {
        this.relayInfo = relayInfo;
        return this;
    }

    public ParameterSet getParameterSet() {
        return parameterSet;
    }

    public RSEIContainer setParameterSet(ParameterSet parameterSet) {
        this.parameterSet = parameterSet;
        return this;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public Object getEnums() {
        return enums;
    }

    public void setEnums(Object enums) {
        this.enums = enums;
    }
}
