import java.util.InputMismatchException;
import java.util.Scanner;

class Expense{
    int Amount;
    String Description;
    String Category;
    String Date;

    public Expense(int amount, String description, String category, String date) {
        Amount = amount;
        Description = description;
        Category = category;
        Date = date;
    }
}

public class Main {

    public static final String REDCOLOR = "\033[31m";
    public static final String RESETCOLOR = "\033[0m";
    public static final String GREENCOLOR = "\033[32m";
    public static final String PURPLECOLOR = "\033[35m";
    public static final String BLUECOLOR = "\033[34m";

    static Scanner getInput = new Scanner(System.in);

    static Expense[] expenselist = new Expense[150];

    public static void main(String[] args) {
        while (true) {
            clearConsole();
            System.out.print(PURPLECOLOR);
            System.out.println("╔════════════════════════════════════════════╗");
            System.out.println("║            ~ EXPENSE TRACKER ~             ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.println(RESETCOLOR);
            System.out.println(GREENCOLOR + "☰ " + "MENU" + RESETCOLOR + " - ENTER YOUR CHOICE");
            System.out.printf("%-22s%-22s%n", "1.➕ Add Expense", "2.📋 View All Expenses");
            System.out.printf("%-24s%-23s%n", "3.🗑️ Delete Expense", "4.🧹 Clear All Expenses");
            System.out.printf("%-23s%-23s%n", "5.📅 Filter By Date", "6.🔍 Filter By Category");
            System.out.printf("%-23s%-23s%n", "7.📋 View Summary  ", "8.💰 Show Total Expenses");
            System.out.printf("%-23s%n", "9.❌ Exit");
            System.out.print(PURPLECOLOR + "(→) " + RESETCOLOR);

            int choice = getInput.nextInt();

            if (choice >= 1 && choice <= 9) {
                switch (choice) {
                    case 1:
                        AddExpense();
                        break;
                    case 2:
                        ViewAllExpenses();
                        break;
                    case 3:
                        DeleteExpense();
                        break;
                    case 4:
                        ClearAllExpenses();
                        break;
                    case 5:
                        FilterByDate();
                        break;
                    case 6:
                        FilterByCategory();
                        break;
                    case 7:
                        ViewSummary();
                        break;
                    case 8:
                        TotalExpense();
                        break;
                    case 9:
                        System.out.println("😊 Thanks for using EXPENSE TRACKER");
                        return;
                }
            } else {
                System.out.println("❌" + REDCOLOR + " Invalid Choice!,Try Again" + RESETCOLOR);
            }
        }
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void AddExpense() {
        System.out.println("ADD EXPENSE - ENTER DETAILS ⤶");
        for (int i = 0; i < expenselist.length; i++) {
            if (expenselist[i] == null) {
                int amount = 0;
                boolean validInput = false;

                while (!validInput) {
                    System.out.print(BLUECOLOR + "(→) " + RESETCOLOR + " Amount: ");
                    try {
                        amount = getInput.nextInt();
                        getInput.nextLine();
                        validInput = true;
                    } catch (InputMismatchException e) {
                        System.out.println(REDCOLOR + "Invalid input! Please enter a number." + RESETCOLOR);
                        getInput.nextLine();
                    }
                }
                System.out.print(BLUECOLOR + "(→) " + RESETCOLOR + " Description: ");
                String description = getInput.nextLine();

                System.out.print(BLUECOLOR + "(→) " + RESETCOLOR + " Category: ");
                String category = getInput.nextLine();

                System.out.print(BLUECOLOR + "(→) " + RESETCOLOR + " Date (dd-mm-yyyy): ");
                String date = getInput.nextLine();

                expenselist[i] = new Expense(amount, description, category, date);

                System.out.println(GREENCOLOR + "\nExpense Added Successfully!" + RESETCOLOR);
                return;
            }
        }
        System.out.println("Expense list is full");
    }

    private static void ViewAllExpenses() {

        System.out.println(PURPLECOLOR);
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                   All Recorded Expenses                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝" + RESETCOLOR);

        if (expenselist[0] == null) {
            System.out.println(REDCOLOR + "No expenses to show!" + RESETCOLOR);
            return;
        }

        System.out.println("╔══════╦══════════╦══════════════════╦══════════╦════════════╗");
        System.out.printf("║ %-4s ║ %-8s ║ %-16s ║ %-8s ║ %-10s ║%n",
                "No", "Amount", "Description", "Category", "Date");
        System.out.println("╠══════╬══════════╬══════════════════╬══════════╬════════════╣");

        for (int i = 0; i < expenselist.length; i++) {
            if (expenselist[i] != null) {
                System.out.printf("║ %-4d ║ %-8d ║ %-16s ║ %-8s ║ %-10s ║%n",
                        i + 1,
                        expenselist[i].Amount,
                        expenselist[i].Description,
                        expenselist[i].Category,
                        expenselist[i].Date);
            }
        }

        System.out.println("╚══════╩══════════╩══════════════════╩══════════╩════════════╝");
    }

    private static void DeleteExpense() {

        if (expenselist[0] == null) {
            System.out.println(REDCOLOR + "No expenses to delete." + RESETCOLOR);
            return;
        }
        System.out.println("DELETE EXPENSES - ENTER S/NO ⤶");

        for (int i = 0; i < expenselist.length; i++) {
            if (expenselist[i] != null) {
                System.out.println((i + 1) + ". " + expenselist[i].Description + " - " + expenselist[i].Amount);
            }
        }

        System.out.print(PURPLECOLOR + "(→) " + RESETCOLOR);
        int SNo = getInput.nextInt();

        if (SNo >= 0 && SNo < expenselist.length && expenselist[SNo] != null) {
            for (int i = SNo; i < expenselist.length - 1; i++) {
                expenselist[i] = expenselist[i + 1];
            }
            expenselist[expenselist.length - 1] = null;
            System.out.println(GREENCOLOR + "Expense deleted successfully!" + RESETCOLOR);
        } else {
            System.out.println(REDCOLOR + "Invalid index or empty slot!" + RESETCOLOR);
        }
    }

    private static void ClearAllExpenses() {
        for (int i = 0; i < expenselist.length; i++) {
            expenselist[i] = null;
        }
        System.out.println(REDCOLOR + "\nAll Expenses have been cleared!" + RESETCOLOR);
    }

    private static void FilterByCategory() {

        if (expenselist != null && expenselist[0] != null) {

            System.out.println(PURPLECOLOR + "CATEGORIES - " + RESETCOLOR + " ENTER YOUR CHOICE ⤶");

            String[] uniqueCategories = new String[50];
            int catIndex = 0;

            for (Expense expense : expenselist) {
                if (expense == null) continue;

                String cat = expense.Category.trim();
                boolean alreadyExists = false;

                for (int i = 0; i < catIndex; i++) {
                    if (uniqueCategories[i].equalsIgnoreCase(cat)) {
                        alreadyExists = true;
                        break;
                    }
                }

                if (!alreadyExists) {
                    uniqueCategories[catIndex++] = cat;
                    System.out.println(BLUECOLOR + "→ " + RESETCOLOR + cat);
                }
            }

            getInput.nextLine();
            String Choice = getInput.nextLine();

            int count = 0;
            System.out.println("╔══════╦══════════╦══════════════════╦══════════╦════════════╗");
            System.out.printf("║ %-4s ║ %-8s ║ %-16s ║ %-8s ║ %-10s ║%n",
                    "No", "Amount", "Description", "Category", "Date");
            System.out.println("╠══════╬══════════╬══════════════════╬══════════╬════════════╣");

            for (int i = 0; i < expenselist.length; i++) {
                if (expenselist[i] != null &&
                        expenselist[i].Category.trim().equalsIgnoreCase(Choice)) {
                    count++;
                    System.out.printf("║ %-4d ║ %-8d ║ %-16s ║ %-8s ║ %-10s ║%n",
                            count,
                            expenselist[i].Amount,
                            expenselist[i].Description,
                            expenselist[i].Category,
                            expenselist[i].Date);
                }
            }

            if (count == 0) {
                System.out.println(REDCOLOR + "No expenses found in selected category." + RESETCOLOR);
            }

            System.out.println("╚══════╩══════════╩══════════════════╩══════════╩════════════╝");
        } else {
            System.out.println(REDCOLOR + "List is Empty, Enter some Expenses first." + RESETCOLOR);
        }
    }

    private static void FilterByDate() {
        getInput.nextLine();
        System.out.print(BLUECOLOR + "(→) " + RESETCOLOR + " Enter Date (dd-mm-yyyy) to filter: ");
        String inputDate = getInput.nextLine();

        int count = 0;
        boolean founded = false;

        for (int i = 0; i < expenselist.length; i++) {
            if (expenselist[i] != null && expenselist[i].Date.equals(inputDate)) {
                founded = true;
                break;
            }
        }

        if (!founded) {
            System.out.println(REDCOLOR + "No expenses found on " + inputDate + RESETCOLOR);
        } else {
            System.out.println(PURPLECOLOR);
            System.out.println("╔════════════════════════════════════════════════════════════╗");
            System.out.printf("║                  Expenses On " + inputDate + "                    ║");
            System.out.println("\n╚════════════════════════════════════════════════════════════╝" + RESETCOLOR);

            System.out.println("╔══════╦══════════╦══════════════════╦══════════╦════════════╗");
            System.out.printf("║ %-4s ║ %-8s ║ %-16s ║ %-8s ║ %-10s ║%n",
                    "No", "Amount", "Description", "Category", "Date");
            System.out.println("╠══════╬══════════╬══════════════════╬══════════╬════════════╣");

            for (int i = 0; i < expenselist.length; i++) {
                if (expenselist[i] != null && expenselist[i].Date.equals(inputDate)) {
                    count++;
                    System.out.printf("║ %-4d ║ %-8s ║ %-16s ║ %-8s ║ %-10s ║%n",
                            count,
                            expenselist[i].Amount,
                            expenselist[i].Description,
                            expenselist[i].Category,
                            expenselist[i].Date);
                }
            }
            System.out.println("╚══════╩══════════╩══════════════════╩══════════╩════════════╝");
        }
    }

    private static void ViewSummary() {
        String[] CategoryList = new String[50];
        int[] AmountList = new int[50];

        if (expenselist != null && expenselist[0] != null) {
            for (Expense e : expenselist) {
                if (e == null) continue;

                String Category = e.Category;
                int Amount = e.Amount;

                boolean categoryFound = false;

                for (int j = 0; j < CategoryList.length; j++) {
                    if (CategoryList[j] == null) break;
                    if (CategoryList[j].equalsIgnoreCase(Category)) {
                        AmountList[j] += Amount;
                        categoryFound = true;
                        break;
                    }
                }

                if (!categoryFound) {
                    for (int j = 0; j < CategoryList.length; j++) {
                        if (CategoryList[j] == null) {
                            CategoryList[j] = Category;
                            AmountList[j] = Amount;
                            break;
                        }
                    }
                }
            }

        System.out.println(PURPLECOLOR + "SUMMARY ⤶" + RESETCOLOR);
            for (int k = 0; k < CategoryList.length; k++) {
                if (CategoryList[k] != null) {
                    System.out.println(GREENCOLOR + CategoryList[k] + RESETCOLOR + " - "+
                            BLUECOLOR + AmountList[k] + RESETCOLOR);
                }
            }
        }
    }

    private static void TotalExpense() {
        int total = 0;

        System.out.println(PURPLECOLOR);
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║        TOTAL EXPENSES         ║");
        System.out.println("╚═══════════════════════════════╝" + RESETCOLOR);

        if (expenselist[0] == null){
            System.out.println("No Expenses recorded.");
            return;
        }

        for (Expense expense : expenselist) {
            if(expense != null) {
                total += expense.Amount;
            }
        }
        System.out.println(GREENCOLOR + "💰 Total: " + RESETCOLOR + total);
    }
}