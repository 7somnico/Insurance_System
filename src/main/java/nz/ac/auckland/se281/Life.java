package nz.ac.auckland.se281;

public class Life extends PolicyType {

    private double basePremium;

    public Life (int sumInsured, int age, String userName) {
        super(sumInsured, userName);
        this.basePremium = (1 + age/100) * SumInsured;
    }

    public int getBasePremium() {
        return (int) basePremium;
    }

}
