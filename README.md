
---

# ArtHouse: An Object-Oriented Auction House System in Java

ArtHouse is an interactive auction system implemented in Java. It provides a platform for users to participate in auctions, manage inventory, and handle user accounts—all through a simulated environment.

## Features

- **User Login**: Separate login functionalities for Admins and Customers.
- **Admin Features**:
  - Manage customers (add, delete).
  - Verify and unverify items.
  - Manage auctions, including starting and ending them.
  - View and manage inventory (verified and unverified).
  - Financial transactions management.
- **Customer Features**:
  - Participate in auctions.
  - View personal inventory.
  - Handle financial transactions (deposit, withdraw).
- **Auction Functionality**:
  - Start auctions with real-time bids.
  - Monitor auction progress with timers.
  - Bid management and validation.
- **Leaderboards**: Display auction and user statistics.
- **Data Persistence**: Save and load auction data, customer data, and item data using serialization.

## Installation

Clone the repository to your local machine:

```bash
git clone https://github.com/nikhildhoka8/ARTHouse.git
cd ARTHouse
```

Ensure you have Java installed on your machine. To compile and run the application, you will need Java (JDK) and optionally make (for using the provided makefile).

## Usage

The project includes a Makefile for easy compilation and cleaning up of class files. Here are some commands you can use:

To compile and run the main application:
```bash
make run
```

To run the client application, follow these steps:
1. On the main terminal, select the "Login as a Customer" option.
2. Open a new terminal window on the same machine and execute the following command:
```bash
make runClient
```

To clean up compiled files:
```bash
make clean
```

To remove all data files (be careful with this command, as it will delete all saved data):
```bash
make cleanData
```

## Project Structure

- **AuctionHouse.java**: The main class that initializes the application.
- **Client.java**: Handles client-side operations.
- **User.java**: An abstract class providing a base for customer and admin profiles.
- **Admin.java**, **Customer.java**: Define functionalities specific to admins and customers.
- **Item.java**, **Auction.java**: Core classes to manage items and auctions.
- **Leaderboard.java**: Implements leaderboards for auctions and users.
- **Account.java**: Manages financial transactions and balance.
- **GenericLL.java**: A generic linked list implementation used throughout the system.

## Data Persistence

The application uses Java serialization to persist data between sessions. Data files are stored with `.dat` extensions and can be cleaned using the `make cleanData` command.

## License

The project is released under the MIT License. Feel free to use, modify, and distribute the code as you see fit.

---
