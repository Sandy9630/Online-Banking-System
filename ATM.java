import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }
}

class User {
    private String username;
    private String password;
    private BankAccount account;

    public User(String username, String password, BankAccount account) {
        this.username = username;
        this.password = password;
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    public BankAccount getAccount() {
        return account;
    }
}

class OnlineBankingSystem {
    private ArrayList<User> users;

    public OnlineBankingSystem() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                return user;
            }
        }
        return null;
    }
}

public class Online Banking System {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OnlineBankingSystem system = new OnlineBankingSystem();

        // Adding a test user
        BankAccount testAccount = new BankAccount("123456", "John Doe", 500.00);
        User testUser = new User("john_doe", "password123", testAccount);
        system.addUser(testUser);

        System.out.println("Welcome to the Online Banking System!");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User loggedInUser = system.login(username, password);

        if (loggedInUser != null) {
            System.out.println("Login successful!");
            BankAccount account = loggedInUser.getAccount();
            int option = 0;

            while (option != 4) {
                System.out.println("\n1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Current balance: $" + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println("Thank you for using the Online Banking System. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Invalid username or password.");
        }

        scanner.close();
    }
}
