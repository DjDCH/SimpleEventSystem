package com.djdch.simpleeventsystem;

import com.djdch.simpleeventsystem.event.EventHandler;
import com.djdch.simpleeventsystem.event.ExceptionEvent;
import com.djdch.simpleeventsystem.thread.SimpleThread;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SimpleThread runnable1 = new SimpleThread("Bob");
        SimpleThread runnable2 = new SimpleThread("Joe");

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        ExceptionEvent event = new ExceptionEvent(new Exception("Should not be received"));
        EventHandler.dispatch(event);

        thread1.start();
        thread2.start();

        int counter = 0;

        while (thread1.isAlive() || thread2.isAlive()) {
            try {
                ExceptionEvent event2 = new ExceptionEvent(new Exception("Empty"));
                EventHandler.dispatch(event2);

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
