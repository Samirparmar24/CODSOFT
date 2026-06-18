import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}

class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== Welcome to the ATM ===");

        boolean running = true;
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    checkBalance();
                    break;
                case "2":
                    handleDeposit();
                    break;
                case "3":
                    handleWithdraw();
                    break;
                case "4":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    private void showMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
    }

    private void checkBalance() {
        System.out.printf("\nYour current balance is: $%.2f\n", account.getBalance());
    }

    private void handleDeposit() {
        System.out.print("\nEnter amount to deposit: $");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Invalid amount. Deposit must be greater than 0.");
            return;
        }

        account.deposit(amount);
        System.out.printf("Deposit successful! New balance: $%.2f\n", account.getBalance());
    }

    private void handleWithdraw() {
        System.out.print("\nEnter amount to withdraw: $");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Invalid amount. Withdrawal must be greater than 0.");
            return;
        }

        boolean success = account.withdraw(amount);

        if (success) {
            System.out.printf("Withdrawal successful! New balance: $%.2f\n", account.getBalance());
        } else {
            System.out.printf("Withdrawal failed. Insufficient balance. Current balance: $%.2f\n", account.getBalance());
        }
    }

    private double readAmount() {
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00); // initial balance
        ATM atm = new ATM(account);
        atm.start();
    }
}