package com.example.lifelapseapp.model;

import java.io.Serializable;
import java.util.List;

/**
 * Api character class to hold the elements of the a character
 */

public class ApiCharacter implements Serializable {

    private String id;
    private String name, status, species, type, gender, image, url;
    private List<String> episodes;
    private Location origin, currentLocation;

    public ApiCharacter(String id, String name, String status,
                        String species, String type, String gender,
                        String image,String url, List<String> episodes, Location origin,
                        Location currentLocation) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.image = image;
        this.episodes = episodes;
        this.origin = origin;
        this.currentLocation = currentLocation;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<String> episodes) {
        this.episodes = episodes;
    }

    public Location getOrigin() {
        return origin;
    }

    public void setOrigin(Location origin) {
        this.origin = origin;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    @Override
    public String toString() {
        return "" +
                "id: " + id + "\n" +
                "name: " + name + "\n" +
                "status: " + status + "\n" +
                "species: " + species + "\n" +
                "type: " + type + "\n" +
                "gender: " + gender + "\n" +
                "image: " + image + "\n" +
                "url: " + url + "\n\n" +
                "episodes: " + episodes + "\n\n" +
                "origin: " + origin + "\n" +
                "currentLocation: " + currentLocation + "\n";
    }
}
