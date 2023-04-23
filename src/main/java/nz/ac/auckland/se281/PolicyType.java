package nz.ac.auckland.se281;

public abstract class PolicyType {
  protected int sumInsured;
  protected String userName;
  
  public PolicyType(int sumInsured, String userName) {
    this.sumInsured = sumInsured;
    this.userName = userName;
  }

  /**
   * @return the sumInsured
   */
  public int getSumInsured() {
    return sumInsured;
  }
  
  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * an abstract class to get the base premium of the policy
   */
  public abstract int getBasePremium();

  /**
   * an abstract class to set the discounted premium of the policy
   * @param calcDiscountedPremium to set the discountedPremium as this value
   */
  public abstract void setDiscountedPremium(int calcDiscountedPremium);
  
  /**
   * an abstract class to get the discounted premium of the policy
   * @return the discountedPremium
   */
  public abstract int getDiscountedPremium();

  /**
   * get the make and model of the policies. Specific for the
   * car policy: It will be overriden for car.
   * For every other policy, return "Does not exist"
   * @return "Does not exist"
   */
  public String getMakeAndModel() {
    return "Does not exist";
  }

  /**
   * get the address the policy of the policies. Specific for the
   * home policy: It will be overriden for home.
   * For every other policy, return "Does not exist"
   * @return "Does not exist"
   */
  public String getAddress() {
    return "Does not exist";
  }

}

