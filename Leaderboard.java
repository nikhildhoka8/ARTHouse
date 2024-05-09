import java.util.ArrayList;
import java.util.Scanner;

public class Leaderboard {
    // make a linked list that contains information about the customers and then I can sort the linked list based on the no. of auction wins, auctions attended, most amount of money spent, etc.
    // make a leaderboard for the auctions based on the highest winning prize, and percentage increase of the winning bid
    GenericLL <Customer> customerArr = new GenericLL<Customer>();
    GenericLL <Auction> auctionHistory = new GenericLL<Auction>();
    public static void main(String[] args) {
        // make a leaderboard for the customers
        Customer c1 = new Customer(0);
        Customer c2 = new Customer(1);
        c1.increaseAuctionWins();
        c2.increaseAuctionWins();
        c2.increaseAuctionWins();
        ArrayList <Customer> customerArr = new ArrayList<Customer>();
        ArrayList <Auction> auctionHistory = new ArrayList<Auction>();
        customerArr.add(c1);
        customerArr.add(c2);
        System.out.println(customerArr.get(0).getAuctionWins());
        System.out.println(customerArr.get(1).getAuctionWins());
        Leaderboard leaderboard = new Leaderboard(customerArr, auctionHistory);
        System.out.println(leaderboard.customerArr.get(0).getData().getAuctionWins());
        System.out.println(leaderboard.customerArr.get(1).getData().getAuctionWins());
        System.out.println("\n");
        leaderboard.sortCustomerWins();
        System.out.println(leaderboard.customerArr.get(0).getData().getAuctionWins());
        System.out.println(leaderboard.customerArr.get(1).getData().getAuctionWins());

    } // end main
    public Leaderboard(ArrayList<Customer> customerArr, ArrayList<Auction> auctionHistory){
        for (int i = 0; i < customerArr.size(); i++){
            this.customerArr.add(customerArr.get(i));
        } // end for
        for (int i = 0; i < auctionHistory.size(); i++){
            this.auctionHistory.add(auctionHistory.get(i));
        } // end for
    } // end constructor
    public void sortCustomerWins(){
        // sort the customerArr based on the no. of auction wins
        for (int i = 0; i < customerArr.size() -1; i++){
            for (int j = 0; j < customerArr.size() - i -1; j++){
                if (customerArr.get(j).getData().getAuctionWins() < customerArr.get(j + 1).getData().getAuctionWins()){
                   customerArr.swap(customerArr.get(j), customerArr.get(j + 1));
                } // end if
            } // end for
        } // end for
    } // end sortCustomerWins
    public void sortAuctionsAttended(){
        // sort the customerArr based on the no. of auctions attended
        for (int i = 0; i < customerArr.size() -1; i++){
            for (int j = 0; j < customerArr.size() - i -1; j++){
                if (customerArr.get(j).getData().getAuctionAttended() < customerArr.get(j + 1).getData().getAuctionAttended()){
                   customerArr.swap(customerArr.get(j), customerArr.get(j + 1));
                } // end if
            } // end for
        } // end for
    } // end sortAuctionsAttended
    public void sortHighestBid(){
        // sort the auctionHistory based on the highest bid
        for (int i = 0; i < auctionHistory.size() -1; i++){
            for (int j = 0; j < auctionHistory.size() - i -1; j++){
                if (auctionHistory.get(j).getData().getWinningBid() < auctionHistory.get(j + 1).getData().getWinningBid()){
                   auctionHistory.swap(auctionHistory.get(j), auctionHistory.get(j + 1));
                } // end if
            } // end for
        } // end for
    } // end sortHighestBid
    public void sortHighestPercentageIncrease(){
        // sort the auctionHistory based on the highest percentage increase
        for (int i = 0; i < auctionHistory.size() -1; i++){
            for (int j = 0; j < auctionHistory.size() - i -1; j++){
                if (auctionHistory.get(j).getData().getPercentageIncrease() < auctionHistory.get(j + 1).getData().getPercentageIncrease()){
                   auctionHistory.swap(auctionHistory.get(j), auctionHistory.get(j + 1));
                } // end if
            } // end for
        } // end for
    } // end sortHighestPercentageIncrease
    public void menu(){
        Scanner input = new Scanner(System.in);
        // make a menu for the leaderboard
        boolean keepGoing = true;
        while(keepGoing){
            try{
                System.out.println("What LeaderBoard do you want to access?");
                System.out.println("0. Exit");
                System.out.println("1. Customer Leaderboard by Auction Wins");
                System.out.println("2. Customer Leaderboard by Auctions Attended");
                System.out.println("3. Auction Leaderboard by Highest Winning Prize");
                System.out.println("4. Auction Leaderboard by Highest Percentage Increase");
                int choice = input.nextInt();
                if(choice == 0){
                    keepGoing = false;
                    
                }
                if (choice == 1){
                    // sort the customerArr based on the no. of auction wins
                    sortCustomerWins();
                    for (int i = 0; i < customerArr.size(); i++){
                        System.out.println((i + 1) + ")");
                        customerArr.get(i).getData().viewInfoLeaderBoard();
                        System.out.println("\n");
                    } // end for
                } // end if
                if (choice == 2){
                    // sort the customerArr based on the no. of auctions attended
                    sortAuctionsAttended();
                    for (int i = 0; i < customerArr.size(); i++){
                        System.out.println((i + 1) + ")");
                        customerArr.get(i).getData().viewInfoLeaderBoard();
                        System.out.println("\n");
                    } // end for
                } // end if
                if (choice == 3){
                    // sort the auctionHistory based on the highest bid
                    sortHighestBid();
                    for (int i = 0; i < auctionHistory.size(); i++){
                        System.out.println((i + 1) + ")");
                        auctionHistory.get(i).getData().viewAll();
                        System.out.println("\n");
                    } // end for
                } // end if
                if (choice == 4){
                    // sort the auctionHistory based on the highest percentage increase
                    sortHighestPercentageIncrease();
                    for (int i = 0; i < auctionHistory.size(); i++){
                        System.out.println((i + 1) + ")");
                        auctionHistory.get(i).getData().viewAll();
                        System.out.println("\n");
                    } // end for
                } // end if
                else if (choice > 4 || choice < 0){
                    System.out.println("Invalid input. Please try again.");
                }
            } // end try
            catch(Exception e){
                System.out.println("Invalid input. Please try again.");
            } // end catch
        } // end while
    } // end menu
} // end class def