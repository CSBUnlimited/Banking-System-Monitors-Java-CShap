public class Student extends BaseThread {

    private String studentName;
    private IBankAccount bankAccount;

    public Student(String studentName, IBankAccount bankAccount, ThreadGroup threadGroup) {
        super(threadGroup, studentName);
        this.studentName = studentName;
        this.bankAccount = bankAccount;
    }

    /**
     * Get student name
     * @return Student name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Get bank account
     * @return BankAccount
     */
    public IBankAccount getBankAccount() {
        return bankAccount;
    }

    /**
     * Print student's account balance
     */
    public void printBankAccountBalance() {
        bankAccount.printBankAccountBalance();
    }

    /**
     * Run method for the thread
     */
    @Override
    public void run() {

        final int maxTransactions = 6;
        int completedTransactions = 0;
        int completedDepositTransactions = 0;
        int completedWithdrawTransactions = 0;

        Transaction[] withdrawTransaction = { 
            new Transaction(100, "Reload"), 
            new Transaction(500, "iPad"),
            new Transaction(700, "iPhone"), 
            new Transaction(200, "Eat") 
        };

        Transaction[] depositTransaction = { 
            new Transaction(300, "Deposit 300"), 
            new Transaction(250, "Deposit 250") 
        };

        while (completedTransactions < maxTransactions) {

            if (CommonMethod.randomNextInt(2) == 1) { // 1 mean deposit otherwise withdraw
                if (completedDepositTransactions >= depositTransaction.length) {
                    continue;
                }
                bankAccount.deposit(depositTransaction[completedDepositTransactions++]);
            } 
            else {
                if (completedWithdrawTransactions >= withdrawTransaction.length) {
                    continue;
                }
                bankAccount.withdrawal(withdrawTransaction[completedWithdrawTransactions++]);
            }

            try {
                CommonMethod.threadSleep((CommonMethod.randomNextInt(4) + 1));
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            completedTransactions++;
        }

        CommonMethod.printTerminateThread();
    }
}