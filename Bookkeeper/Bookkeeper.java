import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    private String date;
    private String description;
    private double amount;
    private String category;

    public Transaction(String date, String description, double amount, String category) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public String toString() {
        return "Date: " + date + ", Description: " + description + ", Amount: " + amount + ", Category: " + category;
    }
}

public class Bookkeeper {
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Bookkeeper Menu ---");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    viewTransactions();
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addTransaction() {
        System.out.print("Enter the date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter the description: ");
        String description = scanner.nextLine();
        System.out.print("Enter the amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter the category (e.g., Food, Rent, etc.): ");
        String category = scanner.nextLine();

        Transaction transaction = new Transaction(date, description, amount, category);
        transactions.add(transaction);
        System.out.println("Transaction added successfully!");
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions recorded yet.");
            return;
        }
        System.out.println("\nAll Transactions:");
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println((i + 1) + ". " + transactions.get(i).toString());
        }
    }
}
