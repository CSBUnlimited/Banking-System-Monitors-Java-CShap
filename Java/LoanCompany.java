public class LoanCompany extends BaseThread {

    private String loanCompanyName;
    private IBankAccount[] studentBankAccounts;

    public LoanCompany(String loanCompanyName, IBankAccount[] studentBankAccounts, ThreadGroup threadGroup) {
        super(threadGroup, loanCompanyName);
        this.loanCompanyName = loanCompanyName;
        this.studentBankAccounts = studentBankAccounts;
    }
    
    /**
     * Run method for the thread
     */
    @Override
    public void run() {

        Transaction[] transactions = { 
            new Transaction(1100, "Student loan 1"),
            new Transaction(2350, "Student loan 2"),
            new Transaction(1900, "Student loan 3")
        };

        for (Transaction transaction : transactions) {

            try {
                CommonMethod.threadSleep(CommonMethod.randomNextInt(10) + 4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (IBankAccount studentBankAccount : studentBankAccounts) {
                studentBankAccount.deposit(transaction);
            }
        }
        
        CommonMethod.printTerminateThread();
    }
}