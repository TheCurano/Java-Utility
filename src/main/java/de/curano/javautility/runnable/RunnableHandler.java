package de.curano.javautility.runnable;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class RunnableHandler {

    private Thread thread;
    private CopyOnWriteArrayList<AsyncScheduler> asyncRunnables = new CopyOnWriteArrayList<AsyncScheduler>();
    private boolean runThread = true;

    public RunnableHandler() {
        thread = new Thread(() -> {
            while (runThread) {
                for (AsyncScheduler scheduler : (ArrayList<AsyncScheduler>) asyncRunnables.clone()) {
                    if (scheduler.getTime() == null && (System.currentTimeMillis() - scheduler.getLastRun()) >= scheduler.getDelay()) {
                        new Thread(() -> {
                            scheduler.setLastRun(System.currentTimeMillis());
                            scheduler.getRunnable().accept(scheduler);
                        }).start();
                        asyncRunnables.remove(scheduler);
                    } else if (scheduler.getTime() != null
                            && ((scheduler.getDelay() == null && (System.currentTimeMillis() - scheduler.getLastRun()) >= scheduler.getTime())
                            || (scheduler.getDelay() != null && (System.currentTimeMillis() - scheduler.getLastRun()) >= scheduler.getDelay()))) {
                        scheduler.setDelay(null);
                        new Thread(() -> {
                            scheduler.setLastRun(System.currentTimeMillis());
                            scheduler.getRunnable().accept(scheduler);
                        }).start();
                    }
                }
                try {
                    Thread.sleep(5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public AsyncScheduler runTaskAsyncLater(Consumer<AsyncScheduler> runnable, long delay) {
        AsyncScheduler asyncScheduler = new AsyncScheduler(this, runnable, delay, System.currentTimeMillis());
        asyncRunnables.add(asyncScheduler);
        return asyncScheduler;
    }

    public AsyncScheduler runTaskAsyncTimer(Consumer<AsyncScheduler> runnable, long delay, long time) {
        AsyncScheduler asyncScheduler = new AsyncScheduler(this, runnable, delay, time, System.currentTimeMillis());
        asyncRunnables.add(asyncScheduler);
        return asyncScheduler;
    }

    public void cancel(AsyncScheduler asyncScheduler) {
        asyncRunnables.remove(asyncScheduler);
    }

    public void stopThread() {
        runThread = false;
        while (thread.isAlive()) {
            try {
                Thread.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
