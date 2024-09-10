import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private double balance;
    private int pin;
    private List<String> transactionHistory;

    public ATM(int pin, double balance) {
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();
        if (enteredPin == pin) {
            System.out.println("PIN is correct. Please select an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdrawCash(scanner);
                    break;
                case 3:
                    depositCash(scanner);
                    break;
                case 4:
                    changePin(scanner);
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    start();
            }
        } else {
            System.out.println("Invalid PIN. Please try again.");
            start();
        }
    }

    private void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
        start();
    }

    private void withdrawCash(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (amount > balance) {
            System.out.println("Insufficient balance. Please try again.");
            withdrawCash(scanner);
        } else {
            balance -= amount;
            transactionHistory.add("Withdrawal: -$" + amount);
            System.out.println("Withdrawal successful. Your new balance is: $" + balance);
            start();
        }
    }

    private void depositCash(Scanner scanner) {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        balance += amount;
        transactionHistory.add("Deposit: +$" + amount);
        System.out.println("Deposit successful. Your new balance is: $" + balance);
        start();
    }

    private void changePin(Scanner scanner) {
        System.out.print("Enter your current PIN: ");
        int currentPin = scanner.nextInt();
        if (currentPin == pin) {
            System.out.print("Enter your new PIN: ");
            int newPin = scanner.nextInt();
            pin = newPin;
            System.out.println("PIN changed successfully.");
            start();
        } else {
            System.out.println("Invalid current PIN. Please try again.");
            changePin(scanner);
        }
    }

    private void viewTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        start();
    }

    public static void main(String[] args) {
        ATM atm = new ATM(1234, 1000.0);
        atm.start();
    }
}