package nz.ac.auckland.se281;

public class Profile {
    private String username;
    private int age;
    
    // creating a constructor to be able to assign variables within a profile
    public Profile (String username, int age){
        this.username = username;
        this.age = age;
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

}
