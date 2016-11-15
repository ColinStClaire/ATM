import java.util.ArrayList;

public class Account {

  private float balance;
  private int accountId;
  public ArrayList<TransNode> history;

  public Account(int id) {
    balance = 100.00F;
    accountId = id;
    history = new ArrayList<>();
  }

  public void creditAccount(float amount) {
    this.balance += amount;
    this.history.add(new TransNode("Deposit", this.balance, amount));
  }

  public Boolean debitAccount(float amount) {
    if (this.balance - amount < 0) {
      return false;
    }
    this.balance -= amount;
    this.history.add(new TransNode("Withdrawal", this.balance, amount));
    return true;
  }

  public float getBalance() {
    return this.balance;
  }

  public int getAccountId() {
    return this.accountId;
  }

}