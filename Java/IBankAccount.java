public interface IBankAccount {

    /**
     * Get current account balance - synchronized
     * @return Account balance
     */
    int getBalance();

    /**
     * Get account number
     * @return Account number
     */
    int getAccountNumber();

    /**
     * Get account holder name
     * @return Account holder name
     */
    String getAccountHolder();

    /**
     * Do a deposit transaction - synchronized
     * @param transaction Transaction
     */
    void deposit(Transaction transaction);

    /**
     * Do a withdraw transaction - synchronized
     * @param transaction Transaction
     */
    void withdrawal(Transaction transaction);

    /**
     * Check account is overdrawn - synchronized
     * @return Whether over drawn or not
     */
    boolean isOverdrawn();

    /**
     * Print account statement
     */
    void printStatements();

    /**
     * Print account balance
     */
    void printBankAccountBalance();
}