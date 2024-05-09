import java.io.Serializable;
import java.util.*;


//websockets, RMI
public class Auction implements Serializable {
    private transient Timer timer;
    int timeLeft;
    int currentBid;
    Item item;
    Customer participantA;
    Customer participantB;
    Customer winner;
    boolean bidPlaced;
    int percentageIncrease;

    public static void main(String[] args) {
        Item item = new Item(1234);
        Customer a = new Customer(1000);
        Customer b = new Customer(1001);
        Auction auction = new Auction(item, a, b);
        auction.viewAll();
    } // end main   
    public Auction(Item item, Customer participantA, Customer participantB) {
        this.percentageIncrease = 0;
        Scanner input = new Scanner(System.in);
        this.timeLeft = 100;
        this.currentBid = item.getStartBid();
        this.item = item;
        this.bidPlaced = false;
        this.timer = new Timer();
        this.participantA = participantA;
        this.participantB = participantB;
        this.winner = null;
        System.out.println("The starting bid is: " + item.getStartBid());
        boolean keepGoing = true;
        while(keepGoing){
            this.start();
            System.out.println(participantA.getName() + ", what is your bid?:  ");
            int bidA = input.nextInt();
            if (bidA < this.participantA.account.getBalance() && placeBid(bidA, participantA)){
                this.bidPlaced = true;
                System.out.println("Your bid was accepted!");
            } else {
                System.out.println("Your bid was not accepted!");
                if (bidA > this.participantA.account.getBalance() || bidA < this.currentBid){
                    keepGoing = false;
                    endAuction();
                    System.out.println("Not a valid Bid!");
                } // end if
                if(winner == null && bidPlaced == false){
                    System.out.println("The starting bid is: " + item.getStartBid());
                } // end if
                else{
                    System.out.println("The Current Highest bid is: " + this.currentBid + " by " + winner.getName());
                } // end else
            } // end if else
            
            System.out.println(participantB.getName() + ", what is your bid?:  ");
            timeLeft = 30;
            start();
            int bidB = input.nextInt();
            if (bidB < this.participantB.account.getBalance() && placeBid(bidB, participantB)){
                this.bidPlaced = true;
                System.out.println("Your bid was accepted!");
            } else {
                if (bidB > this.participantB.account.getBalance() || bidB < this.currentBid){
                    keepGoing = false;
                    endAuction();
                    System.out.println("You do not have enough money in your account to place this bid!");
                } // end if
                System.out.println("Your bid was not accepted!");
                if(winner == null && bidPlaced == false){
                    System.out.println("The starting bid is: " + item.getStartBid());
                } // end if
                else{
                    System.out.println("The Current Highest bid is: " + currentBid + " by " + winner.getName());
                } // end else
            } // end if else
        } // end while
    } // end Acution constructor

    private void start() {
        timer.cancel();
        timer = new Timer();
        AuctionTimerTask timerTask = new AuctionTimerTask();
        timer.schedule(timerTask, 0, 1000);
    } // end start

    private boolean placeBid(int newBid, Customer participant) {
        if (newBid > currentBid) {
            currentBid = newBid;
            winner = participant;
            System.out.println("New bid of " + currentBid + " placed by " + winner.getName() + "!");
            timer.cancel();
            timer = new Timer();
            timeLeft = 30; // reset the timeLeft variable
            return true;
        } // end if 
        else{
            System.out.println("Better Luck next time when you have more money!");
            return false;
        } // end else
    } // end placeBid

    private void endAuction() {
        timer.cancel();
        if (bidPlaced == true) {
            int winningBid = this.getWinningBid();
            int startBid = item.getStartBid();
            this.percentageIncrease = (int) ((winningBid - startBid) / (double) startBid * 100);
            System.out.println("The winner is " + winner.getName() + "!");
            viewAll();
            // System.exit(0);
        } 
    } // end endAuction

    public int getWinningBid(){
        if (bidPlaced == true){
            return this.currentBid;
        } // end if
        else{
            return 0;
        }
    } // end getWinningBid

    public Customer getWinner(){
        return this.winner;
    } // end getWinner

    public Item getItem(){
        return this.item;
    } // end getItem
    public int getPercentageIncrease(){
        return this.percentageIncrease;
    } // end getPercentageIncrease
    
    public void viewAll(){
        System.out.println("\tItem: " + item.getName());
        System.out.println("\tItem ID: " + item.getItemID());
        System.out.println("\tStarting Bid: " + item.getStartBid());
        System.out.println("\tWinning Bid: " + currentBid);
        System.out.println("\tTime Left: " + timeLeft);
        System.out.println("\tParticipant A: " + participantA.getName());
        System.out.println("\tParticipant B: " + participantB.getName());
        System.out.println("\tPercentage Increase: " + this.percentageIncrease + "%");
        if (winner == null){
            System.out.println("Item Unsold");
        } // end if
        else{
            System.out.println("\tWinner: " + winner.getName());
        }
    } // end viewAll

    private class AuctionTimerTask extends TimerTask implements Serializable{
        public void run() {
        //System.out.println("timerleft is: " + timeLeft);
            if (timeLeft == 0) {
                endAuction();
                if (bidPlaced == false){
                    System.out.println("No bids were placed! \n Auction ended!");
                } // end if
                
            } else {
                timeLeft--;
                String timeLeftString = "Time left: " + timeLeft + " seconds: ";
                System.out.print("\r" + timeLeftString);
            } // end if else
        } // end run
    } // end class Def
} // end class def
