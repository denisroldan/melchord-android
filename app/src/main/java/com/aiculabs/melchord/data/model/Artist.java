package com.aiculabs.melchord.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Artist {

    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("founded")
    @Expose
    private Object founded;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("large_image")
    @Expose
    private String largeImage;
    @SerializedName("release_set")
    @Expose
    private List<Release> releaseSet = new ArrayList<Release>();
    @SerializedName("links")
    @Expose
    private List<Link> links = new ArrayList<Link>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Artist() {
    }

    /**
     *
     * @param founded
     * @param thumbnail
     * @param bio
     * @param mbid
     * @param name
     * @param largeImage
     * @param releaseSet
     * @param links
     * @param country
     */
    public Artist(String mbid, String name, Object founded, String country, String bio, String thumbnail, String largeImage, List<Release> releaseSet, List<Link> links) {
        this.mbid = mbid;
        this.name = name;
        this.founded = founded;
        this.country = country;
        this.bio = bio;
        this.thumbnail = thumbnail;
        this.largeImage = largeImage;
        this.releaseSet = releaseSet;
        this.links = links;
    }

    /**
     *
     * @return
     * The mbid
     */
    public String getMbid() {
        return mbid;
    }

    /**
     *
     * @param mbid
     * The mbid
     */
    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The founded
     */
    public Object getFounded() {
        return founded;
    }

    /**
     *
     * @param founded
     * The founded
     */
    public void setFounded(Object founded) {
        this.founded = founded;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The bio
     */
    public String getBio() {
        return bio;
    }

    /**
     *
     * @param bio
     * The bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     *
     * @return
     * The thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     *
     * @param thumbnail
     * The thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     *
     * @return
     * The largeImage
     */
    public String getLargeImage() {
        return largeImage;
    }

    /**
     *
     * @param largeImage
     * The large_image
     */
    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    /**
     *
     * @return
     * The releaseSet
     */
    public List<Release> getReleaseSet() {
        return releaseSet;
    }

    /**
     *
     * @param releaseSet
     * The release_set
     */
    public void setReleaseSet(List<Release> releaseSet) {
        this.releaseSet = releaseSet;
    }

    /**
     *
     * @return
     * The releaseSet
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     *
     * @param releaseSet
     * The release_set
     */
    public void setLinks(List<Link> links) {
        this.links = links;
    }
}