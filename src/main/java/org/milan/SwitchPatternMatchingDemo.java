package org.milan;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author Milan Rathod
 */
public class SwitchPatternMatchingDemo {
    public static void main(String[] args) {
        SavingsAccount savingsAccount = new SavingsAccount();
        TermAccount termAccount = new TermAccount();
        CurrentAccount currentAccount = new CurrentAccount();

        System.out.println(getBalanceWithOutSwitchPattern(savingsAccount));
        System.out.println(getBalanceWithOutSwitchPattern(termAccount));
        System.out.println(getBalanceWithOutSwitchPattern(currentAccount));

        System.out.println(getBalanceWithSwitchPattern(savingsAccount));
        System.out.println(getBalanceWithSwitchPattern(termAccount));
        System.out.println(getBalanceWithSwitchPattern(currentAccount));

        System.out.println(processInputOld("Test"));
        System.out.println(processInputNew("Test"));

        getDayOfWeek();
        patternMatchingInstanceOf();
    }

    private static double getBalanceWithOutSwitchPattern(Account account) {
        double balance = 0;
        if (account instanceof SavingsAccount sa) {
            balance = sa.getSavings();
        } else if (account instanceof TermAccount ta) {
            balance = ta.getTermAccount();
        } else if (account instanceof CurrentAccount ca) {
            balance = ca.getCurrentAccount();
        }
        return balance;
    }

    static double getBalanceWithSwitchPattern(Account account) {
        double result = 0;
        switch (account) {
            case null -> throw new RuntimeException("Oops, account is null");
            case SavingsAccount sa -> result = sa.getSavings();
            case TermAccount ta -> result = ta.getTermAccount();
            case CurrentAccount ca -> result = ca.getCurrentAccount();
            default -> result = account.getBalance();
        }
        return result;
    }

    private static String processInputOld(String input) {
        String output = null;
        switch (input) {
            case null -> output = "Oops, null";
            case String s -> {
                if ("Yes".equalsIgnoreCase(s)) {
                    output = "It's Yes";
                } else if ("No".equalsIgnoreCase(s)) {
                    output = "It's No";
                } else {
                    output = "Try Again";
                }
            }
        }
        return output;
    }

    private static String processInputNew(String input) {
        String output = null;
        switch (input) {
            case null -> output = "Oops, null";
            case String s when "Yes".equalsIgnoreCase(s) -> output = "It's Yes";
            case String s when "No".equalsIgnoreCase(s) -> output = "It's No";
            case String s -> output = "Try Again";
        }
        return output;
    }

    private static void getDayOfWeek() {
        final DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        var typeOfDay = switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Working Day";
            case SATURDAY, SUNDAY -> "Day Off";
        };
        System.out.println(typeOfDay);
    }

    private static void patternMatchingInstanceOf() {
        Object obj = "hello world";

        if (obj instanceof String s) {
            final int length = s.length();
            System.out.println(length);
        }
    }

}

class Account {
    double getBalance() {
        return 0;
    }
}

class SavingsAccount extends Account {
    double getSavings() {
        return 100;
    }
}

class TermAccount extends Account {
    double getTermAccount() {
        return 1000;
    }
}

class CurrentAccount extends Account {
    double getCurrentAccount() {
        return 10000;
    }
}
