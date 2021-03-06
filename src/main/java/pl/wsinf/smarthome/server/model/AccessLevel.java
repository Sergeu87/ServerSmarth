package pl.wsinf.smarthome.server.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by Serhii Razovyi on 15-Oct-19.
 */
public class AccessLevel {

    /**
     * Attributes
     */
    @JacksonXmlProperty(isAttribute = true)
    private int level;
    @JacksonXmlProperty(isAttribute = true)
    private String name;

    /**
     * Empty constructor for XML de/serialization
     */
    public AccessLevel() {
    }

    /**
     * Constructor
     *
     * @param level the level
     * @param name  the name
     */
    public AccessLevel(int level, String name) {
        this.level = level;
        this.name = name;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
