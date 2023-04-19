package nz.ac.auckland.se281;

public class Home extends PolicyType {

    private String address; 
    private double basePremium;

    public Home(int sumInsured, String address, String rental, String userName) {
        super(sumInsured, userName);
        this.address = address;
        
        if (rental.equals("yes")) {
            this.basePremium = 0.02 * sumInsured;
        } else {
            this.basePremium = 0.01 * sumInsured;
        }
        
    }

    public int getBasePremium() {
        return (int) basePremium;
    }

}
