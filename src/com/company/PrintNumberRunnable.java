package com.company;

import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintNumberRunnable implements Runnable {
    public static final int MAX_LIMIT = 1000;
    private final PrintWriter output;
    private String name = "-1";
    private int limit = MAX_LIMIT;

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
     * Function that calls on thread.start();
     */
    @Override
    public void run() {
        this.output.printf("Started processing \"%s\" \n", this.name);

        int i = this.limit;
        do {
            this.output.printf("Countdown in thread named \"%s\". Count: %d \n", this.name, i);

            i--;
        }
        // While we count to 0 or while thread is interrupted
        while (i >= 0 && !Thread.currentThread().isInterrupted());
    }
}
