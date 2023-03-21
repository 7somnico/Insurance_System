package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  // setting up arraylists needed
  private ArrayList<Profile> usersList = new ArrayList<>(); // arraylist of all the profiles with their usernames and ages
  private ArrayList<String> usernamesList = new ArrayList<String>(); // arraylist of just the usernames


  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  // printing out the database of the profiles
  public void printDatabase() {

    // if there are no profiles created
    if (usersList.size() == 0) { 
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0","s","."); 

      // if there is one profile created
    } else if (usersList.size() == 1) { 
      // specifying the index to be zero
      Profile user = usersList.get(0); 

      // printing out the information
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1",":"," ");
      System.out.println("1: " + user.printInfo()); 
      
      // for any number of profiles greater or equal to two profiles
    } else { 

      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("2", "s", ":");
      for (int i = 0; i < usersList.size(); i++) {
        Profile user = usersList.get(i);
        System.out.println((i+1) + ": " + user.printInfo()); 
      }
    }
    
  }

  public void createNewProfile(String userName, String age) {

    // changing the age from string to integer
    int intAge = Integer.valueOf(age); 

    // userName --> Titlecase:
    String lowerUsername = "";

    // for every characters in this string except the first character
    for (int i = 1; i < userName.length(); i++) { 
      char lower = userName.charAt(i);

      // change them into lowercase
      String lowerCharacters = (String.valueOf(lower)).toLowerCase(); 

      // adding the lowercased characters to the string
      lowerUsername = lowerUsername + lowerCharacters; 

    }

    // setting the first character of the string into uppercase
    String firstLetter = String.valueOf(userName.charAt(0)).toUpperCase(); 

    // adding the uppercases and lowercases together to give a title cased string
    userName = firstLetter + lowerUsername; 


    // if the username is too short (less than 3 characters)
    if (userName.length() < 3) { 
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);

      // if the age is negative
    } else if (intAge < 0) { 
      MessageCli.INVALID_AGE.printMessage(userName);

      // if the username overlaps 
    } else if (usernamesList.contains(userName)) {
      MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
    } else {

      usernamesList.add(userName);
    
      Profile user = new Profile(userName, intAge); 
      usersList.add(user);
      
      MessageCli.PROFILE_CREATED.printMessage(userName, age);
      
    } 

  }

  public void loadProfile(String userName) {
    // TODO: Complete this method.
  }

  public void unloadProfile() {
    // TODO: Complete this method.
  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }
}
