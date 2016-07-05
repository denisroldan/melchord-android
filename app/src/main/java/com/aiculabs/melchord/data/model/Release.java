package com.aiculabs.melchord.data.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Release {

    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("large_image")
    @Expose
    private String largeImage;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("song_set")
    @Expose
    private List<Song> songSet = new ArrayList<Song>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Release() {
    }

    /**
     *
     * @param songSet
     * @param date
     * @param title
     * @param thumbnail
     * @param mbid
     * @param largeImage
     * @param type
     */
    public Release(String mbid, String title, String date, String thumbnail, String largeImage, String type, List<Song> songSet) {
        this.mbid = mbid;
        this.title = title;
        this.date = date;
        this.thumbnail = thumbnail;
        this.largeImage = largeImage;
        this.type = type;
        this.songSet = songSet;
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
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
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
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The songSet
     */
    public List<Song> getSongSet() {
        return songSet;
    }

    /**
     *
     * @param songSet
     * The song_set
     */
    public void setSongSet(List<Song> songSet) {
        this.songSet = songSet;
    }

}