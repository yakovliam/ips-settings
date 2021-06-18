package com.tarigma.ipssettings.model.parameter.localization;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.HashSet;

public class Localization {

    /**
     * Represents `<Name Lang3="[]"></Name>"
     */
    @JacksonXmlElementWrapper(localName = "Name", useWrapping = false)
    @JacksonXmlProperty(localName = "Name")
    private final HashSet<LocalizationName> localizationNames;

    /**
     * Represents `<Description Lang3="[]"></Description>"
     */
    @JacksonXmlElementWrapper(localName = "Description", useWrapping = false)
    @JacksonXmlProperty(localName = "Description")
    private final HashSet<LocalizationDescription> localizationDescriptions;

    /**
     * Represents `<Unit Lang3="[]"></Unit>`
     */
    @JacksonXmlElementWrapper(localName = "Unit", useWrapping = false)
    @JacksonXmlProperty(localName = "Unit")
    private final HashSet<LocalizationUnit> localizationUnits;

    /**
     * Construct localization
     */
    public Localization() {
        this.localizationNames = new HashSet<>();
        this.localizationNames.add(new LocalizationName("ENU", null));
        this.localizationNames.add(new LocalizationName("DEU", null));
        this.localizationNames.add(new LocalizationName("FRA", null));
        this.localizationNames.add(new LocalizationName("ESP", null));

        this.localizationUnits = new HashSet<>();
        this.localizationUnits.add(new LocalizationUnit("ENU", null));
        this.localizationUnits.add(new LocalizationUnit("DEU", null));
        this.localizationUnits.add(new LocalizationUnit("FRA", null));
        this.localizationUnits.add(new LocalizationUnit("ESP", null));

        this.localizationDescriptions = new HashSet<>();
        this.localizationDescriptions.add(new LocalizationDescription("ENU", null));
        this.localizationDescriptions.add(new LocalizationDescription("DEU", null));
        this.localizationDescriptions.add(new LocalizationDescription("FRA", null));
        this.localizationDescriptions.add(new LocalizationDescription("ESP", null));
    }

    public Localization setEnuLang3Name(String enuLang3Name) {
        this.localizationNames.add(new LocalizationName("ENU", enuLang3Name));
        return this;
    }


    public Localization setEnuLang3Description(String enuLang3Description) {
        this.localizationDescriptions.add(new LocalizationDescription("ENU", enuLang3Description));
        return this;
    }

    public Localization setEnuLang3Unit(LocalizationUnit enuLang3Unit) {
        this.localizationUnits.add(enuLang3Unit);
        return this;
    }

    public Localization setDeuLang3Name(String deuLang3Name) {
        this.localizationNames.add(new LocalizationName("DEU", deuLang3Name));
        return this;
    }

    public Localization setDeuLang3Description(String deuLang3Description) {
        this.localizationDescriptions.add(new LocalizationDescription("DEU", deuLang3Description));
        return this;
    }

    public Localization setDeuLang3Unit(LocalizationUnit deuLang3Unit) {
        this.localizationUnits.add(deuLang3Unit);
        return this;
    }

    public Localization setFraLang3Name(String fraLang3Name) {
        this.localizationNames.add(new LocalizationName("FRA", fraLang3Name));
        return this;
    }

    public Localization setFraLang3Description(String fraLang3Description) {
        this.localizationDescriptions.add(new LocalizationDescription("FRA", fraLang3Description));
        return this;
    }

    public Localization setFraLang3Unit(LocalizationUnit fraLang3Unit) {
        this.localizationUnits.add(fraLang3Unit);
        return this;
    }

    public Localization setEspLang3Name(String espLang3Name) {
        this.localizationNames.add(new LocalizationName("ESP", espLang3Name));
        return this;
    }

    public Localization setEspLang3Description(String espLang3Description) {
        this.localizationDescriptions.add(new LocalizationDescription("ESP", espLang3Description));
        return this;
    }

    public Localization setEspLang3Unit(LocalizationUnit espLang3Unit) {
        this.localizationUnits.add(espLang3Unit);
        return this;
    }

    public HashSet<LocalizationName> getLocalizationNames() {
        return localizationNames;
    }

    public HashSet<LocalizationDescription> getLocalizationDescriptions() {
        return localizationDescriptions;
    }

    public HashSet<LocalizationUnit> getLocalizationUnits() {
        return localizationUnits;
    }
}
