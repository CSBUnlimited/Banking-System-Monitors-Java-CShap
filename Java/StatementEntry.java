public class StatementEntry {

    private final int id;
    private final String doneBy;
    private final String remark;
    private final int amount;
    private final int finalBalance;
    private final TransactionTypeEnum transactionTypeEnum;

    public StatementEntry(int id, TransactionTypeEnum transactionTypeEnum, String doneBy, String remark, int amount, int finalBalance) {
        this.id = id;
        this.transactionTypeEnum = transactionTypeEnum;
        this.doneBy = doneBy;
        this.remark = remark;
        this.amount = amount;
        this.finalBalance = finalBalance;
    }

    public TransactionTypeEnum getTransactionTypeEnum() {
        return transactionTypeEnum;
    }

    public int getFinalBalance() {
        return finalBalance;
    }

    public String getRemark() {
        return remark;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Done by: " + this.getDoneBy() + "\t\tType: " + this.getTransactionTypeEnum().toString() + "\t\tAmount: " + this.getAmount() + "\t\tBalance: " + this.getFinalBalance() + "\t\tRemark: " + this.getRemark();
    }
}