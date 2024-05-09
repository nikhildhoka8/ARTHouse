import java.io.Serializable;
import java.util.*;
// can I pass in the arrays as a parmeter in the constructor. Will it point to the same element?
public class Admin extends User implements Serializable{
    ArrayList<Customer> customerArr;
    ArrayList<Item> unverifiedInventory;
    ArrayList<Item> verifiedInventory;
    ArrayList<Auction> auctionHistory;
    Account auctionAccount;
    public static void main(String[] args) {
        ArrayList<Customer> customerArr = new ArrayList<Customer>();
        ArrayList<Item> unverifiedInventory = new ArrayList<Item>();
        ArrayList<Item> verifiedInventory = new ArrayList<Item>();
        ArrayList<Auction> auctionHistory = new ArrayList<Auction>();
        Account auctionAccount = new Account(100000);
        Admin admin = new Admin(customerArr, unverifiedInventory, verifiedInventory, auctionHistory, auctionAccount, 1234);
        admin.menu();
    } // end main
    
    public Admin(ArrayList<Customer> customerArr, ArrayList<Item> unverifiedInventory, ArrayList<Item> verifiedInventory, ArrayList<Auction> auctionHistory, Account auctionAccount, int accountNum){
        Scanner input = new Scanner(System.in);
        boolean keepGoing = true;
        while (keepGoing){
            try{
                this.customerArr = customerArr;
                this.unverifiedInventory = unverifiedInventory;
                this.verifiedInventory = verifiedInventory;
                this.auctionHistory = auctionHistory;
                this.auctionAccount = auctionAccount;
                this.accountNum = accountNum;
                this.name = "Nikhil";
                this.pin = 1234;
                keepGoing = false;
            }catch(Exception e){
                System.out.println("Error Message " + e.getMessage() + "\nPlease enter the data in the correct format");
            } // end try catch
        } // end while
    }// end constructor

    public void deleteCustomer(){
        boolean customerFound = false;
        Scanner input = new Scanner(System.in);
        listCustomer();
        System.out.println("Enter the account number of the customer you wish to delete: ");
        int accountNum = input.nextInt();
        for (int i = 0; i < customerArr.size(); i++){
            if (customerArr.get(i).getAccountNum() == accountNum){
                customerArr.remove(i);
                System.out.println("Customer has been deleted");
                customerFound = true;
            } // end if
        } // end for
        if (!customerFound){
            System.out.println("Customer not found");
        } // end if
    } // end deleteCustomer 

    public void listCustomer(){
        int i;
        for(i = 0; i < customerArr.size(); i ++){
            System.out.println((i + 1) + ")");
            customerArr.get(i).viewAll();
            System.out.println("\n");
        } // end for
    } // end listCustomer

    public void addCustomer(){
        boolean keepGoing = true;
        while (keepGoing){
            try{
                int accountNum;
                if (customerArr.size() == 0) {
                    accountNum = 10000;
                } else {
                    accountNum = customerArr.get(customerArr.size() - 1).getAccountNum() + 1;
                } // end if else
                Customer cust = new Customer(accountNum);
                customerArr.add(cust);
                keepGoing = false;
            }catch(Exception e){
                System.out.println("Error Message " + e.getMessage() + "\nPlease enter the data in the correct format");
            } // end try catch
        } // end while
    }// end addCustomer

    public void verifyItem(){
        Scanner input = new Scanner(System.in);
        listUnverifiedInventory();
        System.out.println("Enter the item ID of the item you wish to verify: ");
        int itemID = input.nextInt();
        for (int i = 0; i < unverifiedInventory.size(); i++){
            if (unverifiedInventory.get(i).getItemID() == itemID){
                unverifiedInventory.get(i).changeItemStatus();
                if (unverifiedInventory.get(i).getStatus() == ItemStatus.VERIFIED){
                    Item temp = unverifiedInventory.get(i);
                    verifiedInventory.add(temp);
                    unverifiedInventory.remove(i);
                    System.out.println("Item has been verified. Moved to Verified Inventory!");
                }
                else{
                    System.out.println("Did not change Status of the Item.");
                } // end if else
            } // end if
        } // end for
    } // end verifyItem
    public void unverifyItem(){
        Scanner input = new Scanner(System.in);
        listVerifiedInventory();
        System.out.println("Enter the item ID of the item you wish to unverify: ");
        int itemID = input.nextInt();
        for (int i = 0; i < verifiedInventory.size(); i++){
            if (verifiedInventory.get(i).getItemID() == itemID){
                verifiedInventory.get(i).changeItemStatus();
                if(verifiedInventory.get(i).getStatus() == ItemStatus.NOTVERIFIED){
                    Item temp = verifiedInventory.get(i);
                    unverifiedInventory.add(temp);
                    verifiedInventory.remove(i);
                    System.out.println("Item has been unverified. Moved to Un Verified Inventory");
                }
                else{
                    System.out.println("Did not change Status of the Item.");
                } // end if else
            } // end if
        } // end for
    } // end unverifyItem

   public double getAuctionBalance(){
        return auctionAccount.getBalance();
   }
    public void withdrawAuctionAccount(){
        Scanner input = new Scanner(System.in);
        System.out.println("The balance is: " + auctionAccount.getBalance());
        System.out.println("How much do you want to withdraw");
        double amount = input.nextDouble();
        if(!auctionAccount.withdrawBalance(amount)){
            System.out.println("Insufficient funds");
        }
        else{
            System.out.println("Withdrawal successful");
        }
    }
    public void depositAuctionAccount(){
        Scanner input = new Scanner(System.in);
        System.out.println("The balance is: " + auctionAccount.getBalance());
        System.out.println("How much do you want to deposit");
        double amount = input.nextDouble();
        auctionAccount.depositBalance(amount);
        System.out.println("Deposit successful");
    }
    public void listUnverifiedInventory(){
        int i;
        for(i = 0; i < unverifiedInventory.size(); i ++){
            System.out.println((i + 1) + ")");
            System.out.println(unverifiedInventory.get(i).viewAll());
            System.out.println("\n");
        } // end for
    } // end listUnverifiedInventory
    public void listVerifiedInventory(){
        int i;
        for(i = 0; i < verifiedInventory.size(); i ++){
            System.out.println((i + 1) + ")");
            System.out.println(verifiedInventory.get(i).viewAll());
            System.out.println("\n");
        } // end for
    } // end listVerifiedInventory
    public void listAuctionHistory(){
        int i;
        for(i = 0; i < auctionHistory.size(); i ++){
            System.out.println((i + 1) + ")");
            auctionHistory.get(i).viewAll();
            System.out.println("\n");
        } // end for
    } // end listAuctionHistory

    public void addUnverifiedItem(){
        boolean keepGoing = true;
        while (keepGoing){
            try{
                int itemID = getNewItemID();
                Item item = new Item(itemID);
                unverifiedInventory.add(item);
                keepGoing = false;
            }catch(Exception e){
                System.out.println("Error Message " + e.getMessage() + "\nPlease enter the data in the correct format");
            } // end try catch
        } // end while
    }// end addUnverifiedItem

    public int getNewItemID(){
        int itemID;
        int tempitemID1;
        int tempitemID2;
        int tempitemID3;
        itemID = 1000;
        if(unverifiedInventory.size() == 0 || auctionHistory.size() == 0 || verifiedInventory.size() == 0){
            if(unverifiedInventory.size() == 0 && verifiedInventory.size() != 0 && auctionHistory.size() != 0){
                tempitemID2 = auctionHistory.get(auctionHistory.size() - 1).getItem().getItemID();
                tempitemID3 = verifiedInventory.get(verifiedInventory.size() - 1).getItemID();
                if (tempitemID2 > tempitemID3){
                    itemID = tempitemID2 + 1;
                    return itemID;
                }
                else{
                    itemID = tempitemID3 + 1;
                    return itemID;
                } // end if else
            }
            else if(unverifiedInventory.size() != 0 && verifiedInventory.size() == 0 && auctionHistory.size() != 0){
                tempitemID1 = unverifiedInventory.get(unverifiedInventory.size() - 1).getItemID();
                tempitemID2 = auctionHistory.get(auctionHistory.size() - 1).getItem().getItemID();
                if (tempitemID1 > tempitemID2){
                    itemID = tempitemID1 + 1;
                    return itemID;
                }
                else{
                    itemID = tempitemID2 + 1;
                    return itemID;
                } // end if else
            }
            else if(unverifiedInventory.size() != 0 && verifiedInventory.size() != 0 && auctionHistory.size() == 0){
                tempitemID1 = unverifiedInventory.get(unverifiedInventory.size() - 1).getItemID();
                tempitemID3 = verifiedInventory.get(verifiedInventory.size() - 1).getItemID();
                if (tempitemID1 > tempitemID3){
                    itemID = tempitemID1 + 1;
                    return itemID;
                }
                else{
                    itemID = tempitemID3 + 1;
                    return itemID;
                } // end if else
            }
            else if(auctionHistory.size() != 0 && unverifiedInventory.size() == 0 && verifiedInventory.size() == 0){
                tempitemID2 = auctionHistory.get(auctionHistory.size() - 1).getItem().getItemID();
                itemID = tempitemID2 + 1;
                return itemID;
            }
            else if(verifiedInventory.size() != 0 && unverifiedInventory.size() == 0 && auctionHistory.size() == 0){
                tempitemID3 = verifiedInventory.get(verifiedInventory.size() - 1).getItemID();
                itemID = tempitemID3 + 1;
                return itemID;
            }
            else if(unverifiedInventory.size() != 0 && auctionHistory.size() == 0 && verifiedInventory.size() == 0){
                tempitemID1 = unverifiedInventory.get(unverifiedInventory.size() - 1).getItemID();
                itemID = tempitemID1 + 1;
                return itemID;
            }
            else{
                return itemID;
            } // end else
        } // end if
        else{
            tempitemID1 = unverifiedInventory.get(unverifiedInventory.size() - 1).getItemID();
            tempitemID2 = auctionHistory.get(auctionHistory.size() - 1).getItem().getItemID();
            tempitemID3 = verifiedInventory.get(verifiedInventory.size() - 1).getItemID();
            if (tempitemID1 > tempitemID2 && tempitemID1 > tempitemID3){
                itemID = tempitemID1 + 1;
                return itemID;
            }
            else if(tempitemID2 > tempitemID1 && tempitemID2 > tempitemID3){
                itemID = tempitemID2 + 1;
                return itemID;
            }
            else if(tempitemID3 > tempitemID1 && tempitemID3 > tempitemID2){
                itemID = tempitemID3 + 1;
                return itemID;
            }
            else{
                return itemID;
            }
        } // end if else
    } // end getNewItemID


    public void startAuction(){
        Scanner input = new Scanner(System.in);
        int itemIndex = -1;
        int customer1Index = -1;
        int customer2Index = -1;
        try{
            if(verifiedInventory.size() == 0){
                System.out.println("No verified Items!");
                return;
            }else{
                listVerifiedInventory();
            }
            System.out.println("Enter the item ID of the item you wish to start an auction for: ");
            int itemID = input.nextInt();
            boolean keepGoing;
            while(keepGoing = true){
                for (int i = 0; i < verifiedInventory.size(); i++){
                    if (verifiedInventory.get(i).getItemID() == itemID){
                        itemIndex = i;
                        keepGoing = false;
                    } // end if
                } // end for
                if (itemIndex == -1){
                    System.out.println("Item ID not found, please enter a valid item ID: ");
                    itemID = input.nextInt();
                } // end if
                else{
                    break;
                } // end else
            }
            listCustomer();
            System.out.println("Customer 1 Please Enter your Account Number: ");
            int customer1AccountNum = input.nextInt();
            System.out.println("Customer 1 please enter your pin: ");
            int customer1Pin = input.nextInt();
            for (int i = 0; i < customerArr.size(); i++){
                if (customerArr.get(i).getAccountNum() == customer1AccountNum && customerArr.get(i).getPin() == customer1Pin){
                    customer1Index = i;
                } // end if
            } // end for
            System.out.println("Customer 2 Please Enter your Account Number: ");
            int customer2AccountNum = input.nextInt();
            System.out.println("Customer 2 please enter your pin: ");
            int customer2Pin = input.nextInt();
            for (int i = 0; i < customerArr.size(); i++){
                if (customerArr.get(i).getAccountNum() == customer2AccountNum && customerArr.get(i).getPin() == customer2Pin){
                    customer2Index = i;
                } // end if
            } // end for
        } catch(Exception e){
            System.out.println("Invalid Input");
        } // end try catch 
        Auction auction = new Auction(verifiedInventory.get(itemIndex), customerArr.get(customer1Index), customerArr.get(customer2Index));
        auctionHistory.add(auction);
        customerArr.get(customer1Index).increaseAuctionAttended();
        customerArr.get(customer2Index).increaseAuctionAttended();
        if (auction.bidPlaced == true){
            for (int i =0; i < customerArr.size(); i++){
                if (customerArr.get(i).getAccountNum() == auction.getWinner().getAccountNum()){
                    customerArr.get(i).increaseAuctionWins();
                    customerArr.get(i).addInventory(verifiedInventory.get(itemIndex));
                    customerArr.get(i).account.withdrawBalance(auction.getWinningBid());
                    System.out.println(" " + auction.getWinner().getName() + ", " + auction.getWinningBid() + " has been debited from your account");
                    auctionAccount.depositBalance(auction.getWinningBid());
                    System.out.println("Item ID " + verifiedInventory.get(itemIndex).getName() + " added to your inventory");
                } // end if
            }
            verifiedInventory.remove(itemIndex);
        }
    } // end startAuction

    public void menu(){
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome Admin!");
        boolean keepGoing = true;
        while(keepGoing){
            try{
                System.out.println("0. Quit \n1. List Customers \n2. List Un Verified Inventory \n3. List Verified Inventory \n4. List Auction History \n5. Add a Customer \n6. Delete a Customer \n7. Verify Items \n8. Un Verify Items \n9. Get Auction Account Balance \n10. Wihtdraw from Auction Account \n11. Deposit Auction Account \n12. Add a New Item \n13. Start a new Auction");
                int choice = input.nextInt();
                if (choice == 0){
                    System.out.println("Thank you!");
                    keepGoing = false;
                    return;
                }
                else if(choice == 1){
                    listCustomer();
                }
                else if(choice == 2){
                    listUnverifiedInventory();
                }
                else if(choice == 3){
                    listVerifiedInventory();
                }
                else if(choice == 4){
                    listAuctionHistory();
                }
                else if(choice == 5){
                    addCustomer();
                }
                else if(choice == 6){
                    deleteCustomer();
                }
                else if(choice == 7){
                    verifyItem();
                }
                else if(choice == 8){
                    unverifyItem();
                }
                else if(choice == 9){
                    System.out.println("The balance is: " + getAuctionBalance());
                }
                else if(choice == 10){
                    withdrawAuctionAccount();
                }
                else if(choice == 11){
                    depositAuctionAccount();
                }
                else if(choice == 12){
                    addUnverifiedItem();
                }
                else if (choice == 13){
                    startAuction();
                }
                if (choice > 13 || choice < 0){
                    System.out.println("Invalid Choice!");
                }
            } // end while
            catch(Exception e){
                System.out.println("Invalid Input");
            } // end try catch
        }
    } // end menu
}  
