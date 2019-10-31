package ru.javawebinar.topjava;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.concurrent.TimeUnit;

public class JUnitStopWatch extends Stopwatch {
    private static void logInfo(Description description, String status, long nanos) {
        String testName = description.getMethodName();
        System.out.println(String.format(
                "\n=================================================================================================\n"
                        + "Test completed for method: '%s' %s, spent %d ms", testName, status, TimeUnit.NANOSECONDS.toMillis(nanos))
                + "\n=================================================================================================");
    }

    @Override
    protected void succeeded(long nanos, Description description) {
        logInfo(description, "succeeded", nanos);
    }

    @Override
    protected void failed(long nanos, Throwable e, Description description) {
        logInfo(description, "failed", nanos);
    }

    @Override
    protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
        logInfo(description, "skipped", nanos);
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, "finished", nanos);
    }
}