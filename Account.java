import java.io.Serializable;


public class Account implements Serializable{
    double balance;
    
    public Account(double balance){
        this.balance = balance;
    } // end constructor
    public double getBalance (){
        return this.balance;
    } // end getBalance method
    public boolean withdrawBalance(double withdraw){
        if (withdraw > balance){
            return false;
        } // end if
        else{
            withdraw = balance - withdraw;
            withdraw = Math.round(withdraw * 100.0) / 100.0;
            balance = withdraw;
            return true;
        }// end else
    } // end wihtdraw Balance
    public void depositBalance(double deposit){
        deposit += balance;
        deposit = Math.round(deposit * 100.0) / 100.0;
        balance = deposit;
    } // end depositCheck
} // end class def
