package org.personal.concurrency.exercises.runnable;

public class MultiExecutorRunnableA implements Runnable{
    @Override
    public void run() {
        System.out.println("MultiExecutorRunnableA is running");
    }
}
