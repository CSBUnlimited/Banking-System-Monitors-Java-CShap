import java.util.ArrayList;
import java.util.List;

public class Statement {

    private final List<StatementEntry> statementEntries;

    private final String accountHolder;
    private final int accountNumber;

    public Statement(String accountHolder, int accountNumber) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.statementEntries = new ArrayList<StatementEntry>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public List<StatementEntry> getStatementEntries() {
        return statementEntries;
    }

    /**
     * Add transaction to the statement - synchronized
     * @param transactionTypeEnum TransactionType
     * @param doneBy Transaction done by
     * @param remark Transaction remark
     * @param amount Transaction amount
     * @param finalBalance Balance after transaction
     */
    public synchronized void addTransaction(TransactionTypeEnum transactionTypeEnum, String doneBy, String remark, int amount, int finalBalance) {
        statementEntries.add(new StatementEntry(statementEntries.size() + 1, transactionTypeEnum, doneBy, remark, amount, finalBalance));
    }

    public void printStatements() {
        System.out.println("Statement for " + getAccountHolder() + "'s(" + getAccountNumber() + ") Account");
        CommonMethod.printDoubleLineSeparator();

        for (StatementEntry statementEntry : statementEntries) {
            System.out.println(statementEntry.toString());
        }

        CommonMethod.printDoubleLineSeparator();
    }
}