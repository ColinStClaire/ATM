
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class ATM {
  private Account thisAcc;
  private Bank bank;
  private Random rand;
  private Scanner in;
  private final float AMOUNT_OF_CASH = 100000;
  private final int NUM_OF_ACCOUNTS = 30;
  final int TEST_ACC_NUM = 12345678;
  private static final String dollar =  "|#######====================#######|\n" +
                                 "|#(1)*UNITED STATES OF AMERICA*(1)#|\n" +
                                 "|#**          /===\\   ********  **#|\n" +
                                 "|*# {G}      | (') |             #*|\n" +
                                 "|#*  ******  | /v\\ |    O N E    *#|\n" +
                                 "|#(1)         \\===/            (1)#|\n" +
                                 "|##=========ONE DOLLAR===========##|\n" +
                                 "------------------------------------";

  public ATM() {
    bank = new Bank();
    rand = new Random();
    in = new Scanner(System.in);

    int id;
    // add random 8 digit values to the Bank's list of accounts
    for (int i = 0; i < NUM_OF_ACCOUNTS-1; i++) {
      id = 10000000 + rand.nextInt(99999999-10000000) + 10000000;
      bank.accounts.add(new Account(id));
    }
    // a hard-coded account with accountId: 12345678
    bank.accounts.add(new Account(TEST_ACC_NUM));
 }

 // print functions to avoid writing System... so many times
  public static void pl(){
    System.out.println();
  }
  public static void pl(String msg) {
    System.out.println(msg);
  }

  private void start() {
    int option;
    float amt;
    Transaction t;
    pl();
    pl("Please select from the following options...");
    pl();
    pl("Enter:");
    pl("'1' to Withdraw");
    pl("'2' to Deposit");
    pl("'3' for Account Inquiry");
    pl("'4' to End Session");
    pl();

    option = in.nextInt();

    switch (option) {
      case 1:
        thisAcc = enterAccount();
        t = new Transaction(thisAcc);
        pl();
        pl("Enter the amount you wish to withdraw...");
        amt = in.nextFloat();
        if (t.withdraw(amt)) {
          pl("Please take your cash...");
          for (int i = 0; i < (int) amt; i++) {
            pl(dollar);
          }
          pl("Don't forget your cash!");
          printReceipt();
          anotherChoice();
        } else {
          pl("You do not have enough funds to withdraw $" + amt + ". Please select a smaller amount or cancel.");
          anotherChoice();
        }
      case 2:
        thisAcc = enterAccount();
        t = new Transaction(thisAcc);
        pl();
        pl("Enter the amount you wish to deposit...");
        amt = in.nextFloat();
        t.deposit(amt);
        printReceipt();
        anotherChoice();
      case 3:
        thisAcc = enterAccount();
        displayAllTransactions();
        anotherChoice();
      case 4:
        endSession();
      default:
        pl("'"+ option + "' is not a valid option. Please select a valid option.");
        anotherChoice();
        break;
    }
  }

  private Account enterAccount() {
    Account currentAcc = null;
    pl();
    pl("Please Enter your 8-digit Account Number...");
    int accNum = in.nextInt();
    currentAcc = bank.returnAccount(accNum);
    if (currentAcc == null) {
      pl("Sorry, account number " + accNum + " doesn't appear to exist. Please try again");
      start();
      return null;
    } else {
      return currentAcc;
    }
  }

  private void anotherChoice() {
    pl();
    pl("Would you like another transaction? Enter 'y' for yes or 'n' for no...");
    String yesOrNo = in.next();
    if (yesOrNo.equals("y")) {
      start();
    } else {
      endSession();
    }
  }

  private void displayAllTransactions() {
    if (thisAcc.history.size() < 1) {
      pl("It looks like there have been no transactions associated with this account.");
      start();
    } else {
      for (TransNode thisTrans : thisAcc.history) {
        Date date = thisTrans.timeOfTrans;
        String type = thisTrans.nameOfTrans;
        int transId = thisTrans.transId;
        float transAmt = thisTrans.amount;
        float bal = thisTrans.snapShotBalance;
        pl();
        pl("Transaction ID " + transId + "\n\t" +
                "Date: " + date + "\n\t" +
                "Type: " + type + "\n\t" +
                "Amount: $" + transAmt + "\n\t" +
                "Snapshot Balance: $" + bal);
        pl();
      }
    }
  }

  private void printReceipt() {
    pl();
    pl("Would you like a receipt? Enter 'y' for yes or 'n' for no...");
    String yesOrNo = in.next();
    if (yesOrNo.equals("n")) {
      anotherChoice();
    } else {
      TransNode thisTrans = thisAcc.history.get(thisAcc.history.size() - 1);
      Date date = thisTrans.timeOfTrans;
      String type = thisTrans.nameOfTrans;
      int transId = thisTrans.transId;
      float transAmt = thisTrans.amount;
      float bal = thisAcc.getBalance();
      pl();
      pl("Here is your receipt for Transaction ID " + transId + ":\n\t" +
              "Date: " + date + "\n\t" +
              "Type: " + type + "\n\t" +
              "Amount: $" + transAmt + "\n\t" +
              "Current Balance: $" + bal);
      pl();
    }
  }

  private void endSession() {
    pl("Thank you for choosing iQ Credit Union!");
    System.exit(0);
  }

  public static void main(String args[]) {
    ATM thisAtm = new ATM();
    pl("Welcome to iQ Credit Union!");
    thisAtm.start();
  }
}