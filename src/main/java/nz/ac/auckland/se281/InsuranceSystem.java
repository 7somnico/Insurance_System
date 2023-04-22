package nz.ac.auckland.se281;

import java.util.ArrayList;

import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  
  // setting up arraylists needed
  private ArrayList<Profile> usersList = new ArrayList<>(); 
  private ArrayList<String> userNamesList = new ArrayList<String>(); 
  private Profile loadedProfile = null;  // loaded profile, if any
  private ArrayList<String> livesInsuranceNamesList = new ArrayList<String>();

  public InsuranceSystem() {
    //
  }
  
  /* 
  * printDatabase method prints out the database of profiles and their policies.
  * Depending on the number of profiles created and whether the profile has any policies,
  * the method will print out the appropriate message.
  */

  public void printDatabase() {

    // if there are no profiles created
    if (usersList.size() == 0) { 
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0","s","."); 
      
      // if there is one profile created
    } else if (usersList.size() == 1) { 
      Profile user = usersList.get(0);
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1",":"," ");
      
      // print different things depending on whether a profile is loaded:
      // This is when there is 1 profile to load
      if (loadedProfile == null) {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
          "",Integer.toString(1),user.getUserName(),
          Integer.toString(user.getAge()),user.getNumOfPolicies(),
          user.endingForPrintingPolicy(),user.getTotalCosts(user));
      } else {
        if (user.getUserName().equals(loadedProfile.getUserName())) {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "*** ",Integer.toString(1),user.getUserName(),
            Integer.toString(user.getAge()),user.getNumOfPolicies(),
            user.endingForPrintingPolicy(),user.getTotalCosts(user));
      } else {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
          "",Integer.toString(1),user.getUserName(),
          Integer.toString(user.getAge()),user.getNumOfPolicies(),
          user.endingForPrintingPolicy(),user.getTotalCosts(user));
      }

      // print the policies each user has signed themselves on 
      user.printPolicies(user);
    }

    // for any number of profiles greater or equal to two profiles
  } else { 
    MessageCli.PRINT_DB_POLICY_COUNT.printMessage(Integer.toString(usersList.size()),"s",":");
    
    for (int i = 0; i < usersList.size(); i++) {
      Profile user = usersList.get(i);

      // print different things depending on whether a profile is loaded:
      // This is when there is more than 1 profiles to load 
      if (loadedProfile == null) {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
          "",Integer.toString(i+1),user.getUserName(),
          Integer.toString(user.getAge()),user.getNumOfPolicies(),
          user.endingForPrintingPolicy(),user.getTotalCosts(user));
      } else {
        if (user.getUserName().equals(loadedProfile.getUserName())) {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "*** ",Integer.toString(i+1),user.getUserName(),
            Integer.toString(user.getAge()),user.getNumOfPolicies(),
            user.endingForPrintingPolicy(),user.getTotalCosts(user));
      } else {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
          "",Integer.toString(i+1),user.getUserName(),
          Integer.toString(user.getAge()), user.getNumOfPolicies(),
          user.endingForPrintingPolicy(),user.getTotalCosts(user));
      }
    }

    // print the policies each user has signed themselves on 
    user.printPolicies(user);
  }
}
}

/**
 * createNewProfile method creates a new profile with the given
 * username and age.
 * It filters out the cases when a new profile cannot be created,
 * and prints out the appropriate message.
 * 
 * @param userName is the Username of the profile that should be
 * created as the new instance of a profile 
 * @param age is the age of the profile that should be created as
 * the instance of a profile
 */

 public void createNewProfile(String userName, String age) {
  // changing the age into an integer, and titlecasing the username
  int intAge = Integer.valueOf(age); 
  userName = userNameTitleCase(userName);

  // if a profile is already loaded, we cannot create another profile
  // until it has been unloaded
  if (loadedProfile != null) { 
    MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(loadedProfile.getUserName());
    return;
  }
    
  // filtering out the cases where we cannot create a new profile:
  // short username, negative age and overlapping username
  if (userName.length() < 3) { 
    MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);

  } else if (intAge < 0) { 
    MessageCli.INVALID_AGE.printMessage(Integer.toString(intAge), userName);

  } else if (userNamesList.contains(userName)) {
    MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
  } else {

    // creating a new profile under the Username and age given afert
    // filtering out the cases where we cannot create a new profile
    userNamesList.add(userName);
    
    Profile user = new Profile(userName, intAge); 
    usersList.add(user);
      
    MessageCli.PROFILE_CREATED.printMessage(userName, age);
      
  } 

}

/**
 * loadProfile method loads the profile of the given username. It filters
 * out the cases when a profile cannot be loaded: ie. there is no profile
 * created under that Username yet
 * * @param userName is the Username of the profile that should be loaded
 */

 public void loadProfile(String userName) {

  userName = userNameTitleCase(userName);

  // if the username is in the list of usernames, load the profile
  if (userNamesList.contains(userName)) {
    // get the age from the userslist to that corresponding username
    int loadedAge = usersList.get(userNamesList.indexOf(userName)).getAge();

    // uploading the profile of that username as the loadedPorfile 
    MessageCli.PROFILE_LOADED.printMessage(userName);
    loadedProfile = new Profile(userName, loadedAge);

  } else {
    MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(userName);
  }

}

/**
 * userNameTitleCase method changes the username into a title cased string
 * 
 * @param userName is the Username of the profile that should be title cased
 * @return the title cased string of the username
 */

 public String userNameTitleCase(String userName) {
  String lowerUserName = "";

  // for every characters in this string except the first character
  for (int i = 1; i < userName.length(); i++) { 
    char lower = userName.charAt(i);

    // change them into lowercase
    String lowerCharacters = (String.valueOf(lower)).toLowerCase(); 

    // adding the lowercased characters to the string
    lowerUserName = lowerUserName + lowerCharacters; 

  }

  // setting the first character of the string into uppercase
  String firstLetter = String.valueOf(userName.charAt(0)).toUpperCase(); 

  // adding the uppercases and lowercases together to give a title cased string
  userName = firstLetter + lowerUserName; 

  return userName;
}

/**
 * unloadProfile method unloads the profile that is currently loaded.
 * It filters out the cases when a profile cannot be unloaded:
 * ie. there is no profile loaded
 */

 public void unloadProfile() {
  if (loadedProfile != null) {
    MessageCli.PROFILE_UNLOADED.printMessage(loadedProfile.getUserName());
    loadedProfile = null;
  } else {
    MessageCli.NO_PROFILE_LOADED.printMessage();
  }
}

/**
 * deleteProfile method deletes the profile of the given username.
 * It filters out the cases when a profile cannot be deleted:
 * ie. there is no profile created under that Username yet, or that
 * corresponding profile is currently loaded
 * 
 * @param userName is the Username of the profile that should be deleted
 */

 public void deleteProfile(String userName) {

  userName = userNameTitleCase(userName);

  // filtering out the cases where we cannot delete a profile: that corresponding
  // is loaded, so we cannot delete
  if (loadedProfile != null && loadedProfile.getUserName().equals(userName)) {
    MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(userName);
    return;
  }

  // if the username is in the list of profile usernames, delete the profile
  if (userNamesList.contains(userName)) {
    usersList.remove(userNamesList.indexOf(userName)); 
    userNamesList.remove(userName);
    MessageCli.PROFILE_DELETED.printMessage(userName);
  } else {
    MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(userName);
  }

}

/**
 * createPolicy method creates a new policy of the given type and options.
 * It filters out the cases when a policy cannot be created:
 * ie. there is no profile loaded, or that profile already has a life policy
 * 
 * @param type is the type of the policy that should be created
 * @param options is the options of the policy that should be created
 */

 public void createPolicy(PolicyType type, String[] options) { 

  // if there is no loaded profile, we cannot create a policy for that profile
  if (loadedProfile == null) {
    MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
    return;
  }

  int ageForInsurance = loadedProfile.getAge();

  // find where the loaded profile has the same name as the profile list
  int index = userNamesList.indexOf(loadedProfile.getUserName());
  // get the profile from the profile list
  Profile user = usersList.get(index);

  // depending on the type of policy, create a new policy corresponding to
  // that particular type
  switch (type) {

    // add a home insurance for that profile
    case HOME:
    user.addHomeInsurance(options, loadedProfile.getUserName());
    MessageCli.NEW_POLICY_CREATED.printMessage("home",loadedProfile.getUserName());
    break;

    // add a life insurance for that profile, after filtering out the cases
    // where we cannot create a life insurance: when the user's age is over
    // 100 or if the user already has a life policy
    case LIFE:
    if (ageForInsurance > 100) {
      MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(loadedProfile.getUserName());
      break;
    } else if (livesInsuranceNamesList.contains(loadedProfile.getUserName())) {
      MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(loadedProfile.getUserName());
      break;
    } 
      
    user.addLifeInsurance(options, ageForInsurance, loadedProfile.getUserName());
    livesInsuranceNamesList.add(loadedProfile.getUserName());
    MessageCli.NEW_POLICY_CREATED.printMessage("life",loadedProfile.getUserName());
    
    break;
    
    // add a car insurance for that profile
    case CAR:
    user.addCarInsurance(options, ageForInsurance, loadedProfile.getUserName());
    MessageCli.NEW_POLICY_CREATED.printMessage("car",loadedProfile.getUserName());
  } 
    
}

}




