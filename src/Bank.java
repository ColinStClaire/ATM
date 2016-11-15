import java.util.ArrayList;
public class Bank {
  public ArrayList<Account> accounts;

  public Bank() {
    accounts = new ArrayList<>();
  }

  public Account returnAccount(int accountId) {
    Account thisAcc;
    try {
      if ((thisAcc = getAccount(accountId)) != null) {
        return thisAcc;
      }
    } catch (Exception ex) {
      throw new NullPointerException(ex.getMessage());
    }
    return null;
  }

  private Account getAccount(int accountId) {
    for (Account a : accounts) {
      if (a.getAccountId() == accountId) {
        return a;
      }
    }
    return null;
  }
}