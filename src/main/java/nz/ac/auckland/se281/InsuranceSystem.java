package nz.ac.auckland.se281;

import java.util.ArrayList;

import org.eclipse.jgit.transport.CredentialItem.Username;

import nz.ac.auckland.se281.Main.PolicyType;





public class InsuranceSystem {

  private String userName;
  private int age;

  private ArrayList<String> profileArrayUsername = new ArrayList<String>();
  private ArrayList<Integer> profileArrayAge = new ArrayList<Integer>();

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {
    if (profileArrayUsername.size() == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0","s",".");
    } else if (profileArrayUsername.size() == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1",":"," ");
      System.out.println("1: " + profileArrayUsername.get(0) +  ", " + profileArrayAge.get(0));
    } else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("2", "s", ":");
      for (int i = 0; i < profileArrayUsername.size(); i++) {
        System.out.println((i+1) + ": " + profileArrayUsername.get(i) +  ", " + profileArrayAge.get(i));
      }
    }
    
  }

  public void createNewProfile(String userName, String age) {
    this.age = Integer.valueOf(age);

    String lowerUsername = "";
    for (int i = 1; i < userName.length(); i++) {
      char lower = userName.charAt(i);

      String lowerCharacters = (String.valueOf(lower)).toLowerCase();

      lowerUsername = lowerUsername + lowerCharacters;

    }

    String firstLetter = String.valueOf(userName.charAt(0)).toUpperCase();

    this.userName = firstLetter + lowerUsername;


    if (userName.length() < 3) {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(this.userName);
    } else if (this.age < 0) {
      MessageCli.INVALID_AGE.printMessage(this.userName);
    } else {
      // check if the username is unique! (have not done that part yet)
      profileArrayUsername.add(this.userName);
      profileArrayAge.add(this.age);

      MessageCli.PROFILE_CREATED.printMessage(this.userName, age);

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
