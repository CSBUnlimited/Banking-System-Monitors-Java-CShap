public abstract class BaseThread extends Thread {

    /**
     * Get current thread name
     * @return Thread name
     */
    protected String getThreadName() {
        return super.getName();
    }

    /**
     * Base Thread - Constuctor
     * @param threadGroup ThreadGroup
     * @param name Thread name
     */
    public BaseThread(ThreadGroup threadGroup, String name) {
        super(threadGroup, name);
    }

    /**
     * Call to start the the Thread
     */
    @Override
    public synchronized void start() {
        System.out.println("Starting " + super.getName() + " thread.");
        super.start();
    }
    
    /**
     * Run method for the thread
     */
    @Override
    public abstract void run();
}