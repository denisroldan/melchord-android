package com.aiculabs.melchord.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

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
     *
     */
    public Link() {
    }

    /**
     *
     * @param target
     * @param type
     */
    public Link(String target, String type) {
        this.target = target;
        this.type = type;
    }

    /**
     *
     * @return
     * The target
     */
    public String getTarget() {
        return target;
    }

    /**
     *
     * @param target
     * The target
     */
    public void setTarget(String target) {
        this.target = target;
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

}