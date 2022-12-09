package de.curano.javautility.runnable;

import java.util.function.Consumer;

public class AsyncScheduler {

    private RunnableHandler runnableHandler;
    private Consumer<AsyncScheduler> runnable;
    private Long delay = null;
    private Long time = null;
    private Long lastRun = null;

    protected AsyncScheduler(RunnableHandler runnableHandler, Consumer<AsyncScheduler> runnable, long delay, long lastRun) {
        this.runnableHandler = runnableHandler;
        this.runnable = runnable;
        this.delay = delay;
        this.lastRun = lastRun;
    }

    protected AsyncScheduler(RunnableHandler runnableHandler, Consumer<AsyncScheduler> runnable, long delay, long time, long lastRun) {
        this.runnableHandler = runnableHandler;
        this.runnable = runnable;
        this.delay = delay;
        this.time = time;
        this.lastRun = lastRun;
    }

    protected Consumer<AsyncScheduler> getRunnable() {
        return runnable;
    }

    protected Long getDelay() {
        return delay;
    }

    protected Long getTime() {
        return time;
    }

    protected Long getLastRun() {
        return lastRun;
    }

    protected Long setLastRun(Long lastRun) {
        return this.lastRun = lastRun;
    }

    protected void setDelay(Long delay) {
        this.delay = delay;
    }

    public void cancel() {
        runnableHandler.cancel(this);
    }

}
