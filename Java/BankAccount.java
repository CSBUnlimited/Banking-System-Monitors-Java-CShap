public class BankAccount implements IBankAccount {

    private final int overdrawnAmount = 0;
    private int accountNumber;
    private String accountHolder;
    private int balance;
    private Statement statement;

    public BankAccount(String accountName, int accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountName;
        this.balance = balance;
        this.statement = new Statement(this.getAccountHolder(), this.getAccountNumber());
        System.out.println("Initialized " + this.getAccountHolder() + "(" + getAccountNumber() + ")'s account. Initial balance " + this.getBalance() + ".");
    }

    /**
     * Get current account balance
     * @return Account balance
     */
    @Override
    public synchronized int getBalance() {
        return this.balance;
    }

    /**
     * Get account number
     * @return Account number
     */
    @Override
    public int getAccountNumber() {
        return this.accountNumber;
    }

    /**
     * Get account holder name
     * @return Account holder name
     */
    @Override
    public String getAccountHolder() {
        return this.accountHolder;
    }

    /**
     * Calculate Balance and add transaction to statement - synchronized
     * @param transactionTypeEnum TransactionTypeEnum
     * @param transaction Transaction
     * @return New Balance
     */
    private synchronized int calculateBalance(TransactionTypeEnum transactionTypeEnum, Transaction transaction) {

        if (transactionTypeEnum == TransactionTypeEnum.Deposit) {
            this.balance += transaction.getAmount();
        }
        else if (transactionTypeEnum == TransactionTypeEnum.Withdraw) {
            this.balance -= transaction.getAmount();
        }
        
        this.statement.addTransaction(transactionTypeEnum, transaction.getDoneBy(), transaction.getRemark(), transaction.getAmount(), this.balance);
        return this.balance;
    }

    /**
     * Do a deposit transaction - synchronized
     * @param transaction Transaction
     */
    @Override
    public synchronized void deposit(Transaction transaction) {
        System.out.println("Thread [ " + Thread.currentThread().getName() + " ] depositing " + transaction.getAmount() + " to " + this.getAccountHolder() + "'s account, Reason - " + transaction.getRemark());
        calculateBalance(TransactionTypeEnum.Deposit, transaction);
        notifyAll();
    }

    /**
     * Do a withdraw transaction - synchronized
     * @param transaction Transaction
     */
    @Override
    public synchronized void withdrawal(Transaction transaction) {
        System.out.println("Thread [ " + Thread.currentThread().getName() + " ] trying to withdraw " + transaction.getAmount() + " from " + this.getAccountHolder() + "'s account, Reason - " + transaction.getRemark());
        
        while (isOverdrawn()) {
            try {
                System.out.println("Thread [ " + Thread.currentThread().getName() + " ] waiting to withdraw " + transaction.getAmount() + ", " + this.getAccountHolder() + "'s account is already overdrawn.");
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Thread [ " + Thread.currentThread().getName() + " ] withdrawing " + transaction.getAmount() + " from " + this.getAccountHolder() + "'s account, Reason - " + transaction.getRemark());
        calculateBalance(TransactionTypeEnum.Withdraw, transaction);
        notifyAll();
    }

    /**
     * Check account is overdrawn
     * @return Whether over drawn or not
     */
    @Override
    public synchronized boolean isOverdrawn() {
        return getBalance() < overdrawnAmount;
    }

    /**
     * Print account statement
     */
    @Override
    public void printStatements() {
        this.statement.printStatements();
    }

    /**
     * Print account balance
     */
    @Override
    public void printBankAccountBalance() {
        System.out.println("" + getAccountHolder() + "(" + getAccountNumber() + ")'s account balance is " + getBalance() + ".");
    }
}