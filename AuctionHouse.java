import java.io.*;
import java.io.Serializable;
import java.util.*;
import java.net.*;


public class AuctionHouse implements Serializable{
    Scanner input = new Scanner(System.in);
    ArrayList<Customer> customerArr = new ArrayList<Customer>();
    ArrayList<Item> unverifiedInventory = new ArrayList<Item>();
    ArrayList<Item> verifiedInventory = new ArrayList<Item>();
    ArrayList<Auction> auctionHistory = new ArrayList<Auction>();
    Account auctionAccount = new Account(100000);

    public static void main(String[] args) {
        AuctionHouse auctionHouse = new AuctionHouse();
    } // end main
    public AuctionHouse(){
        loadData();
        System.out.println("Welcome to ArtHouse: An Object-Oriented Auction House System in Java!");
        boolean keepGoing = true;
        while(keepGoing){
            try{
                int choice;
                System.out.println("0. Quit \n1. Login as an Admin \n2. Login as a Customer \n3. LeaderBoards");
                choice = input.nextInt();
                if(choice == 0){
                    // for(int i = 0; i < customerArr.size(); i++){
                    //     System.out.println(customerArr.get(i).getName());
                    // }
                    saveData();
                    keepGoing = false;
                    System.out.println("Thank you for your time! Have a nice day ahead!");
                    return;
                }
                if (choice == 1){
                    System.out.println("Please enter your Account Number: ");
                    int accountNum = input.nextInt();
                    System.out.println("Please enter your pin");
                    int pin = input.nextInt();
                    if (accountNum == 12345 && pin == 1234){
                        Admin a = new Admin(customerArr, unverifiedInventory, verifiedInventory, auctionHistory, auctionAccount, 12345);
                        a.menu();
                    } // end if
                    else{
                        System.out.println("Invalid Account Number or Pin!");
                    } // end else
                } // end else if
                if (choice == 2){
                    ServerSocket serverSocket = null;
                    try {
                        // start listening for connections on port 8888
                        serverSocket = new ServerSocket(8888);
                        System.out.println("Waiting for connections on PORT 8888 ...");
                    } catch (Exception e) {
                        System.err.println("Could not listen on port: 8888");
                    }

                    Socket clientSocket = null;

                    try {
                        // accept client connection
                        clientSocket = serverSocket.accept();

                        System.out.println("Client Connected");
                        
                    } catch (IOException e) {
                        System.err.println("Accept failed");
                    }
                    OutputStreamWriter os = new OutputStreamWriter(clientSocket.getOutputStream());
                    PrintWriter out = new PrintWriter(os);
                    Scanner scanner = new Scanner(System.in);
                    BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String inputLine;
                    Customer currentCustomer = null;

                    int accountNum = Integer.parseInt(br.readLine());
                    int pin = Integer.parseInt(br.readLine());
                    System.out.println("Accoun Num: " + accountNum);
                    System.out.println("Pin: " + pin);
                    // search for the customer with the given account number and pin
                    for (int i = 0; i < customerArr.size(); i++) {
                        if (customerArr.get(i).getAccountNum() == accountNum&& customerArr.get(i).getPin() == pin) {
                            currentCustomer = customerArr.get(i);
                            System.out.println("Customer Found");
                        }
                    }
                    ObjectOutputStream outStream = new ObjectOutputStream(clientSocket.getOutputStream ());
                    ObjectInputStream inStream = new ObjectInputStream(clientSocket.getInputStream ());
                    System.out.println("Name: " + currentCustomer.getName());
                    if (currentCustomer != null) {
                        // send the customer object to the client
                        outStream.writeObject(currentCustomer);
                        out.flush();
                        // receive customer object back from client
                        Object response = inStream.readObject();
                        if (response instanceof Customer) {
                            currentCustomer = (Customer) response;

                        // update the customer array list
                            for (int i = 0; i < customerArr.size(); i++) {
                                if (customerArr.get(i).getAccountNum() == currentCustomer.getAccountNum()) {
                                    customerArr.set(i, currentCustomer);
                                    break;
                                }
                            }
                        }
                    }
                    // close streams and sockets
                    out.close();
                    br.close();
                    clientSocket.close();
                    serverSocket.close();
                }
                if (choice == 3){
                    Leaderboard a = new Leaderboard(customerArr, auctionHistory);
                    a.menu();
                }
                if (choice > 3 || choice < 0){
                    System.out.println("Invalid Choice!");
                }
            } catch(Exception e){
                
                System.out.println("Invalid Choice!");
            }// end try catch
        } // end while
    } // end constructor

    public void loadData(){
        loadAuctionHistory();
        loadCustomerArr();
        loadUnverifiedInventory();
        loadVerifiedInventory();
        loadAuctionAccount();
    } // end loadData
    
    public void saveData(){
        saveAuctionHistory();
        saveCustomerArr();
        saveUnverifiedInventory();
        saveVerifiedInventory();
        saveAuctionAccount();
    } // end saveData
    
    public void loadAuctionHistory(){
        try {
            FileInputStream fileIn = new FileInputStream("auctionhistory.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            auctionHistory = (ArrayList<Auction>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("Failed to load data from file: " + i.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("Auction class not found: " + c.getMessage());
        }
    } // end loadAuctionHistory
    public void saveAuctionHistory(){
        try {
            FileOutputStream fileOut = new FileOutputStream("auctionhistory.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(auctionHistory);
            out.close();
            fileOut.close();
            System.out.println("Data saved successfully.");
        } catch (IOException i) {
            System.out.println("Failed to save data to file: " + i.getMessage());
        }
    } // end saveAuctionHistory
    public void loadCustomerArr(){
        try {
            FileInputStream fileIn = new FileInputStream("customerarr.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            customerArr = (ArrayList<Customer>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("Failed to load data from file: " + i.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("Customer class not found: " + c.getMessage());
        }
    } // end loadCustomerArr
    public void saveCustomerArr(){
        // for(int i = 0; i < customerArr.size(); i++){
        //     System.out.println(customerArr.get(i).getName());
        // }
        try {
            FileOutputStream fileOut = new FileOutputStream("customerarr.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(customerArr);
            out.flush();
            out.close();
            fileOut.close();
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            System.out.println("Failed to save data to file: " + e.getMessage());
            e.printStackTrace();
        }
    } // end saveCustomerArr
    public void loadUnverifiedInventory(){
        try {
            FileInputStream fileIn = new FileInputStream("unverifiedinventory.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            unverifiedInventory = (ArrayList<Item>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("Failed to load data from file: " + i.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("Item class not found: " + c.getMessage());
        } 
    } // end loadUnverifiedInventory
    public void saveUnverifiedInventory(){
        try {
            FileOutputStream fileOut = new FileOutputStream("unverifiedinventory.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(unverifiedInventory);
            out.close();
            fileOut.close();
            System.out.println("Data saved successfully.");
        } catch (IOException i) {
            System.out.println("Failed to save data to file: " + i.getMessage());
        }
    } // end saveUnverifiedInventory
    public void loadVerifiedInventory(){
        try {
            FileInputStream fileIn = new FileInputStream("verifiedinventory.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            verifiedInventory = (ArrayList<Item>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("Failed to load data from file: " + i.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("Item class not found: " + c.getMessage());
        }
    } // end loadVerifiedInventory
    public void saveVerifiedInventory(){
        try {
            FileOutputStream fileOut = new FileOutputStream("verifiedinventory.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(verifiedInventory);
            out.close();
            fileOut.close();
            System.out.println("Data saved successfully.");
        } catch (IOException i) {
            System.out.println("Failed to save data to file: " + i.getMessage());
        }
    } // end saveVerifiedInventory
    public void loadAuctionAccount(){
        try {
            FileInputStream fileIn = new FileInputStream("auctionaccount.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            auctionAccount = (Account) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println("Failed to load data from file: " + i.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("AuctionAccount class not found: " + c.getMessage());
        }
    } // end loadAuctionAccount
    public void saveAuctionAccount(){
        try {
            FileOutputStream fileOut = new FileOutputStream("auctionaccount.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(auctionAccount);
            out.close();
            fileOut.close();
            System.out.println("Data saved successfully.");
        } catch (IOException i) {
            System.out.println("Failed to save data to file: " + i.getMessage());
        }
    } // end saveAuctionAccount

} // end class defs
