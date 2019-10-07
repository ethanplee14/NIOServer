package lib;


public abstract class Runner extends Conditional implements Runnable {

    private volatile boolean running = true;

    public Runner() {}

    @Override
    public void run() {
        while(running && condition().getAsBoolean()){
            try {
                exec();
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void running(boolean running) {
        this.running = running;
    }

    public boolean running() {
        return running;
    }

    protected abstract void exec();
}
