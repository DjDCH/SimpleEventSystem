package com.djdch.simpleeventsystem.thread;

import com.djdch.simpleeventsystem.event.EventHandler;
import com.djdch.simpleeventsystem.event.ExceptionEvent;
import com.djdch.simpleeventsystem.listener.ExceptionListener;

public class SimpleThread extends Thread implements ExceptionListener {

    public SimpleThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        display("Hi");

        EventHandler.registerListener(this);
        display("Registered self as ExceptionListener");

        while (isRunning()) {
            try {
//                ExceptionEvent event = new ExceptionEvent(new Exception("Empty"));
//                EventHandler.dispatch(event);

                java.lang.Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();

                setRunning(false);
            }
        }
    }

    @Override
    public void onException(ExceptionEvent event) {
        display("ExceptionEvent received with Exception message : " + event.getException().getMessage());
    }
}
