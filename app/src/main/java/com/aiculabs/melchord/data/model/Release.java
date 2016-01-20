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
    @SerializedName("launched")
    @Expose
    private String launched;
    @SerializedName("thumbnail")
    @Expose
    private Object thumbnail;
    @SerializedName("large_image")
    @Expose
    private Object largeImage;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("song_set")
    @Expose
    private List<SongSet> songSet = new ArrayList<SongSet>();

    /**
     * No args constructor for use in serialization
     *
     */
    public Release() {
    }

    /**
     *
     * @param songSet
     * @param launched
     * @param title
     * @param thumbnail
     * @param mbid
     * @param largeImage
     * @param type
     */
    public Release(String mbid, String title, String launched, Object thumbnail, Object largeImage, String type, List<SongSet> songSet) {
        this.mbid = mbid;
        this.title = title;
        this.launched = launched;
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
     * The launched
     */
    public String getLaunched() {
        return launched;
    }

    /**
     *
     * @param launched
     * The launched
     */
    public void setLaunched(String launched) {
        this.launched = launched;
    }

    /**
     *
     * @return
     * The thumbnail
     */
    public Object getThumbnail() {
        return thumbnail;
    }

    /**
     *
     * @param thumbnail
     * The thumbnail
     */
    public void setThumbnail(Object thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     *
     * @return
     * The largeImage
     */
    public Object getLargeImage() {
        return largeImage;
    }

    /**
     *
     * @param largeImage
     * The large_image
     */
    public void setLargeImage(Object largeImage) {
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
    public List<SongSet> getSongSet() {
        return songSet;
    }

    /**
     *
     * @param songSet
     * The song_set
     */
    public void setSongSet(List<SongSet> songSet) {
        this.songSet = songSet;
    }

}