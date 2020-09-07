package com.company;

import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintNumberRunnable implements Runnable {
    public static final int MAX_LIMIT = 5;
    private final PrintWriter output;
    private String name = "-1";
    private int limit = MAX_LIMIT;
    private boolean isSleeping = false;

    /**
     * PrintNumberRunnable constructor
     *
     * @param name  Current thread name
     * @param limit Limit for count
     * @param outer Output stream
     */
    PrintNumberRunnable(String name, int limit, PrintStream outer) {
        this.output = new PrintWriter(outer, true);
        this.name = name;
        if (limit > MAX_LIMIT) {
            limit = MAX_LIMIT;
        }
        this.limit = limit;
    }

    /**
     * PrintNumberRunnable constructor
     *
     * @param name       Current thread name
     * @param limit      Limit for count
     * @param outer      Output stream
     * @param isSleeping Flag to process sleeping threads
     */
    PrintNumberRunnable(String name, int limit, PrintStream outer, boolean isSleeping) {
        this.output = new PrintWriter(outer, true);
        this.name = name;
        if (limit > MAX_LIMIT) {
            limit = MAX_LIMIT;
        }
        this.limit = limit;
        this.isSleeping = isSleeping;
    }

    /**
     * Function that calls on thread.start();
     */
    @Override
    public void run() {
        this.output.printf("Started processing \"%s\" \n", this.name);

        int i = this.limit;
        do {
            this.output.printf("Countdown in thread named \"%s\". Count: %d \n", this.name, i);

            // If isSleeping mode is set we sleep current thread for random number in boundaries [500, 2500] ms
            if (this.isSleeping && !Thread.currentThread().isInterrupted()) {
                try {
                    float sleepingTime = (float) (500 + Math.random() * 2000);

                    this.output.printf(
                            "Thread \"%s\" going to sleep for %.2f seconds \n",
                            this.name,
                            sleepingTime / 1000
                    );

                    Thread.sleep((long) sleepingTime);
                } catch (InterruptedException ignored) {
                    break;
                }
            }

            i--;
        }
        // While we count to 0 or while thread is interrupted
        while (i >= 0 && !Thread.currentThread().isInterrupted());
    }
}
