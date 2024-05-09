import java.io.Serializable;
import java.util.*;

public class Item implements Serializable {
    String name;
    int itemID;
    int startBid;
    ItemCategory category;
    ItemStatus status;
    public static void main(String[] args) {
        Item item = new Item(1234);
        item.changeItemStatus();
        System.out.println(item.viewAll());
    } // end main

    public Item(int itemID) {
        Scanner input = new Scanner(System.in);

        boolean keepGoing = true;
        while (keepGoing) {
            try {
                System.out.println("Enter the name of the item: ");
                String name = input.nextLine();
                this.itemID = itemID;
                System.out.println("The Item ID is: " + this.itemID);
                System.out.println("Enter the starting bid: ");
                int startBid = input.nextInt();
                this.status = ItemStatus.NOTVERIFIED;
                input.nextLine();
                selectEnumItemCategory();
                this.name = name;
                this.itemID = itemID;
                this.startBid = startBid;
                keepGoing = false;
            } catch (Exception e) {
                System.out.println("Error Message " + e.getMessage() + "\nPlease enter the data in the correct format");
            }
        }
    } // end constructor

    public String getName() {
        return this.name;
    } // end getName

    public int getItemID() {
        return this.itemID;
    } // end getItemID

    public int getStartBid() {
        return this.startBid;
    } // end getStartBid

    public ItemCategory getCategory() {
        return this.category;
    } // end getCategory

    public ItemStatus getStatus() {
        return this.status;
    } // end getStatus

    public void changeItemStatus() {
        Scanner input = new Scanner(System.in);
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("The item status is : " + status);
            System.out.println("Do you want to Change the Item Status? (Y/N)");
            String choice = input.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                if (this.status == ItemStatus.NOTVERIFIED) {
                    this.status = ItemStatus.VERIFIED;
                } else {
                    this.status = ItemStatus.NOTVERIFIED;
                }
                keepGoing = false;
            }
            else if(choice.equalsIgnoreCase("N")){
                keepGoing = false;
            }
            else{
                System.out.println("Please enter Y or N");
            }
        }
    }

    public void selectEnumItemStatus() {
        Scanner input = new Scanner(System.in);
        boolean keepGoing = true;
        while (keepGoing) {
            try {
                for (int i = 0; i < ItemStatus.values().length; i++) {
                    System.out.println(i + ". " + ItemStatus.values()[i]);
                }
                System.out.println("Enter the number of the status you want to select: ");
                int choice = input.nextInt();
                for (int q = 0; q < ItemStatus.values().length; q++) {
                    if (ItemStatus.values()[q] == ItemStatus.values()[choice]) {
                        this.status = ItemStatus.values()[choice];
                        System.out.println("You selected " + this.status);
                        keepGoing = false;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error Message " + e.getMessage() + "\nPlease enter the data in the correct format");

            }
        }
    } // end listEnumItemStatus

    public void selectEnumItemCategory() {
        Scanner input = new Scanner(System.in);
        boolean keepGoing = true;
        while (keepGoing) {
            try {
                for (int i = 0; i < ItemCategory.values().length; i++) {
                    System.out.println(i + ". " + ItemCategory.values()[i]);
                }
                System.out.println("Enter the number of the category you want to select: ");
                int choice = input.nextInt();
                for (int q = 0; q < ItemCategory.values().length; q++) {
                    if (ItemCategory.values()[q] == ItemCategory.values()[choice]) {
                        this.category = ItemCategory.values()[choice];
                        System.out.println("You selected " + this.category);
                        keepGoing = false;
                    }
                }

            } catch (Exception e) {
                System.out.println("Error Message " + e.getMessage() + "\nPlease enter the data in the correct format");
            }
        }
    } // end listEnumItemCategory
    public String viewAll(){
        return "\tItem Name: " + this.name + "\n\tItem ID: " + this.itemID + "\n\tStarting Bid: " + this.startBid + "\n\tItem Category: " + this.category + "\n\tItem Status: " + this.status;
    }
} // END CLASS DEF
