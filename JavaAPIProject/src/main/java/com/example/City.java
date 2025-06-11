package com.example; 

//this class helps represent a single US city and its attributes in a structured format
public class City  {
    private String name;         //official city name
    private String region;      //geographic area like "Pacific Coast"
    private String state;      //containing state
    private String knownFor;  //famous characteristic

    //this constructor initializes all attributes(instance variables) when creating a new City object
    //Example: City seattle = new City("Seattle", "Pacific Coast", "Washington", "Emerald City");
    public City (String name, String region, String state, String knownFor) {
        this.name = name;
        this.region = region;
        this.state = state;
        this.knownFor = knownFor; 
    }

    //These four getter methods provide controlled access to the city's attributes
    //Application: These methods are used throughout the program to display city information, generate game hints, and compare user guesses. 
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