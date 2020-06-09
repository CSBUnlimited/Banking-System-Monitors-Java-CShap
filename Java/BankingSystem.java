public class BankingSystem {

    public static void main(String[] args) throws InterruptedException {
        
        // Consts
        final String STUDENTS_THREAD_GROUP_NAME = "STUDENTS_THREAD_GROUP_NAME";
        final String ORGANISATIONS_THREAD_GROUP_NAME = "ORGANISATIONS_THREAD_GROUP_NAME";

        final String STUDENT_RIK = "Rik";
        final String STUDENT_SUE = "Sue";

        // Create bank account object. This is a shared resource.
        CommonMethod.printDoubleLineSeparator("Creating bank account");
        IBankAccount accountRik = new BankAccount(STUDENT_RIK, 1, 1000);
        IBankAccount accountSue = new BankAccount(STUDENT_SUE, 2, 2000);
        IBankAccount[] studentAccounts = { accountRik, accountSue };
        CommonMethod.printSingleLineSeparator();
        System.out.println();

        // Create thread groups
        CommonMethod.printDoubleLineSeparator("Creating thread groups");
        ThreadGroup studentsThreadGroup = new ThreadGroup(STUDENTS_THREAD_GROUP_NAME);
        ThreadGroup organisationsThreadGroup = new ThreadGroup(ORGANISATIONS_THREAD_GROUP_NAME);
        System.out.println("Students and Organizations thread groups created");
        CommonMethod.printSingleLineSeparator();
        System.out.println();

        // Create threads for student, university and loan company
        CommonMethod.printDoubleLineSeparator("Creating student thread");
        BaseThread studentRik = new Student(STUDENT_RIK, accountRik, studentsThreadGroup);
        BaseThread studentSue = new Student(STUDENT_SUE, accountSue, studentsThreadGroup);
        System.out.println("Rik and Sue student threads created.");
        CommonMethod.printSingleLineSeparator();
        System.out.println();

        CommonMethod.printDoubleLineSeparator("Creating university thread");
        BaseThread universityUow = new University("UoW", studentAccounts, organisationsThreadGroup);
        System.out.println("University thread created.");
        CommonMethod.printSingleLineSeparator();
        System.out.println();

        CommonMethod.printDoubleLineSeparator("Creating loan company thread");
        BaseThread loanCompanyLB = new LoanCompany("LBF", studentAccounts, organisationsThreadGroup);
        System.out.println("Loan company thread created.");
        CommonMethod.printSingleLineSeparator();
        System.out.println();

        // Start student, university and loan company threads
        studentRik.start();
        studentSue.start();
        universityUow.start();
        loanCompanyLB.start();

        // Wait for threads to terminate
        try {
            studentRik.join();
            studentSue.join();
            universityUow.join();
            loanCompanyLB.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        CommonMethod.printSingleLineSeparator();
        System.out.println();
        System.out.println("Threads successfully terminated");

        // Print final bank statements
        CommonMethod.printDoubleLineSeparator("Final bank statements");
        System.out.println();

        for (IBankAccount bankAccount : studentAccounts) {
            bankAccount.printStatements();
            System.out.println();
        }

        CommonMethod.printSingleLineSeparator();
        System.out.println();
        // Get final account balances
        accountRik.printBankAccountBalance();
        accountSue.printBankAccountBalance();

        System.out.println();
        CommonMethod.printSingleLineSeparator();
        System.out.println();
    }
}