package com.anush.trendingrepositories.models;

import java.util.Objects;

public class Repository {
    private Integer id;
    private String name;
    private String description;
    private Owner owner;
    private Integer starsCount;

    public Repository(Integer id, String name, String description, Owner owner, Integer starsCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.starsCount = starsCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescriptionFormatted() {
        if (description == null || description.isEmpty()) {
            return "Description is missing";
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(owner, that.owner) && Objects.equals(starsCount, that.starsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, owner, starsCount);
    }
}
