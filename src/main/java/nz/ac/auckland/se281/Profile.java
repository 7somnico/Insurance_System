package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profile {
  private String username;
  private int age;
  private int numOfPolicies;
  private int initTotalCost;

  private ArrayList<PolicyType> insuranceList = new ArrayList<>();

  // creating a constructor to be able to assign variables within a profile
  public Profile(String username, int age) {
    this.username = username;
    this.age = age;
    this.numOfPolicies = 0;
    this.initTotalCost = 0;
  }

  /**
   * @return the age
   */
  public int getAge() {
    return age;
  }

  /**
   * @return the username
   */
  public String getUserName() {
    return username;
  }

  /**
   * addHomeInsurance method adds a home insurance to the profile it has been directed through the
   * input: Username and the options for the policy they want Create a new home insurance and add it
   * to the insuranceList
   *
   * @param options dictates the sumInsured, address and type of home: which are the options needed
   *     to create a home insurance
   * @param userNameForInsurance dictates the username of the user who is creating the insurance
   */
  public void addHomeInsurance(String[] options, String userNameForInsurance) {
    Home homeInsuranceMade =
        new Home(Integer.valueOf(options[0]), options[1], options[2], userNameForInsurance);
    insuranceList.add(homeInsuranceMade);
    numOfPolicies++;
  }

  /**
   * addLifeInsurance method adds a life insurance to the profile it has been directed through the
   * input: Username and the options for the policy they want Create a new life insurance and add it
   * to the insuranceList
   *
   * @param options dictates the sumInsured, which is the option needed to create a life insurance
   * @param userNameForInsurance dictates the username of the user who is creating the insurance
   * @param ageForInsurance dictates the age of the user who is creating the insurance
   */
  public Life addLifeInsurance(String[] options, int ageForInsurance, String userNameForInsurance) {
    Life lifeInsuranceMade =
        new Life(Integer.valueOf(options[0]), ageForInsurance, userNameForInsurance);

    insuranceList.add(lifeInsuranceMade);
    numOfPolicies++;
    return lifeInsuranceMade;
  }

  /**
   * addCarInsurance method adds a car insurance to the profile it has been directed through the
   * input: Username and the options for the policy they want Create a new car insurance and add it
   * to the insuranceList
   *
   * @param options dictates the sumInsured, make, model and year of the car: which are the options
   *     needed to create a car insurance
   * @param ageForInsurance dictates the age of the user who is creating the insurance
   * @param userNameForInsurance dictates the username of the user who is creating the insurance
   */
  public void addCarInsurance(String[] options, int ageForInsurance, String userNameForInsurance) {
    Car carInsuranceMade =
        new Car(
            Integer.valueOf(options[0]),
            options[1],
            options[2],
            options[3],
            ageForInsurance,
            userNameForInsurance);
    insuranceList.add(carInsuranceMade);
    numOfPolicies++;
  }

  /**
   * getTotalCosts method calculates the total cost of the insurance policies of a user The method
   * differs depending on the number of policies the user has
   *
   * @param user dictates the user who's total cost is being calculated
   * @return the total cost of the user's insurance policies
   */
  public String getTotalCosts(Profile user) {
    if (this.numOfPolicies == 1) {
      // no discount
      for (PolicyType insurance : insuranceList) {
        if (insurance.getUserName().equals(user.getUserName())) {
          return Integer.toString(insurance.getBasePremium());
        }
      }
      return Integer.toString(this.initTotalCost);

    } else if (this.numOfPolicies == 2) {
      // 10% discount on this policy and the previous ones
      for (PolicyType insurance : insuranceList) {
        if (insurance.getUserName().equals(user.getUserName())) {
          insurance.setDiscountedPremium((int) (0.9 * insurance.getBasePremium()));
          initTotalCost += insurance.getBasePremium();
        }
      }
      return Integer.toString((int) (0.9 * initTotalCost));
    } else {
      // 20% discount on this policy and the previous ones
      for (PolicyType insurance : insuranceList) {
        if (insurance.getUserName().equals(user.getUserName())) {
          insurance.setDiscountedPremium((int) (0.8 * insurance.getBasePremium()));
          initTotalCost += insurance.getBasePremium();
        }
      }
      return Integer.toString((int) (0.8 * initTotalCost));
    }
  }

  /**
   * printPolicies method prints the policies of a user The method differs depending on the number
   * of policies the user has
   *
   * @param user dictates the user who's policies are being printed
   */
  public void printPolicies(Profile user) {
    if (numOfPolicies == 0) {
      // no policies
    } else {
      // print policies, depending on the type of insurance
      for (PolicyType insurance : insuranceList) {
        if (insurance instanceof Life) {
          MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
              Integer.toString(insurance.getSumInsured()),
              Integer.toString(insurance.getBasePremium()),
              Integer.toString(insurance.getDiscountedPremium()));
        } else if (insurance instanceof Car) {
          MessageCli.PRINT_DB_CAR_POLICY.printMessage(
              insurance.getMakeAndModel(),
              Integer.toString(insurance.getSumInsured()),
              Integer.toString(insurance.getBasePremium()),
              Integer.toString(insurance.getDiscountedPremium()));
        } else if (insurance instanceof Home) {
          MessageCli.PRINT_DB_HOME_POLICY.printMessage(
              insurance.getAddress(),
              Integer.toString(insurance.getSumInsured()),
              Integer.toString(insurance.getBasePremium()),
              Integer.toString(insurance.getDiscountedPremium()));
        }
      }
    }
  }

  /**
   * getNumOfPolicies method returns the number of policies a user has
   *
   * @return the numOfPolicies
   */
  public String getNumOfPolicies() {
    return Integer.toString(numOfPolicies);
  }

  /**
   * endingForPrintingPolicy method returns the correct ending for the word "policy" depending on
   * the number of policies the user has
   *
   * @return the correct ending for the word "policy"
   */
  public String endingForPrintingPolicy() {

    if (this.numOfPolicies == 1) {
      return "y";
    } else {
      return "ies";
    }
  }
}
