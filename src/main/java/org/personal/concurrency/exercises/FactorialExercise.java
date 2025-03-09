package org.personal.concurrency.exercises;

import lombok.SneakyThrows;
import org.personal.concurrency.exercises.runnable.FactorialThread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FactorialExercise {

    @SneakyThrows
    public static void main(String[] args) {
        List<Long> inputNumbers = Arrays.asList(10000000L, 3435L, 35435L, 2324L, 23L, 2435L, 5566L);
        List<FactorialThread> threads = new ArrayList<>();

        inputNumbers.forEach(n -> threads.add(new FactorialThread(n)));

        threads.forEach(Thread::start);
        for(Thread thread : threads) {
            thread.setDaemon(true);
            thread.join(2000);
        }

        for(int i = 0; i < inputNumbers.size(); i++) {
            FactorialThread factorialThread = threads.get(i);
            if(factorialThread.isFinished()) {
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }

    }

}
