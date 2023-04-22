package nz.ac.auckland.se281;

public class Life extends PolicyType {

    private double basePremium;
    private double discountedPremium;

    public Life (int sumInsured, int age, String userName) {
        super(sumInsured, userName);
        this.basePremium = (100+age)/10000.0 * sumInsured;

        this.discountedPremium = this.basePremium;
    }

    /**
     * @return the basePremium
     */
    @Override
    public int getBasePremium() {
        return (int) basePremium;
    }

    /**
     * set the discountedPremium using the calculated discounted premium
     * @param calcDiscountedPremium to set the discountedPremium as this value
     */
    @Override
    public void setDiscountedPremium(int calcDiscountedPremium) {
        this.discountedPremium = calcDiscountedPremium;
    }

    /**
     * @return the discountedPremium
     */
    @Override
    public int getDiscountedPremium() {
        return (int) this.discountedPremium;
    }

}