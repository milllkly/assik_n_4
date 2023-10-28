import java.util.Scanner;

// Factory Pattern Components

interface FinancialAccount {
    void deposit(double amount);
    double getBalance();
}

class SavingsAccount implements FinancialAccount {
    private double balance = 0.0;
    @Override
    public void deposit(double amount) {
        balance += amount;
    }
    @Override
    public double getBalance() {
        return balance;
    }
}

class CurrentAccount implements FinancialAccount {
    private double balance = 0.0;
    @Override
    public void deposit(double amount) {
        balance += amount;
    }
    @Override
    public double getBalance() {
        return balance;
    }
}

class FixedDepositAccount implements FinancialAccount {
    private double balance = 0.0;
    @Override
    public void deposit(double amount) {
        balance += amount;
    }
    @Override
    public double getBalance() {
        return balance;
    }
}

class AccountFactory {
    public static FinancialAccount createAccount(String type) {
        switch (type.toLowerCase()) {
            case "savings": return new SavingsAccount();
            case "current": return new CurrentAccount();
            case "fixeddeposit": return new FixedDepositAccount();
            default: throw new IllegalArgumentException("Invalid account type");
        }
    }
}

// Observer Pattern Components

interface AccountBalanceObserver {
    void notifyBalanceChange(double newBalance);
}

class SmsNotifier implements AccountBalanceObserver {
    @Override
    public void notifyBalanceChange(double newBalance) {
        System.out.println("SMS sent: Your new account balance is: " + newBalance);
    }
}

public class FinancialSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FinancialAccount account = null;
        SmsNotifier smsNotifier = new SmsNotifier();

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1: Create an account");
            System.out.println("2: Deposit money");
            System.out.println("3: Check balance");
            System.out.println("4: Exit");
            System.out.print("Choose an option (1/2/3/4): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please select a valid option.");
                continue;
            }

            switch (choice) {
                case 1:
                    if (account != null) {
                        System.out.println("An account is already created. Proceed with deposit or check balance.");
                        break;
                    }
                    System.out.println("Which account would you like to create? (Savings, Current, FixedDeposit)");
                    String accountType = scanner.nextLine();
                    try {
                        account = AccountFactory.createAccount(accountType);
                        System.out.println(accountType + " account created successfully.");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    if (account == null) {
                        System.out.println("Please create an account first.");
                        break;
                    }
                    System.out.print("Enter the amount to deposit: ");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        account.deposit(amount);
                        smsNotifier.notifyBalanceChange(account.getBalance());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid amount entered.");
                    }
                    break;

                case 3:
                    if (account == null) {
                        System.out.println("Please create an account first.");
                        break;
                    }
                    System.out.println("Your account balance is: " + account.getBalance());
                    break;

                case 4:
                    System.out.println("Thank you for using the financial system. Goodbye!");
                    return;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }
}

