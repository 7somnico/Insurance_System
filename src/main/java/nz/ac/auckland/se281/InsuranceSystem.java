package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  // setting up arraylists needed
  private ArrayList<Profile> usersList = new ArrayList<>(); // arraylist of all the profiles with their usernames and ages
  private ArrayList<String> usernamesList = new ArrayList<String>(); // arraylist of just the usernames
  private Profile loadedProfile = null;  // loaded profile, if any
  private Life LifeInsuranceMade = null; // life insurance policy, if any
  // private ArrayList<Car> carsInsuranceList = new ArrayList<>(); // arraylist of all the cars insurance policies
  // private ArrayList<Home> homesInsuranceList = new ArrayList<>(); // arraylist of all the homes insurance policies 
  // private ArrayList<Life> livesInsuranceList = new ArrayList<>(); // arraylist of all the lives insurance policies
  private ArrayList<String> livesInsuranceNamesList = new ArrayList<String>();


  public InsuranceSystem() {
    //
  }

  // printing out the database of the profiles
  public void printDatabase() {

    // if there are no profiles created
    if (usersList.size() == 0) { 
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0","s","."); 

      // if there is one profile created
    } else if (usersList.size() == 1) { 

      Profile user = usersList.get(0); 
      
      // printing out the information
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1",":"," ");
      // String loadedusersname = loadedProfileName.get(0);

      if (user.getUsername().equals(loadedProfile.getUsername())) {
        System.out.println("*** 1: " + user.printInfo());
      } else {
        System.out.println("1: " + user.printInfo());
      }
      // add carsInsuranceList.size() + homesInsuranceList.size() + livesInsuranceList.size() to the end of the printInfo() method
      

      // for any number of profiles greater or equal to two profiles
    } else { 
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(Integer.toString(usersList.size()),"s", ":");

      for (int i = 0; i < usersList.size(); i++) {
        Profile user = usersList.get(i);

        if (user.getUsername().equals(loadedProfile.getUsername())) {
          MessageCli.PRINT_DB_PROFILE_HEADER_MEDIUM.printMessage("*** ", Integer.toString(i+1), user.getUsername(), Integer.toString(user.getAge()), user.getNumOfPolicies(), user.EndingForPrintingPolicy());
        } else {
          MessageCli.PRINT_DB_PROFILE_HEADER_MEDIUM.printMessage("", Integer.toString(i+1), user.getUsername(), Integer.toString(user.getAge()), user.getNumOfPolicies(), user.EndingForPrintingPolicy());
        }

        
        
          //" %s%s: %s, %s, %s polic%s")
        //if (loadedProfile == null) {
          //System.out.println((i+1) + ": " + user.printInfo());
        //} else {
          //if (user.getUsername().equals(loadedProfile.getUsername())) {
            //System.out.println("*** " + (i+1) + ": " + user.printInfo());
          //} else {
            //System.out.println((i+1) + ": " + user.printInfo());
          //}
        //}


      }

      
    }
  }

  public void createNewProfile(String userName, String age) {
    // chaning the age into an integer, and titlecasing the username
    int intAge = Integer.valueOf(age); 
    userName = UsernameTitleCase(userName);

    // if a profile is already loaded, we cannot create another profile until it has been unloaded
    if (loadedProfile != null) { 
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(loadedProfile.getUsername());
      return;
    }

    // if the username is too short (less than 3 characters)
    if (userName.length() < 3) { 
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);

      // if the age is negative
    } else if (intAge < 0) { 
      
      MessageCli.INVALID_AGE.printMessage(Integer.toString(intAge), userName);

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
    // loads the profile into the system
    // requires exactly one argument

    userName = UsernameTitleCase(userName);

    // if the username is in the list of usernames, load the profile
    if (usernamesList.contains(userName)) {
      // get the age from the userslist to that corresponding username
      int loadedAge = usersList.get(usernamesList.indexOf(userName)).getAge();
      // uploading the profile of that username as the loadedPorfile 
      loadedProfile = new Profile(userName, loadedAge);
      MessageCli.PROFILE_LOADED.printMessage(userName);
    } else {
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
    }

  }

  public String UsernameTitleCase(String userName) {
    // titlecasing the userName
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

    return userName;
  }

  public void unloadProfile() {
    if (loadedProfile != null) {
      MessageCli.PROFILE_UNLOADED.printMessage(loadedProfile.getUsername());
      loadedProfile = null;
    } else {
      MessageCli.NO_PROFILE_LOADED.printMessage();
    }
  }

  public void deleteProfile(String userName) {
    // deleting the profile from the system
    // requires exactly one argument

    userName = UsernameTitleCase(userName);

    if (loadedProfile != null && loadedProfile.getUsername().equals(userName)) {
      MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(userName);
      return;
    }

    // if the username is in the list of usernames, delete the profile
    if (usernamesList.contains(userName)) {
      usersList.remove(usernamesList.indexOf(userName)); 
      usernamesList.remove(userName);
      MessageCli.PROFILE_DELETED.printMessage(userName);
    } else {
      MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(userName);
    }

  }

  public void createPolicy(PolicyType type, String[] options) { 
    // if there is already a life policy for a username, we cannot create another one for that person
    if (loadedProfile == null) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
      return;
    }

    int ageForInsurance = loadedProfile.getAge();
    //String NameForInsurance = loadedProfile.getUsername();

    // find where the loaded profile has the same name as the profile list
    int index = usernamesList.indexOf(loadedProfile.getUsername());
    // get the profile from the profile list
    Profile user = usersList.get(index);

    switch (type) {

      case HOME:
      user.addHomeInsurance(options, loadedProfile.getUsername());
      MessageCli.NEW_POLICY_CREATED.printMessage("home",loadedProfile.getUsername());
      break;

      case LIFE:
      if (ageForInsurance > 100) {
       MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(loadedProfile.getUsername());
        break;
      } else if (livesInsuranceNamesList.contains(loadedProfile.getUsername())) {
        MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(loadedProfile.getUsername());
        break;
      } 
      
      user.addLifeInsurance(options, ageForInsurance, loadedProfile.getUsername());
      livesInsuranceNamesList.add(loadedProfile.getUsername());
      MessageCli.NEW_POLICY_CREATED.printMessage("life",loadedProfile.getUsername());
    
      break;
    
      case CAR:
      user.addCarInsurance(options, ageForInsurance, loadedProfile.getUsername());
      MessageCli.NEW_POLICY_CREATED.printMessage("car",loadedProfile.getUsername());
    } 
    
  }

}

// need to do for task 2:
// section 3 & 4: MessageCli.PRINT_DB_PROFILE_HEADER_SHORT

// additional tests:
// if a profile is loaded, you cannot create another profile until it has been unloaded
// incorrect number of arguments? do we need to write anything for this?
// section 8: is it only for the profile that is loaded that we cannot delete, or is it the time while any profile has been loaded?

// need to do for task 3:
// can we use enum for rental and mechanical breakdown?
// also does the code need to pass task 2 tests? the print statements are different for task 3