package nz.ac.auckland.se281;

public class Home extends PolicyType {
  private String address;
  private double basePremium;
  private double discountedPremium;

  public Home(int sumInsured, String address, String rental, String userName) {
    super(sumInsured, userName);
    this.address = address;

    if (rental.equals("yes")) {
      this.basePremium = 0.02 * sumInsured;
    } else {
      this.basePremium = 0.01 * sumInsured;
    }

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
   * @param basePremium set the discountedPremium
   */
  @Override
  public String getAddress() {
    return address;
  }

  /**
   * set the discountedPremium using the calculated discounted premium
   *
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
