import java.util.Random;

public class CommonMethod {

    private final static int MAX_LENGTH = 50;
    private final static char CHAR_DEFAULT = '\u0000';
    private final static char CHAR_DASH = '-';
    private final static char CHAR_EQUAL = '=';
    private final static Random RANDOM = new Random();
    
    public static int randomNextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static void printSingleLineSeparator() {
        printSeparator("", CHAR_DEFAULT, CHAR_DASH);
    }

    public static void printSingleLineSeparator(String title) {
        printSeparator(title, ' ', CHAR_DASH);
    }

    public static void printDoubleLineSeparator() {
        printSeparator("", CHAR_DEFAULT, CHAR_EQUAL);
    }

    public static void printDoubleLineSeparator(String title) {
        printSeparator(title, ' ', CHAR_EQUAL);
    }

    public static void printTerminateThread() {
        System.out.println("Terminating thread " + Thread.currentThread().getName() + ".");
    }

    public static void threadSleep(int seconds) throws InterruptedException {
        System.out.println("Thread [ " + Thread.currentThread().getName() + " ] sleeping for " + seconds + " seconds.");
        Thread.sleep(seconds * 1000);
    }

    private static void printSeparator(String title, char lineTitleSeparator, char lineChar) {

        StringBuilder printString = new StringBuilder();

        int lineTitleSeparatorStartIndex = (MAX_LENGTH - 1 - title.length() - ((lineTitleSeparator == CHAR_DEFAULT) ? 0 : 2)) / 2;
        
        if (lineTitleSeparatorStartIndex < 0) {
            lineTitleSeparatorStartIndex = 0;
        }

        int lineTitleSeparatorEndIndex = lineTitleSeparatorStartIndex + title.length() + ((lineTitleSeparator == CHAR_DEFAULT) ? 0 : 1);

        for (int ind = 0; ind < MAX_LENGTH; ind++) {
            
            if (lineTitleSeparatorStartIndex == ind && lineTitleSeparator != CHAR_DEFAULT) {
                printString.append(lineTitleSeparator);
            }
            else if (lineTitleSeparatorEndIndex == ind && lineTitleSeparator != CHAR_DEFAULT) {
                printString.append(lineTitleSeparator);
            }
            else if (lineTitleSeparatorStartIndex < ind && lineTitleSeparatorEndIndex > ind) {
                printString.append(title);
                ind += title.length() - 1;
            }
            else {
                printString.append(lineChar);
            }
        }

        System.out.println(printString.toString());
    }
}