package com.aiculabs.melchord.data.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class SongSet {

    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("length")
    @Expose
    private String length;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("tab_set")
    @Expose
    private List<Object> tabSet = new ArrayList<Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public SongSet() {
    }

    /**
     *
     * @param title
     * @param mbid
     * @param tabSet
     * @param length
     * @param number
     */
    public SongSet(String mbid, String title, String length, Integer number, List<Object> tabSet) {
        this.mbid = mbid;
        this.title = title;
        this.length = length;
        this.number = number;
        this.tabSet = tabSet;
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
     * The length
     */
    public String getLength() {
        return length;
    }

    /**
     *
     * @param length
     * The length
     */
    public void setLength(String length) {
        this.length = length;
    }

    /**
     *
     * @return
     * The number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     *
     * @param number
     * The number
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     *
     * @return
     * The tabSet
     */
    public List<Object> getTabSet() {
        return tabSet;
    }

    /**
     *
     * @param tabSet
     * The tab_set
     */
    public void setTabSet(List<Object> tabSet) {
        this.tabSet = tabSet;
    }

}