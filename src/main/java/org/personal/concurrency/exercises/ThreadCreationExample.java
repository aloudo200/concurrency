package org.personal.concurrency.exercises;

public class ThreadCreationExample {
    public static void main(String[] args) throws InterruptedException {

         Thread thread1 = new Thread(()->{
             Thread.currentThread().setName("thread1");
             System.out.println("We are now in thread: " +  Thread.currentThread().getName());
         });

        Thread thread2 = new Thread(()->{
            Thread.currentThread().setName("thread2");
            System.out.println("We are now in thread: " +  Thread.currentThread().getName());
        });

//        Thread threadThrowsError = new Thread(()->{
//            Thread.currentThread().setName("threadThrowsError");
//            throw new RuntimeException("Intentional Exception");
//        });

        Thread thread3 = new NewThread();
        thread3.start();

//        threadThrowsError.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread t, Throwable e) {
//                System.out.printf("Uncaught exception in thread %s, message %s\n", t.getName(), e.getMessage());
//            }
//        });


    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            Thread.currentThread().setName("threadCreatedFromClassThreadExtension");
            System.out.println("We are now in thread: " + this.getName()); // doing it this way allows for calls to this.getName()
        }
    }
}
