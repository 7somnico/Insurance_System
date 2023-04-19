package nz.ac.auckland.se281;

public abstract class PolicyType {
    
    protected int SumInsured;
    protected String userName;

    public PolicyType(int SumInsured, String userName) {
        this.SumInsured = SumInsured;
        this.userName = userName;
    }

    public int getSumInsured() {
        return SumInsured;
    }

    public String getUserName() {
        return userName;
    }


}
