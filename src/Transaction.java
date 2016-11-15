import java.util.Date;

public class Transaction {
  public Account currentAcc;
  public Date timeOfTrans;

  public Transaction(Account currentAcc) {
    this.currentAcc = currentAcc;
    timeOfTrans = new Date();
  }

  public void deposit(float amt) {
    currentAcc.creditAccount(amt);
  }

  public Boolean withdraw(float amt) {
    return currentAcc.debitAccount(amt);
  }

}