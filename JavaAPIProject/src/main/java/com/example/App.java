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

    //text colors 
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //text backgrounds
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.println(ANSI_BLUE + "                             Hello! This is Aryan's USA Weather Exploration Game!\n                            Please indicate the feature that you would like to use\n                Enter 1 for City Weather, 2 for City Detail, 3 for Simple Game, and 4 for the Challenging Game with Points" + ANSI_WHITE);
        int choice = scanner.nextInt();
        if(choice == 1) {
            System.out.println("<<< Please enter the city that you would like data on: >>>");
            scanner.nextLine();
            String city = scanner.nextLine();
            System.out.println(ANSI_GREEN + "___Here is the weather data for " + city + "___" + ANSI_WHITE);
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
                    System.out.println(ANSI_PURPLE + "--------" + "Here are the details for " + citiesList.get(i).getName() + "--------" + ANSI_WHITE);
                    System.out.println("Latitude: " + API.getLatitude(city));
                    System.out.println("Longitude: " + API.getLongitude(city));
                    System.out.println("This city is located in the region: " + foundCity.getRegion() );
                    System.out.print("This city is located in the state: ");
                    if (city.equals("new york city")) {
                        System.out.print("New York" + "\n");
                    } else if (city.equals("indianapolis")) {
                        System.out.print("Indiana" + "\n");
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
            System.out.println(ANSI_CYAN + " <<< Welcome to Aryan's Mystery City Game! \n\nIn this multiple-choice game, you would try to guess a popular city in the United States. \nYou will be given clues involving weather conditions, geography, and factual hints." + ANSI_WHITE);
            System.out.println();
            System.out.println("--------- Let's Begin! ---------");
            oneQuestion();
        } else if (choice == 4) {
            System.out.println();
            System.out.println(ANSI_CYAN + "<<< Welcome to Aryan's Mystery City Game! \n\nIn this multiple choice game, you would try to guess a popular city in the United States. \n You would be given clues involving weather conditions, geography, and factual hints. \n\n The aim is to reach 60 points with the least amounts of guesses available." + ANSI_WHITE);
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
        System.out.println(ANSI_BLUE + "((((These are the weather details of the city:))))" + ANSI_WHITE);
        System.out.println(API.getCityInformation(actualCityName));
        System.out.println();

        System.out.println(ANSI_RED + "----Here are your options----" + ANSI_WHITE);
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
                System.out.println(ANSI_PURPLE + "✅ Correct!✅ " + ANSI_WHITE);
            } else {
                System.out.println(ANSI_YELLOW + " ❌ Wrong!❌ The correct answer is " + answer + "!" + ANSI_WHITE);
            }
        } else if (res.toLowerCase().equals("no")) {
            String hint = actualCity.knownFor();
            System.out.println();
            System.out.println(ANSI_GREEN + "This city is known (for/as): " + hint + ANSI_WHITE);
            System.out.println();
            System.out.println("-----Do you have an answer?-----\n-----Enter YES to affirm yes OR Enter NO to get a Hint-----"); 
            String re = scanner.nextLine();
            if (re.toLowerCase().equals("yes")) {
                System.out.println("Great! Enter Your Answer!");
                int userAnswer = scanner.nextInt();
                if (userAnswer == answer) {
                    System.out.println(ANSI_PURPLE + "✅ Correct!✅ " + ANSI_WHITE);
                } else {
                   System.out.println(ANSI_YELLOW + " ❌ Wrong!❌ The correct answer is " + answer + "!" + ANSI_WHITE);
                }
            } else if (re.toLowerCase().equals("no")) {
                int random = (int) (Math.random() * 2);
                System.out.println();
                if (random == 0) {
                    System.out.println(ANSI_PURPLE + "This city is located in this state: " + actualCity.getState() + ANSI_WHITE);
                } else {
                    System.out.println(ANSI_GREEN + "This city is located in this region of the country: " + actualCity.getRegion()  + ANSI_WHITE);
                }
                System.out.println();
                System.out.println("-----Please enter your answer now:-----");
                int userAnswer = scanner.nextInt();
                if (userAnswer == answer) {
                    System.out.println(ANSI_PURPLE + "✅ Correct!✅ " + ANSI_WHITE);
                } else {
                    System.out.println(ANSI_YELLOW + " ❌ Wrong!❌ The correct answer is " + answer + "!" + ANSI_WHITE);
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
        System.out.println(ANSI_GREEN + "----------Current Stats:----------");
        System.out.println("Total Points: " + totalPoints);
        System.out.println("Total Guesses " + totalHints);
        System.out.println("Total Questions: " + totalQuestions + ANSI_WHITE);
    
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
            System.out.println(ANSI_BLUE + "((((These are the weather details of the city:))))" + ANSI_WHITE);
            System.out.println(API.getCityInformation(actualCityName));
            System.out.println();

            System.out.println(ANSI_RED + "----Here are your options----" + ANSI_WHITE);
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
                    System.out.println(ANSI_PURPLE + "✅ Correct!✅ " + ANSI_WHITE);
                } else {
                    System.out.println(ANSI_YELLOW + " ❌ Wrong!❌ The correct answer is " + answer + "!" + ANSI_WHITE);
                }
            } else if (res.toLowerCase().equals("no")) {
                totalHints++;
                String hint = actualCity.knownFor();
                System.out.println();
                System.out.println(ANSI_GREEN + "This city is known (for/as): " + hint + ANSI_WHITE);
                System.out.println();
                System.out.println("-----Do you have an answer?-----\n-----Enter YES to affirm yes OR Enter NO to get a Hint-----"); 
                String re = scanner.nextLine();
                if (re.toLowerCase().equals("yes")) {
                    System.out.println("Great! Enter Your Answer!");
                    int userAnswer = scanner.nextInt();
                    if (userAnswer == answer) {
                        System.out.println(ANSI_PURPLE + "✅ Correct!✅ " + ANSI_WHITE);
                        totalPoints += 6;
                    } else {
                         System.out.println(ANSI_YELLOW + " ❌ Wrong!❌ The correct answer is " + answer + "!" + ANSI_WHITE);
                    }
                } else if (re.toLowerCase().equals("no")) {
                    totalHints++;
                    int random = (int) (Math.random() * 2);
                    System.out.println();
                    if (random == 0) {
                        System.out.println(ANSI_PURPLE + "This city is located in this state: " + actualCity.getState() + ANSI_WHITE);
                    } else {
                         System.out.println(ANSI_GREEN + "This city is located in this region of the country: " + actualCity.getRegion() + ANSI_WHITE);
                    }
                    System.out.println();
                    System.out.println("-----Please enter your answer now:-----");
                    int userAnswer = scanner.nextInt();
                    if (userAnswer == answer) {
                        System.out.println(ANSI_PURPLE + "✅ Correct!✅ " + ANSI_WHITE_BACKGROUND);
                        totalPoints += 3;
                    } else {
                       System.out.println(ANSI_YELLOW + " ❌ Wrong!❌ The correct answer is " + answer + "!" + ANSI_WHITE);
                    }
                }
            }
            System.out.println(ANSI_GREEN + "Total Points: " + totalPoints + ANSI_WHITE);
            System.out.println("Total Guesses: " + totalHints);
            System.out.println("Total Questions: " + totalQuestions + ANSI_WHITE);
        }
        System.out.println(ANSI_BLUE + "YAY! You have completed this challenge! You reached " + totalPoints + " with " + totalHints + " hints in " +  totalQuestions + " questions!");
        double percent = (totalPoints / (totalQuestions * 10.0)) * 100;
        System.out.println("You obtained " + percent + "% of the points!!" );
    }
}