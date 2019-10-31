package ru.javawebinar.topjava;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.text.DecimalFormat;


public class JUnitTestWatcherSummary extends TestWatcher {
    private long timeStart;

    protected void starting(Description description) {
        timeStart = System.currentTimeMillis();
    }

    protected void finished(Description description) {
        long timeEnd = System.currentTimeMillis();
        double seconds = (timeEnd - timeStart) / 1000.0;
        System.out.println("\nEND OF CLASS TESTING:");
        System.out
                .println("=================================================================================================");
        System.out
                .println("Test completed for class '" + description.getClassName() + "' - ran in: "
                        + new DecimalFormat("0.000").format(seconds) + " sec");
        System.out
                .println("=================================================================================================\n");
    }
}