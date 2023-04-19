package nz.ac.auckland.se281;

public class Car extends PolicyType {

    private String makeAndModel;
    private String licensePlate;
    private int age;
    private double basePremium;

    public Car(int sumInsured, String makeAndModel, String licensePlate, String mechanicalBreakdown, int age, String userName) {
        super(sumInsured, userName);
        this.makeAndModel = makeAndModel;
        this.licensePlate = licensePlate;
        this.age = age;

        if (this.age < 25) {
            this.basePremium = 0.15 * sumInsured;
        } else {
            this.basePremium = 0.1 * sumInsured;
        }

        if (mechanicalBreakdown.equals("yes")) {
            this.basePremium = basePremium + 80;
        }
    }
    
    public int getBasePremium() {
        return (int) basePremium;
    }
    
    
}
