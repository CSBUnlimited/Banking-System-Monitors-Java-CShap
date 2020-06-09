public class Transaction {

    private final String doneBy;
    private final String remark;
    private final int amount;

    public Transaction(int amount, String remark) {
        this.doneBy = Thread.currentThread().getName();;
        this.amount = amount;
        this.remark = remark;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public String getRemark() {
        return remark;
    }

    public synchronized int getAmount() {
        return amount;
    }
}