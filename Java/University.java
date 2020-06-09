public class University extends BaseThread {

    private String universityName;
    private IBankAccount[] studentBankAccounts;

    public University(String universityName, IBankAccount[] studentBankAccounts, ThreadGroup threadGroup) {
        super(threadGroup, universityName);
        this.universityName = universityName;
        this.studentBankAccounts = studentBankAccounts;
    }

    /**
     * Run method for the thread
     */
    @Override
    public void run() {

        Transaction[] transactions = { 
            new Transaction(1000, "Entrance fee"), 
            new Transaction(1500, "1st Semester fee"),
            new Transaction(2500, "2nd Semester fee")
        };

        for (Transaction transaction : transactions) {

            try {
                CommonMethod.threadSleep(CommonMethod.randomNextInt(5) + 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (IBankAccount studentBankAccount : studentBankAccounts) {
                studentBankAccount.withdrawal(transaction);
            }
        }

        CommonMethod.printTerminateThread();
    }
    
}