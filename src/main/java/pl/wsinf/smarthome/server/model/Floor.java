package pl.wsinf.smarthome.server.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by Serhii Razovyi on 15-Oct-19.
 */
@JacksonXmlRootElement(localName = "floor")
public class Floor {

    /**
     * Attributes
     */
    @JacksonXmlProperty(isAttribute = true)
    private String id;
    @JacksonXmlProperty(isAttribute = true)
    private String name;
    @JacksonXmlProperty(isAttribute = true)
    private String heightOrder;

    /**
     * Empty constructor for XML de/serialization
     */
    public Floor() {
    }

    /**
     * Constructor
     *
     * @param id          the id
     * @param name        the name
     * @param heightOrder the height order
     */
    public Floor(String id, String name, String heightOrder) {
        this.id = id;
        this.name = name;
        this.heightOrder = heightOrder;
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
     * Gets height order.
     *
     * @return the height order
     */
    public String getHeightOrder() {
        return heightOrder;
    }

    /**
     * Sets height order.
     *
     * @param heightOrder the height order
     */
    public void setHeightOrder(String heightOrder) {
        this.heightOrder = heightOrder;
    }
}