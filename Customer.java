
import java.io.Serializable;
import java.time.*;
import java.util.*;

public class Customer extends User implements Serializable{
    public ArrayList<Item> custInventory  = new ArrayList<Item>();
    Account account;
    int auctionWins;
    int auctionAttended;
    LocalDate birthday;
    public static void main(String[] args) {
        Customer cust = new Customer(1234);
        cust.menu();
    } // end main
    public Customer(int accountNum) {
        Scanner input = new Scanner(System.in);
        boolean keepGoing = true;
        while (keepGoing){
            try{
                this.accountNum = accountNum;
                System.out.println("Enter your name: ");
                String name = input.nextLine();
                System.out.println("Your account number is: " + this.accountNum);
                System.out.println("Enter the Account Pin: ");
                int pin = input.nextInt();
                System.out.println("Enter your birthday: ");
                this.name = name;
                this.pin = pin;
                input.nextLine();
                System.out.println("When is the birthday of the User? (In mm/dd/yyyy format)");
                String birthday = input.nextLine();
                String[] parts = birthday.split("/");
                String monthStr = parts[0];
                Month month = Month.of(Integer.parseInt(monthStr));
                int dayStr = Integer.parseInt(parts[1]);
                int yearStr = Integer.parseInt(parts[2]);
                this.birthday = LocalDate.of(yearStr, month , dayStr);
                System.out.println("How much do you want to deposit in the account?: ");
                double deposit = input.nextDouble();
                this.account = new Account(deposit);
                keepGoing = false;
            }catch(Exception e){
                System.out.println("Error Message " + e.getMessage() + "\nPlease enter the data in the correct format");
            } // end try catch
        } // end while
    } // end constructor
    public void menu(){
        Scanner input = new Scanner(System.in);
        boolean keepGoing = true;
        while (keepGoing){
            System.out.println("0. Quit \n1. View All Information\n2. Deposit Money\n3. Withdraw Money\n4. View Inventory");
            int choice = input.nextInt();
            if (choice == 0){
                keepGoing = false;
            }
            if (choice == 1){
                viewAll();
            }
            if (choice == 2){
                System.out.println("How much do you want to deposit?: ");
                double deposit = input.nextDouble();
                account.depositBalance(deposit);
            }
            if (choice == 3){
                System.out.println("How much do you want to withdraw?: ");
                double withdraw = input.nextDouble();
                if (account.withdrawBalance(withdraw)){
                    System.out.println("Withdraw Successful");
                }
                else{
                    System.out.println("Insufficient Balance");
                }
            }
            if (choice == 4){
                listInventory();
            }
            // if (choice == 5){
            //     Item a = new Item(1234);
            //     addInventory(a);
            // }
            else if (choice > 4 || choice < 0){
                System.out.println("Invalid Choice");
            } // end else if
        }
    } // end menu method
    public void addInventory(Item item){
        custInventory.add(item);
    } // end addInventory method\
    public void removeInventory(Item item){
        custInventory.remove(item);
    } // end removeInventory method
    public void listInventory(){
        for (int i = 0; i < custInventory.size(); i ++){
            System.out.println((i + 1) + ")");
            System.out.println(custInventory.get(i).viewAll());
        } // end for
    } // end viewInventory method
    public void increaseAuctionWins(){
        this.auctionWins++;
    } // end addAuctionWins method
    public void increaseAuctionAttended(){
        this.auctionAttended++;
    } // end addAuctionAttended method
    public int getAuctionWins(){
        return this.auctionWins;
    } // end getAuctionWins method
    public int getAuctionAttended(){
        return this.auctionAttended;
    } // end getAuctionAttended method
    public String viewBirthday(){
        return this.birthday.toString();
    } // end getBirthday method
    public void viewAll(){
        System.out.println("\tName: " + name);
        System.out.println("\tAccount Number: " + accountNum);
        System.out.println("\tPin: " + pin);
        System.out.println("\tBirthday: " + birthday);
        System.out.println("\tAuctions Attended: " + auctionAttended);
        System.out.println("\tAuction Wins: " + auctionWins);
        System.out.println("\tAccount Balance: " + account.getBalance());
    } // end viewAll method
    public void viewInfoLeaderBoard(){
        System.out.println("\tName: " + name);
        System.out.println("\tAuctions Attended: " + auctionAttended);
        System.out.println("\tAuction Wins: " + auctionWins);
    } // end viewInfoLeaderBoard method
} // end class def
