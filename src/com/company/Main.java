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

    /**
     * Get array filled with new Thread(PrintNumberRunnable(*))
     *
     * @param length Result array length
     * @param limit  Limit to PrintNumberRunnable
     * @return Thread[]
     */
    private static Thread[] getNewThreadsArray(int length, int limit) {
        Thread[] resultArray = new Thread[length];

        for (int i = 0; i < resultArray.length; i++) {
            String threadName = THREAD_NAME_PREFIX + i;

            PrintNumberRunnable runnable = new PrintNumberRunnable(threadName, limit, System.out);
            resultArray[i] = new Thread(runnable, threadName);
        }

        return resultArray;
    }

    public static void main(String[] args) throws Exception {
        int numberOfThreads = getIntegerFromInput("number of processing threads");
        int limit = getIntegerFromInput("limit for threads");
        int timeout = getIntegerFromInput("time limited timeout");

        Thread[] threadsLimitedWithNumber = Main.getNewThreadsArray(numberOfThreads, limit);

        for (Thread thread : threadsLimitedWithNumber) {
            thread.start();
        }

        for (Thread thread : threadsLimitedWithNumber) {
            System.out.printf("Thread \"%s\" STARTED join ???????????? \n", thread.getName());
            thread.join();
            System.out.printf("Thread \"%s\" ENDED join -----------! \n", thread.getName());
        }

        Thread[] threadsLimitedWithTime = Main.getNewThreadsArray(numberOfThreads, limit);

        for (Thread thread : threadsLimitedWithTime) {
            thread.start();
        }

        Thread.sleep(timeout);

        for (Thread thread : threadsLimitedWithTime) {
            System.out.printf("Thread \"%s\" STARTED INTERRUPT (sleeping thread) ()()()()()() \n", thread.getName());
            thread.interrupt();
            System.out.printf("Thread \"%s\" ENDED INTERRUPT (sleeping thread) ____________ \n", thread.getName());
        }

        System.out.println("Main end");
    }
}
