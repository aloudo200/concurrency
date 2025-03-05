package org.personal.concurrency.exercises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SecureVaultExercise {

    public static final int MAX_PASSWORD = 5000;

    private static final Logger logger = LoggerFactory.getLogger(SecureVaultExercise.class);

    public static void main(String[] args) {

        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASSWORD));

        List<Thread> threads = Arrays.asList(new AscendingOrderGuesser(vault), new DescendingOrderGuesser(vault), new Police());

        threads.forEach(Thread::start);

    }

    private static class Vault {
        private final int password;
        public Vault(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) {
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
            return this.password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected Vault vault;
        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MIN_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread: " + this.getName());
            super.start();
        }
    }

    private static class AscendingOrderGuesser extends HackerThread {
        public AscendingOrderGuesser(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {

            for(int guess = 0; guess <= MAX_PASSWORD; guess++) {
               if(vault.isCorrectPassword(guess)) {
                   System.out.printf("Thread with name %s guessed the password correctly: %d%n", this.getName(), guess);
                   System.exit(0);
               }
            }
        }
    }
    private static class DescendingOrderGuesser extends HackerThread {
        public DescendingOrderGuesser(Vault vault) {
            super(vault);
        }
        @Override
        public void run() {
            for(int guess = MAX_PASSWORD; guess >= 0 ; guess--) {
                if(vault.isCorrectPassword(guess)) {
                    System.out.printf("Thread with name  %s guessed the password correctly: %d%n", this.getName(), guess);
                }
            }
        }
    }
    private static class Police extends Thread {
        @Override
        public void run() {
            for(int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
                System.out.println("Police arriving in " + i + " seconds");

            }

            System.out.println("Game over hackers!!!");
            System.exit(0);
        }
    }

}
