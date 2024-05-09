import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        try {
            // create a new socket
            Socket socket = new Socket("localhost", 8888);

            // create input and output streams
            OutputStreamWriter os = new OutputStreamWriter(socket.getOutputStream());
            PrintWriter out = new PrintWriter(os);
            Scanner scanner = new Scanner(System.in);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // read account number and pin from user
            System.out.println("Please enter your Account Number: ");
            int accountNum = scanner.nextInt();
            System.out.println("Please enter your pin");
            int pin = scanner.nextInt();
            // send account number and pin to server
            out.println(accountNum);
            out.println(pin);
            out.flush();
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.flush();
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            // read response from server
            Object response = inStream.readObject();
            if (response instanceof Customer) {
                // customer found, access the menu
                Customer customer = (Customer) response;
                System.out.println("Welcome " + customer.getName());

                boolean keepGoing = true;
                while (keepGoing) {
                    // display menu
                    System.out.println("0. Quit \n1. Customer Menu");
                    int choice = scanner.nextInt();
                    if (choice == 0){
                        outStream.writeObject(customer);
                        keepGoing = false;
                    }
                    if (choice == 1){
                        customer.menu();
                    }
                }
            } else if(!(response instanceof Customer)) {
                // invalid account number or pin
                System.out.println("Invalid Account Number or Pin!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
