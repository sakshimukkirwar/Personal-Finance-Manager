import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonalFinanceManager {
    private static List<Expense> expenses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static File file = new File("expenses.csv");

    public static void main(String[] args) {
        readExpensesFromFile();
        boolean quit = false;
        while (!quit) {
            System.out.println("\nChoose an option: \n1. Add Expense \n2. List Expenses \n3. Display Summary \n4. Quit");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    listExpenses();
                    break;
                case 3:
                    displaySummaryByDate();
                    break;
                case 4:
                    saveExpensesToFile();
                    quit = true;
                    break;
            }
        }
        scanner.close();
    }

    private static void addExpense() {
        System.out.println("Enter expense description:");
        String description = scanner.nextLine();
        System.out.println("Enter amount:");
        double amount = scanner.nextDouble();
        System.out.println("Enter date (YYYY-MM-DD):");
        scanner.nextLine();
        String date = scanner.nextLine();
        Expense expense = new Expense(description, amount, LocalDate.parse(date, dtf));
        expenses.add(expense);
        System.out.println("Expense added successfully.");
    }

    private static void listExpenses() {
        System.out.println("\nExpenses:");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    private static void displaySummaryByDate() {
        System.out.println("\nExpense Summary by Date:");
        Map<LocalDate, Double> summary = new TreeMap<>();
        for (Expense expense : expenses) {
            summary.merge(expense.date, expense.amount, Double::sum);
        }
        summary.forEach((date, total) -> System.out.println(dtf.format(date) + " - Total: $" + total));
    }

    private static void saveExpensesToFile() {
        try (PrintWriter output = new PrintWriter(file)) {
            for (Expense expense : expenses) {
                output.println(expense.toCsvString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void readExpensesFromFile() {
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] data = line.split(",");
                    Expense expense = new Expense(data[0], Double.parseDouble(data[1]), LocalDate.parse(data[2], dtf));
                    expenses.add(expense);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }
    }

    static class Expense {
        String description;
        double amount;
        LocalDate date;

        Expense(String description, double amount, LocalDate date) {
            this.description = description;
            this.amount = amount;
            this.date = date;
        }

        @Override
        public String toString() {
            return description + " - $" + amount + " on " + dtf.format(date);
        }

        public String toCsvString() {
            return description + "," + amount + "," + dtf.format(date);
        }
    }
}