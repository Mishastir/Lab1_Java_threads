package com.company;

import java.util.Scanner;

public class Main {
    public static final String THREAD_NAME_PREFIX = "Thread #";

    /**
     * Get integer value from console input
     *
     * @param entityName Text that will be printed
     * @return Integer
     */
    public static int getIntegerFromInput(String entityName) {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.printf("Enter %s (integer): ", entityName);
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong input!!!");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int numberOfThreads = getIntegerFromInput("number of processing threads");
        int limit = getIntegerFromInput("limit for threads");

        // Just set limits to be sure that everything will be computed without lags
        if (numberOfThreads > 5) {
            System.out.println("Entered number of threads is too big, number set to maximum allowed value: 5 threads");
            numberOfThreads = 5;
        }

        Thread[] threadsLimitedWithNumber = new Thread[numberOfThreads];

        for (int i = 0; i < threadsLimitedWithNumber.length; i++) {
            String threadName = THREAD_NAME_PREFIX + i;

            PrintNumberRunnable runnable = new PrintNumberRunnable(threadName, limit, System.out);

            Thread thread = new Thread(runnable, threadName);

            threadsLimitedWithNumber[i] = thread;

            thread.start();
        }

        for (Thread thread : threadsLimitedWithNumber) {
            if (thread.isInterrupted()) {
                thread.join();
            }
        }

        Thread[] threadsLimitedWithTime = new Thread[numberOfThreads];

        for (int i = 0; i < threadsLimitedWithTime.length; i++) {
            String threadName = THREAD_NAME_PREFIX + i + " (sleeping)";

            PrintNumberRunnable runnable = new PrintNumberRunnable(threadName, limit, System.out, true);

            Thread thread = new Thread(runnable, threadName);

            threadsLimitedWithTime[i] = thread;

            thread.start();

            Thread.sleep(1000);
            thread.interrupt();
        }

        // Foreach thread in threads
        for (Thread thread : threadsLimitedWithTime) {
            thread.join();
        }

        System.out.println("Main end");
    }
}
