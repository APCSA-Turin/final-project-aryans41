package com.example; 
public class City  {
    private String name;
    private String region;
    private String state; 
    private String knownFor; 

    public City (String name, String region, String state, String knownFor) {
        this.name = name;
        this.region = region;
        this.state = state;
        this.knownFor = knownFor; 
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getState() {
        return state;
    }

    public String knownFor() {
        return knownFor;
    }
}