
package com.aiculabs.melchord.data.model;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ArtistSearch {

    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     */
    public ArtistSearch() {
    }

    /**
     * @param mbid
     * @param area
     * @param name
     * @param score
     * @param type
     * @param comment
     */
    public ArtistSearch(String area, String comment, String type, Integer score, String mbid, String name) {
        this.area = area;
        this.comment = comment;
        this.type = type;
        this.score = score;
        this.mbid = mbid;
        this.name = name;
    }

    /**
     * @return The area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area The area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return The comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment The comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return The score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * @param score The score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @return The mbid
     */
    public String getMbid() {
        return mbid;
    }

    /**
     * @param mbid The mbid
     */
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

}