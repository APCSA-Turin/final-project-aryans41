package com.example;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.HttpURLConnection;
import java.net.URI;
import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.io.IOException; 
import java.util.Scanner;

public class API {
    static final String apiKey = "d34f54f2a2caad463e41f92915d5b7d4"; 

    public static String getCoordinates(String city) throws Exception {
        city = city.replaceAll(" ", "+"); //helps concatenate the city for the url(it can't have spaces)
        String urlStr = "https://api.openweathermap.org/geo/1.0/direct?q=" + city + ",US&limit=1&appid=" + apiKey;
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;//variable to store text, line by line
        StringBuilder content = new StringBuilder();
        while ((inputLine = buff.readLine()) != null) {
            content.append(inputLine);
        }
        buff.close();
        connection.disconnect(); 
        return content.toString();
    }
 
    public static double getLatitude(String city) throws Exception {
        String response = getCoordinates(city);
        JSONArray arr = new JSONArray(response);
        JSONObject res = arr.getJSONObject(0);
        double latitude = res.getDouble("lat");
        return latitude;
    }

    public static double getLongitude(String city) throws Exception {
        String response = getCoordinates(city);
        JSONArray arr = new JSONArray(response);
        JSONObject res = arr.getJSONObject(0);
        double longitude = res.getDouble("lon");
        return longitude;
    }


    public static String getCityData(String city) throws Exception {
        double lat = getLatitude(city);
        double lon = getLongitude(city);
        String urlStr = "https://api.openweathermap.org/data/3.0/onecall?lat=" + lat + "&lon=" + lon + "&exclude={part}&appid=" + apiKey;
        /*endpoint is a url (string) that you get from an API website*/
        URL url = new URL(urlStr);
        /*connect to the URL*/
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        /*creates a GET request to the API.. Asking the server to retrieve information for our program*/
        connection.setRequestMethod("GET");
        /* When you read data from the server, it wil be in bytes, the InputStreamReader will convert it to text. 
        The BufferedReader wraps the text in a buffer so we can read it line by line*/
        BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;//variable to store text, line by line
        /*A string builder is similar to a string object but faster for larger strings, 
        you can concatenate to it and build a larger string. Loop through the buffer 
        (read line by line). Add it to the stringbuilder */
        StringBuilder content = new StringBuilder();
        while ((inputLine = buff.readLine()) != null) {
            content.append(inputLine);
        }
        buff.close(); //close the bufferreader
        connection.disconnect(); //disconnect from server 
        return content.toString(); //return the content as a string
    }

    public static String getCityInformation(String city) throws Exception {
        String information = "";
        String response = getCityData(city);
        JSONObject arr = new JSONObject(response);
        double longitude = getLongitude(city) * -1;
        double latitude = getLatitude(city);
        information += "----" + "The City is located:" + "----\n";
        information += "Latitude: " + latitude + "° N" + "\n";
        information += "Longitude: " + longitude + "° W" + "\n";

        information += "----Weather Details:" + "----\n";
        double temperature = arr.getJSONObject("current").getDouble("temp");
        double inFar = Math.round((temperature - 273.15) * 9.0/5 + 32) * 100.0/100.0; //calculates the temperature in Fahrenheit rounded
        information += "Temperature: " + inFar + "° F";
        if (inFar <= 50) {
            information += " (cold weather)" + "\n";
        } 
        if (inFar > 50 && inFar <= 70) {
            information += " (mild weather)" + "\n";
        } 
        if (inFar > 70 && inFar <= 85) {
            information += " (warm weather)" + "\n";
        }
        if (inFar > 85) {
            information += " (hot weather)" + "\n";
        }

        double pressure = arr.getJSONObject("current").getDouble("pressure");
        information += "Pressure: " + pressure + " hPa";
        if (pressure < 1000) {
            information += " (low pressure - stormy or unstable weather)" + "\n";
        }
        if (pressure > 1000 && pressure <= 1025) {
            information += " (normal - fair, calm weather)" + "\n";
        }
        if (pressure > 1025) {
            information += " (high pressure - very stable, often clear skies)" + "\n";
        }

        double humidity = arr.getJSONObject("current").getDouble("humidity");
        information += "Humidity: " + humidity + "%";
        if (humidity <= 40) {
            information += " (dry)" + "\n";
        }
        if (humidity > 40 && humidity <= 70) {
            information += " (comfortable)" + "\n";
        }
        if (humidity > 70 && humidity <= 100) {
            information += " (humid)" + "\n";
        }

        double uvi = arr.getJSONObject("current").getDouble("uvi");
        information += "Ultaviolet Index: " + uvi;
        if (uvi <= 3) {
            information += " (low risk)" + "\n";
        }
        if (uvi >= 3 && uvi < 8) {
            information += " (moderate to high risk)" + "\n";
        }
        if (uvi >= 8) {
            information += " (very high to extreme risk)" + "\n";
        }


        double clouds = arr.getJSONObject("current").getDouble("clouds");
        information += "Cloud Cover: " + clouds + "%";
        if (clouds >= 0 && clouds <= 31) {
            information += " (clear)" + "\n";
        }
        if (clouds > 31 && clouds <= 71) {
            information += " (partly cloudy)" + "\n";
        }
        if (clouds > 71) {
            information += " (overcast)" + "\n";
        }

        double visibility = arr.getJSONObject("current").getDouble("visibility");
        information += "Visibility: " + visibility/1000 + " km";
        if (visibility >= 0 && visibility <= 6) {
            information += " (poor)" + "\n";
        }
        if (visibility >= 6 && visibility <= 12) {
            information += " (moderate)" + "\n";
        }
        if (visibility > 12) {
            information += " (good to excellent)" + "\n";
        }


        double windSpeed = arr.getJSONObject("current").getDouble("wind_speed");
        information += "Windspeed: " + windSpeed + " m/s";
        if (windSpeed > 0 && windSpeed <= 10) {
            information += " (light breeze)" + "\n";
        }
        if (windSpeed > 10 && windSpeed <= 25) {
            information += " (moderate wind)" + "\n";
        }
        if (windSpeed > 26) {
            information += " (strong/potentially hazardous)" + "\n";
        }

        String summary = arr.getJSONArray("daily").getJSONObject(0).getString("summary");
        information += "Summary: " + summary;
        return information;
    }
}