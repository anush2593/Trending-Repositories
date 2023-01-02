package com.anush.trendingrepositories.models;

public class Repository {
    private String name;
    private String description;
    private Owner owner;
    private Integer starsCount;

    public Repository(String name, String description, Owner owner, Integer starsCount) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.starsCount = starsCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Integer getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(Integer starsCount) {
        this.starsCount = starsCount;
    }
}
