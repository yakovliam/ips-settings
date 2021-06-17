package com.tarigma.ipssettings.model;

import java.util.UUID;

public class RSEIContainer {

    /**
     * Represents `Comment`
     */
    private String comment;

    /**
     * Represents `VersionRSEI`
     */
    private String versionRSEI;

    /**
     * Represents `SoftwareSource`
     */
    private String softwareSource;

    /**
     * Represents `MachineName`
     */
    private String machineName;

    /**
     * Represents `UserDomainName`
     */
    private String userDomainName;

    /**
     * Represents `UserName`
     */
    private String userName;

    /**
     * Represents `DataSource`
     */
    private String dataSource;

    /**
     * Represents `Database`
     */
    private String database;

    /**
     * Represents `SqlServerVersion`
     */
    private String sqlServerVersion;

    /**
     * Represents `DateTimeFormat`
     */
    private String dateTimeFormat;

    /***
     * Represents `DateTime`
     */
    private String dateTime;

    /**
     * Represents `LanguageUI`
     */
    private String languageUI;

    /**
     * Represents `FileStamp`
     */
    private UUID fileStamp;

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

    public RSEIContainer setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
        return this;
    }

    public String getDateTime() {
        return dateTime;
    }

    public RSEIContainer setDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

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
}
