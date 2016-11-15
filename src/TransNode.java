import java.util.Date;

public class TransNode {
  public String nameOfTrans;
  public Date timeOfTrans;
  public float amount;
  public float snapShotBalance;
  public int transId;

  public TransNode(String nameOfTrans, float bal, float amount) {
    this.nameOfTrans = nameOfTrans;
    this.snapShotBalance = bal;
    this.amount = amount;
    timeOfTrans = new Date();
    transId = timeOfTrans.hashCode() + this.nameOfTrans.hashCode();
  }
}

