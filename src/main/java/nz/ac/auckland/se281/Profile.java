package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profile {
    private String username;
    private int age;
    protected int numOfPolicies;
    protected int totalCosts;

    private ArrayList<Car> carsInsuranceList = new ArrayList<>(); // arraylist of all the cars insurance policies
    private ArrayList<Home> homesInsuranceList = new ArrayList<>(); // arraylist of all the homes insurance policies
    private ArrayList<Life> livesInsuranceList = new ArrayList<>(); // arraylist of all the lives insurance policies
    
    // creating a constructor to be able to assign variables within a profile
    public Profile (String username, int age){
        this.username = username;
        this.age = age;
        this.numOfPolicies = 0;
        this.totalCosts = 0;
    }

    // printing information (username and age)
    public String printInfo() {
        return (this.username + ", " + this.age);
    } 

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public void addHomeInsurance(String[] options, String userNameForInsurance) {
        Home homeInsuranceMade = new Home(Integer.valueOf(options[0]), options[1], options[2], userNameForInsurance);
        homesInsuranceList.add(homeInsuranceMade);
        numOfPolicies++;

        if (numOfPolicies == 1) {
            // no discount
            totalCosts += homeInsuranceMade.getBasePremium();
        } else if (numOfPolicies == 2) {
            // 10% discount on this policy and the previous ones
            totalCosts = (int) (0.9 * (totalCosts + homeInsuranceMade.getBasePremium()));
        } else {
            // 20% discount on this policy and the previous ones
            totalCosts = (int) (0.8 * (totalCosts + homeInsuranceMade.getBasePremium()));
        }
        
        // basePremiumList.add(homeInsuranceMade.getBasePremium());

        // for (Integer basePremium : basePremiumList) {


        // }

    }

    public Life addLifeInsurance(String[] options, int ageForInsurance, String userNameForInsurance) {
        Life lifeInsuranceMade = new Life(Integer.valueOf(options[0]), ageForInsurance, userNameForInsurance);
        livesInsuranceList.add(lifeInsuranceMade);
        numOfPolicies++;

        return lifeInsuranceMade;
    }

    public void addCarInsurance(String[] options, int ageForInsurance, String userNameForInsurance) {
        Car carInsuranceMade = new Car(Integer.valueOf(options[0]), options[1], options[2], options[3], ageForInsurance, userNameForInsurance);
        carsInsuranceList.add(carInsuranceMade);
        numOfPolicies++;
    }

    //public int discount(String userNameForInsurance) {
        // find the insurances under the userNameForInsurance
        // int i = 0;
        // while (i <= numOfPolicies) {
            // carsInsuranceList.indexOf(userNameForInsurance);
            // homesInsuranceList.indexOf(userNameForInsurance);
            // livesInsuranceList.indexOf(userNameForInsurance);
            // i++;
        // }

        // if (numOfPolicies == 2) {
            // return 2;
        // } else if (numOfPolicies == 1) {
            // return 4;
        // } else {
            // return 0;
        // }
    // }

    public String getNumOfPolicies() {
        return Integer.toString(numOfPolicies);
    }
    
    public String EndingForPrintingPolicy() {

        if (this.numOfPolicies == 1) {
          return "y";
        } else {
          return "ies";
        }
    }

}
