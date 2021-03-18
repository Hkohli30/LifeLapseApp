package com.example.lifelapseapp.model;

import java.io.Serializable;

/**
 * Location to hold the origin and current location elements of character
 */
public class Location implements Serializable {

    private String name;
    private String url;

    public Location(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "" +
                "name: '" + name + "\n" +
                "url: " + url + "\n";
    }
}
