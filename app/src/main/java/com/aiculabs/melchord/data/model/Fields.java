
package com.aiculabs.melchord.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Fields {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("founded")
    @Expose
    private Object founded;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("large_image")
    @Expose
    private Object largeImage;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Fields() {
    }

    /**
     * 
     * @param founded
     * @param thumbnail
     * @param name
     * @param largeImage
     * @param country
     */
    public Fields(String name, Object founded, String country, String thumbnail, Object largeImage) {
        this.name = name;
        this.founded = founded;
        this.country = country;
        this.thumbnail = thumbnail;
        this.largeImage = largeImage;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The founded
     */
    public Object getFounded() {
        return founded;
    }

    /**
     * 
     * @param founded
     *     The founded
     */
    public void setFounded(Object founded) {
        this.founded = founded;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The largeImage
     */
    public Object getLargeImage() {
        return largeImage;
    }

    /**
     * 
     * @param largeImage
     *     The large_image
     */
    public void setLargeImage(Object largeImage) {
        this.largeImage = largeImage;
    }


}
