package pl.wsinf.smarthome.server.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by Serhii Razovyi on 02-Nov-19.
 */
@JacksonXmlRootElement(localName = "division_type")
public class DivisionType {

    /**
     * Attributes
     */
    @JacksonXmlProperty(isAttribute = true)
    private String id;
    @JacksonXmlProperty(isAttribute = true)
    private String name;

    /**
     * Empty constructor for XML de/serialization
     */
    public DivisionType() {
    }

    /**
     * Constructor
     *
     * @param id   the id
     * @param name the name
     */
    public DivisionType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getID() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

}
