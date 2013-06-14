package com.djdch.simpleeventsystem;

import com.djdch.simpleeventsystem.event.ConsoleOutputEvent;
import com.djdch.simpleeventsystem.event.EventDispatcher;
import com.djdch.simpleeventsystem.thread.ComplexThread;
import com.djdch.simpleeventsystem.thread.SimpleThread;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SimpleThread runnable1 = new SimpleThread("Bob");
        ComplexThread runnable2 = new ComplexThread("Joe");

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

//        ExceptionEvent event = new ExceptionEvent(new Exception("Should not be received"));
//        EventDispatcher.dispatch(event);

        thread1.start();
        thread2.start();

//        ExceptionEvent event2 = new ExceptionEvent(new Exception("Empty"));
//        EventDispatcher.dispatch(event2);

        ConsoleOutputEvent event1 = new ConsoleOutputEvent("Empty");
        EventDispatcher.dispatch(event1);

        int counter = 0;

        while (thread1.isAlive() || thread2.isAlive()) {
            try {
                if (counter < 10) {
                    counter++;
                } else {
                    runnable1.setRunning(false);
                    runnable2.setRunning(false);
                }

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

                System.exit(1);
            }
        }
    }
}
