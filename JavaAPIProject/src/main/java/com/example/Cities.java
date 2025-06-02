package com.example;
import java.util.ArrayList;

public class Cities {
    private ArrayList<City> cities = new ArrayList<City>(); 
    
    public Cities() {
        City albany = new City("Albany", "Mid-Atlantic", "New York", "Cradle of the Union");
        cities.add(albany); 
        City newYork = new City("New York City", "Mid-Atlantic", "Not Available", "Gotham");
        cities.add(newYork);
        City boston = new City("Boston", "New England", "Massachusetts", "Beantown");
        cities.add(boston);
        City providence = new City("Providence", "New England", "Rhode Island", "The Renaissance City");
        cities.add(providence);
        City philidelphia = new City("Philadelphia", "Mid-Atlantic", "Pennsylvania", "Brotherly Love");
        cities.add(philidelphia);
        City pittsburgh = new City("Pittsburgh", "Mid-Atlantic", "Pennsylvania", "Steel City");
        cities.add(pittsburgh);
        City washingtonDC = new City("Washington, D.C.", "Mid-Atlantic", "Not IN A State", "The District");
        cities.add(washingtonDC);
    
        City chicago = new City("Chicago", "Midwest", "Illinois", "The Windy City");
        cities.add(chicago);
        City minneapolis = new City("Minneapolis", "Midwest", "Minnesota", "City of Lakes");
        cities.add(minneapolis);
        City detroit = new City("Detroit", "Midwest", "Michigan", "Motor City");
        cities.add(detroit);
        City cleveland = new City("Cleveland", "Midwest", "Ohio", "Rock and Roll Capital");
        cities.add(cleveland);
        City stLouis = new City("St Louis", "Midwest", "Missouri", "Gateway City");
        cities.add(stLouis);

        City atlanta = new City("Atlanta", "Southeast", "Georgia", "The Big Peach");
        cities.add(atlanta);
        City miami = new City("Miami", "Southeast", "Florida", "Vice City");
        cities.add(miami);
        City orlando = new City("Orlando", "Southeast", "Florida", "Theme Park Capital of the World");
        cities.add(orlando);
        City nashville = new City("Nashville", "Southeast", "Tennessee", "Music City");
        cities.add(nashville);
        City charlotte = new City("Charlotte", "Southeast", "North Carolina", "The Queen City");
        cities.add(charlotte);

        City denver = new City("Denver", "Western Rockies", "Colorado", "The Mile High City");
        cities.add(denver);
        City houston = new City("Houston", "Southwest", "Texas", "Space City");
        cities.add(houston);
        City dallas = new City("Dallas", "Southwest", "Texas", "The Cowboys");
        cities.add(dallas);
        City phoenix = new City("Phoenix", "Southwest", "Arizona", "Valley of the Sun");
        cities.add(phoenix);

        City losAngeles = new City("Los Angeles", "Pacific Coast", "California", "City of Angels");
        cities.add(losAngeles);
        City sanDiego = new City("San Diego", "Pacific Coast", "California", "America's Finest City");
        cities.add(sanDiego);
        City sanFrancisco = new City("San Francisco", "Pacific Coast", "California", "The Golden City");
        cities.add(sanFrancisco);
        City sacramento = new City("Sacramento", "Pacific Coast", "California", "City of Trees");
        cities.add(sacramento);
        City seattle = new City("Seattle", "Pacific Coast", "Washington", "Emerald City");
        cities.add(seattle);
        City portland = new City("Portland", "Pacific Coast", "Oregon", "Rose City");
        cities.add(portland);

        City honolulu = new City ("Honolulu", "Pacific", "Hawaii", "The Crossroads of the Pacific");
        cities.add(honolulu);
        City sanJuan = new City("San Juan", "Caribbean", "U.S. Territory", "The Pearl of the Caribbean");
        cities.add(sanJuan);

        City milwaukee = new City("Milwaukee", "Great Lakes", "Wisconsin", "Brew City");
        cities.add(milwaukee); 
        City memphis = new City("Memphis", "Mid-South", "Tennessee", "Bluff City");
        cities.add(memphis);

        City indianapolis = new City("Indianapolis", "Mid-West", "Not Available", "Racing Capital");
        cities.add(indianapolis);
        City oklahomaCity = new City("Oklahoma City", "Great Plains", "Not Available", "The Big Friendly");
        cities.add(oklahomaCity);
        City saltLakeCity = new City("Salt Lake City", "Mountain West", "Utah", "The Crossroads of the West");
        cities.add(saltLakeCity);
        City newOrleans = new City("New Orleans", "Gulf Coast", "Louisiana", "The Birthplace of Jazz");
        cities.add(newOrleans);
    }

    
    public ArrayList<City> getCities() {
        return cities;
    }
}