package org.personal.concurrency.exercises;

import org.personal.concurrency.exercises.runnable.MultiExecutorRunnableA;
import org.personal.concurrency.exercises.runnable.MultiExecutorRunnableB;
import org.personal.concurrency.exercises.runnable.MultiExecutorRunnableC;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    // Add any necessary member variables here

    private List<Thread> parallelTasks;
    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {

        List<Thread> parallelThreads = new ArrayList<>();

        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            parallelThreads.add(thread);
        }

        this.parallelTasks = parallelThreads;

        executeAll();
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        for (Thread thread : parallelTasks) {
            thread.start();
        }
    }

    public static void main(String[] args) {
        new MultiExecutor(List.of(new MultiExecutorRunnableC(), new MultiExecutorRunnableA(), new MultiExecutorRunnableB()));
    }
}
