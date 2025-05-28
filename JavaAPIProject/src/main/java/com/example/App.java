package com.example; 
import org.json.JSONObject;
import java.net.URL; 
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.io.IOException;
import java.util.*;

public class App {
    private static int totalHints = 0;
    private static int totalPoints = 0; 
    private static int totalQuestions = 0;
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                             Hello! This is Aryan's USA Weather Exploration Game!\n Please indicate if you would like to play the Mystery City Game OR Find out the weather conditions in a specific U.S. City\n                Enter 1 for City Weather, 2 for City Detail, 3 for Simple Game, and 4 for the Challenging Game with Points");
        int choice = scanner.nextInt();
        if(choice == 1) {
            System.out.println("<<< Please enter the city that you would like data on: >>>");
            scanner.nextLine();
            String city = scanner.nextLine();
            System.out.println("___Here is the weather data for " + city + "___");
            System.out.println(API.getCityInformation(city));
        } else if (choice == 2) {
            System.out.println("<<< Please enter the city that you would like data on: >>>");
            scanner.nextLine();
            String city = scanner.nextLine().toLowerCase();
            System.out.println("---------------------------------------------------------------------------------");
            Cities cities = new Cities();
            ArrayList<City> citiesList = cities.getCities();
            City foundCity = null;
            for (int i = 0; i < citiesList.size(); i++) {
                String currentCityName = citiesList.get(i).getName().toLowerCase();
                if (currentCityName.equals(city)) {
                    foundCity = citiesList.get(i);
                    System.out.println("--------" + "Here are the details for " + citiesList.get(i).getName() + "--------");
                    System.out.println("Latitude: " + API.getLatitude(city));
                    System.out.println("Longitude: " + API.getLongitude(city));
                    System.out.println("This city is located in the region: " + foundCity.getRegion() );
                    System.out.print("This city is located in the state: ");
                    if (city.equals("new york city")) {
                        System.out.println("New York" + "\n");
                    } else if (city.equals("indianapolis")) {
                        System.out.println("Indiana" + "\n");
                    } else {
                        System.out.print(foundCity.getState() + "\n");
                    }
                    System.out.println("This city is known (for/as): " + foundCity.knownFor());
                }
            }
            if (foundCity == null) {
                System.out.println("Sorry, this city was not found in the system!");
            }
        } else if (choice == 3) {
            //map is displayed with the longitude and latitude
            System.out.println(); 
            System.out.println(" <<< Welcome to Aryan's Mystery City Game! \n\nIn this multiple-choice game, you would try to guess a popular city in the United States. \nYou will be given clues involving weather conditions, geography, and factual hints.");
            System.out.println();
            System.out.println("--------- Let's Begin! ---------");
            oneQuestion();
        } else if (choice == 4) {
            System.out.println();
            System.out.println("<<< Welcome to Aryan's Mystery City Game! \n\nIn this multiple choice game, you would try to guess a popular city in the United States. \n You would be given clues involving weather conditions, geography, and factual hints. \n\n The aim is to reach 60 points with the least amounts of guesses available." );
            oneQuestionCompetitiveGame();
        }
    }

    public static void oneQuestion() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Cities cities = new Cities();
        ArrayList<City> citiesList = cities.getCities();
        int totalCities = citiesList.size();
        int actualCityIndex = (int) (Math.random() * totalCities);
        City actualCity = citiesList.get(actualCityIndex);
        String actualCityName = actualCity.getName();

        int second = actualCityIndex;
        while (second == actualCityIndex) {
            second = (int) (Math.random() * totalCities);
        }
        City secondOption = citiesList.get(second);
        int third = actualCityIndex;
        while (third == actualCityIndex || third == second) {
            third = (int) (Math.random() * totalCities);
        }
        City thirdOption = citiesList.get(third);

        int fourth = actualCityIndex;
        while (fourth == actualCityIndex || fourth == second || fourth == third) {
            fourth = (int) (Math.random() * totalCities);
        }
        City fourthOption = citiesList.get(fourth);

        System.out.println();
        System.out.println("((((These are the weather details of the city:))))");
        System.out.println(API.getCityInformation(actualCityName));
        System.out.println();

        System.out.println("----Here are your options----");
        City[] options = {actualCity, secondOption, thirdOption, fourthOption};
        Collections.shuffle(Arrays.asList(options)); //shuffles the 4 options
        int answer = 0; 
        for (int i = 0; i < options.length; i++) {
            if (options[i].getName().equals(actualCityName)) {
                answer = i + 1;
            }
        }
        System.out.println("1:" + options[0].getName());
        System.out.println("2:" + options[1].getName());
        System.out.println("3:" + options[2].getName());
        System.out.println("4:" + options[3].getName());

        System.out.println();
        System.out.println("-----Do you have an answer?-----\n-----Enter YES to affirm yes OR Enter NO to get a Hint-----"); 
        String res = scanner.nextLine();
        if (res.toLowerCase().equals("yes")) {
            System.out.println("Great! Enter Your Answer!");
            int userAnswer = scanner.nextInt();
            if (userAnswer == answer) {
                System.out.println("✅ Correct!✅ ");
            } else {
                System.out.println(" ❌ Wrong!❌ The correct answer is " + answer + "!");
            }
        } else if (res.toLowerCase().equals("no")) {
            String hint = actualCity.knownFor();
            System.out.println();
            System.out.println("This city is known (for/as): " + hint);
            System.err.println();
            System.out.println("-----Do you have an answer?-----\n-----Enter YES to affirm yes OR Enter NO to get a Hint-----"); 
            String re = scanner.nextLine();
            if (re.toLowerCase().equals("yes")) {
                System.out.println("Great! Enter Your Answer!");
                int userAnswer = scanner.nextInt();
                if (userAnswer == answer) {
                    System.out.println(" ✅Correct!✅ ");
                } else {
                   System.out.println(" ❌ Wrong!❌ The correct answer is " + answer + "!");
                }
            } else if (re.toLowerCase().equals("no")) {
                int random = (int) (Math.random() * 2);
                System.out.println();
                if (random == 0) {
                    System.out.println("____This city is located in this state: " + actualCity.getState() + "____");
                } else {
                    System.out.println(" ____This city is located in this region of the country: " + actualCity.getRegion() + "____");
                }
                System.out.println();
                System.out.println("-----Please enter your answer now:-----");
                int userAnswer = scanner.nextInt();
                if (userAnswer == answer) {
                    System.out.println(" ✅Correct!✅ ");
                } else {
                    System.out.println(" ❌ Wrong!❌ The correct answer is " + answer + "!");
                }
            }
        }
        scanner.nextLine();
        System.out.println("_____ Would you like to go again? (YES OR NO) ____");
        String again = scanner.nextLine();
        if (again.toLowerCase().equals("no")) {
            System.out.println("Thanks for Playing!😊👌😁");
        } else if (again.toLowerCase().equals("yes")) {
            oneQuestion();;
        }
    }

    public static void oneQuestionCompetitiveGame() throws Exception {
        System.out.println("----------Current Stats:----------");
        System.out.println("Total Points: " + totalPoints);
        System.out.println("Total Guesses " + totalHints);
        System.out.println("Total Questions: " + totalQuestions);
    
        while (totalPoints < 60) {
             try {
                Thread.sleep(1000); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            totalQuestions++;
            Scanner scanner = new Scanner(System.in);
            Cities cities = new Cities();
            ArrayList<City> citiesList = cities.getCities();
            int totalCities = citiesList.size();
            int actualCityIndex = (int) (Math.random() * totalCities);
            City actualCity = citiesList.get(actualCityIndex);
            String actualCityName = actualCity.getName();
            int second = actualCityIndex;
            while (second == actualCityIndex) {
                second = (int) (Math.random() * totalCities);
            }
            City secondOption = citiesList.get(second);
            int third = actualCityIndex;
            while (third == actualCityIndex || third == second) {
                third = (int) (Math.random() * totalCities);
            }
            City thirdOption = citiesList.get(third);

            int fourth = actualCityIndex;
            while (fourth == actualCityIndex || fourth == second || fourth == third) {
                fourth = (int) (Math.random() * totalCities);
            }
            City fourthOption = citiesList.get(fourth);

            System.out.println();
            System.out.println("((((These are the weather details of the city:))))");
            System.out.println(API.getCityInformation(actualCityName));
            System.out.println();

            System.out.println("----Here are your options----");
            City[] options = {actualCity, secondOption, thirdOption, fourthOption};
            Collections.shuffle(Arrays.asList(options)); //shuffles the 4 options
            int answer = 0; 
            for (int i = 0; i < options.length; i++) {
                if (options[i].getName().equals(actualCityName)) {
                    answer = i + 1;
                }
            }
            System.out.println("1:" + options[0].getName());
            System.out.println("2:" + options[1].getName());
            System.out.println("3:" + options[2].getName());
            System.out.println("4:" + options[3].getName());

            System.out.println();
            System.out.println("-----Do you have an answer?-----\n-----Enter YES to affirm yes OR Enter NO to get a Hint-----"); 
            String res = scanner.nextLine();
            if (res.toLowerCase().equals("yes")) {
                System.out.println("Great! Enter Your Answer!");
                int userAnswer = scanner.nextInt();
                if (userAnswer == answer) {
                    totalPoints += 10;
                    System.out.println("✅ Correct!✅ ");
                } else {
                   System.out.println(" ❌ Wrong!❌ The correct answer is " + answer + "!");
                }
            } else if (res.toLowerCase().equals("no")) {
                totalHints++;
                String hint = actualCity.knownFor();
                System.out.println();
                System.out.println("This city is known (for/as): " + hint);
                System.err.println();
                System.out.println("-----Do you have an answer?-----\n-----Enter YES to affirm yes OR Enter NO to get a Hint-----"); 
                String re = scanner.nextLine();
                if (re.toLowerCase().equals("yes")) {
                    System.out.println("Great! Enter Your Answer!");
                    int userAnswer = scanner.nextInt();
                    if (userAnswer == answer) {
                        System.out.println(" ✅Correct!✅ ");
                        totalPoints += 6;
                    } else {
                        System.out.println(" ❌ Wrong!❌ The correct answer is " + answer + "!");
                    }
                } else if (re.toLowerCase().equals("no")) {
                    totalHints++;
                    int random = (int) (Math.random() * 2);
                    System.out.println();
                    if (random == 0) {
                        System.out.println("____This city is located in this state: " + actualCity.getState() + "_____");
                    } else {
                        System.out.println(" ____This city is located in this region of the country: " + actualCity.getRegion() + "____");
                    }
                    System.out.println();
                    System.out.println("-----Please enter your answer now:-----");
                    int userAnswer = scanner.nextInt();
                    if (userAnswer == answer) {
                        System.out.println(" ✅Correct!✅ ");
                        totalPoints += 3;
                    } else {
                       System.out.println(" ❌ Wrong!❌ The correct answer is " + answer + "!");
                    }
                }
            }
            System.out.println("Total Points: " + totalPoints);
            System.out.println("Total Guesses: " + totalHints);
            System.out.println("Total Questions: " + totalQuestions);
        }
        System.out.println("YAY! You have completed this challenge! You reached " + totalPoints + " with " + totalHints + " hints in " +  totalQuestions + " questions!");
    }
}
