package pl.wsinf.smarthome.server.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by Serhii Razovyi on 15-Oct-19.
 */
@JacksonXmlRootElement(localName = "division")
public class Division {

    /**
     * Attributes
     */
    @JacksonXmlProperty(isAttribute = true)
    private String id;
    @JacksonXmlProperty(isAttribute = true)
    private String name;
    @JacksonXmlProperty(isAttribute = true)
    private String refFloor;
    @JacksonXmlProperty(isAttribute = true)
    private String accessLevel;
    @JacksonXmlProperty(isAttribute = true)
    private String refDivisionType;

    /**
     * Empty constructor for XML de/serialization
     */
    public Division() {
    }

    /**
     * Constructor
     *
     * @param id              the id
     * @param name            the name
     * @param refFloor        the ref floor
     * @param accessLevel     the access level
     * @param refDivisionType the ref division type
     */
    public Division(String id, String name, String refFloor, String accessLevel, String refDivisionType) {
        this.id = id;
        this.name = name;
        this.refFloor = refFloor;
        this.accessLevel = accessLevel;
        this.refDivisionType = refDivisionType;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * Gets ref floor.
     *
     * @return the ref floor
     */
    public String getRefFloor() {
        return refFloor;
    }

    /**
     * Sets ref floor.
     *
     * @param refFloor the ref floor
     */
    public void setRefFloor(String refFloor) {
        this.refFloor = refFloor;
    }

    /**
     * Gets access level.
     *
     * @return the access level
     */
    public String getAccessLevel() {
        return accessLevel;
    }

    /**
     * Sets access level.
     *
     * @param accessLevel the access level
     */
    public void setAccessLevel(String accessLevel) {
        this.accessLevel = accessLevel;
    }

    /**
     * Gets ref division type.
     *
     * @return the ref division type
     */
    public String getRefDivisionType() {
        return refDivisionType;
    }

    /**
     * Sets ref division type.
     *
     * @param refDivisionType the ref division type
     */
    public void setRefDivisionType(String refDivisionType) {
        this.refDivisionType = refDivisionType;
    }

}
