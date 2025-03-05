package org.personal.concurrency.exercises;


import org.personal.concurrency.ConcurrencyApplication;
import org.personal.concurrency.exercises.runnable.MultiExecutorRunnableA;
import org.personal.concurrency.exercises.runnable.MultiExecutorRunnableB;
import org.personal.concurrency.exercises.runnable.MultiExecutorRunnableC;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiExecutor {

    // Add any necessary member variables here

    private List<Thread> parallelTasks = new ArrayList<>();
    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {

        for (Runnable task : tasks) {
            Thread thread = new Thread(task);
            this.addThread(thread);
        }

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

    public void addThread(Thread thread) {

        this.parallelTasks.add(thread);

    }

    public static void main(String[] args) {
        MultiExecutor multiExecutor = new MultiExecutor(List.of(new MultiExecutorRunnableC(), new MultiExecutorRunnableA(), new MultiExecutorRunnableB()));

    }
}
