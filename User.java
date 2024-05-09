import java.io.Serializable;

// import java.util.*;
public abstract class User implements Serializable{
    String name;
    int accountNum;
    int pin;
    
    public String getName() {
        return this.name;
    } // end getName

    public int getAccountNum() {
        return this.accountNum;
    } //end getAccountNum

    public int getPin() {
        return this.pin;
    } // end getPin
    public abstract void menu(); // abstract menu method
} // end class def