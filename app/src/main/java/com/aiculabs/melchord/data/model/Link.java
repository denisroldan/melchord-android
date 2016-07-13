package com.aiculabs.melchord.data.model;

import com.aiculabs.melchord.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Link {

    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     */
    public Link() {
    }

    /**
     * @param target
     * @param type
     */
    public Link(String target, String type) {
        this.target = target;
        this.type = type;
    }

    /**
     * @return The target
     */
    public String getTarget() {
        return target;
    }

    public String getTitle() {

        if (this.getType().equals("DC")) return "Discogs";
        if (this.getType().equals("GP")) return "Google Play";
        if (this.getType().equals("IG")) return "Instagram";
        if (this.getType().equals("IB")) return "IMDB";
        if (this.getType().equals("DZ")) return "Deezer";
        if (this.getType().equals("WP")) return "Wikipedia";
        if (this.getType().equals("LY")) return "Lyrics";
        if (this.getType().equals("LF")) return "Last.fm";
        if (this.getType().equals("FB")) return "Facebook";
        if (this.getType().equals("SC")) return "SoundCloud";
        if (this.getType().equals("DB")) return "Other Database";
        if (this.getType().equals("IM")) return "Image";
        if (this.getType().equals("HO")) return "HomePage";
        if (this.getType().equals("SP")) return "Spotify";
        if (this.getType().equals("YT")) return "YouTube";
        if (this.getType().equals("TW")) return "Twitter";
        if (this.getType().equals("IT")) return "iTunes";
        if (this.getType().equals("MS")) return "MySpace";
        if (this.getType().equals("AM")) return "All Music";

        return "Other link";
    }

    public Integer getDrawableId() {
        if (this.getType().equals("GP")) return R.drawable.play;
        if (this.getType().equals("IG")) return R.drawable.instagram;
        if (this.getType().equals("DZ")) return R.drawable.deezer;
        if (this.getType().equals("WP")) return R.drawable.wikipedia;
        if (this.getType().equals("LF")) return R.drawable.lastfm;
        if (this.getType().equals("FB")) return R.drawable.facebook;
        if (this.getType().equals("SC")) return R.drawable.soundcloud;
        if (this.getType().equals("HO")) return R.drawable.home;
        if (this.getType().equals("SP")) return R.drawable.spotify;
        if (this.getType().equals("YT")) return R.drawable.youtube;
        if (this.getType().equals("TW")) return R.drawable.twitter;
        if (this.getType().equals("IT")) return R.drawable.itunes;
        if (this.getType().equals("MS")) return R.drawable.myspace;
        return R.drawable.default_ic;
    }

    /**
     * @param target The target
     */
    public void setTarget(String target) {
        this.target = target;
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

}