
package com.aiculabs.melchord.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class FeaturedArtist {

    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("pk")
    @Expose
    private String pk;
    @SerializedName("fields")
    @Expose
    private Fields fields;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FeaturedArtist() {
    }

    /**
     * 
     * @param model
     * @param fields
     * @param pk
     */
    public FeaturedArtist(String model, String pk, Fields fields) {
        this.model = model;
        this.pk = pk;
        this.fields = fields;
    }

    /**
     * 
     * @return
     *     The model
     */
    public String getModel() {
        return model;
    }

    /**
     * 
     * @param model
     *     The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 
     * @return
     *     The pk
     */
    public String getPk() {
        return pk;
    }

    /**
     * 
     * @param pk
     *     The pk
     */
    public void setPk(String pk) {
        this.pk = pk;
    }

    /**
     * 
     * @return
     *     The fields
     */
    public Fields getFields() {
        return fields;
    }

    /**
     * 
     * @param fields
     *     The fields
     */
    public void setFields(Fields fields) {
        this.fields = fields;
    }

}
