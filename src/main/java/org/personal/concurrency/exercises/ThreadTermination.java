package org.personal.concurrency.exercises;

import java.math.BigInteger;

public class ThreadTermination {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new BlockingTask());
        thread1.start();

        thread1.interrupt();

        Thread thread2 = new Thread(new LongComputationTask(new BigInteger("2000"), new BigInteger("1000000")));
        thread2.setDaemon(true);
        thread2.start();
        Thread.sleep(10000);


    }

    private static class BlockingTask implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("Blocking task interrupted");
            }
        }
    }

    private static class LongComputationTask implements Runnable {

        private final BigInteger base;
        private final BigInteger power;

        public LongComputationTask(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {

            System.out.println(base+"^"+power+" = " +pow(base, power));
        }

        BigInteger result = BigInteger.ONE;

        private BigInteger pow(BigInteger base, BigInteger power) {
            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) !=0; i=i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }

            return result;
        }
    }

}
