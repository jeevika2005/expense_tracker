package com.example;

import java.io.*;
import java.util.*;

public class ExpenseTracker {
    static Scanner scanner = new Scanner(System.in);
    static List<String> expenses = new ArrayList<>();
    static final String FILE_NAME = "expenses.txt";

    public static void main(String[] args) {
        loadExpenses();

        while (true) {
            System.out.println("\n==== Expense Tracker ====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1": addExpense(); break;
                case "2": viewExpenses(); break;
                case "3":
                    saveExpenses();
                    System.out.println("Expenses saved. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    static void addExpense() {
        System.out.print("Amount: ");
        String amount = scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Note: ");
        String note = scanner.nextLine();

        String entry = String.format("%s | %s | %s | %s", date, amount, category, note);
        expenses.add(entry);
        System.out.println("✅ Expense added.");
    }

    static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses yet.");
        } else {
            System.out.println("\n--- Your Expenses ---");
            for (String e : expenses) {
                System.out.println(e);
            }
        }
    }

    static void loadExpenses() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                expenses.add(line);
            }
        } catch (IOException e) {
            // File might not exist yet — that's OK
        }
    }

    static void saveExpenses() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String e : expenses) {
                bw.write(e);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Failed to save expenses.");
        }
    }
}

