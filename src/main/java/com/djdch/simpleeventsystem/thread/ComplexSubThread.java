package com.djdch.simpleeventsystem.thread;

import com.djdch.simpleeventsystem.event.ConsoleOutputEvent;
import com.djdch.simpleeventsystem.event.EventDispatcher;
import com.djdch.simpleeventsystem.event.ExceptionEvent;
import com.djdch.simpleeventsystem.event.LocalConsoleOutputEvent;
import com.djdch.simpleeventsystem.event.SmartConsoleOutputEvent;
import com.djdch.simpleeventsystem.listener.ConsoleOutputListener;
import com.djdch.simpleeventsystem.listener.ExceptionListener;
import com.djdch.simpleeventsystem.listener.LocalConsoleOutputListener;

public class ComplexSubThread extends Thread implements ExceptionListener, ConsoleOutputListener, LocalConsoleOutputListener {

    public ComplexSubThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        display("Hi");

        EventDispatcher.registerListener(this);
        display("Registered self as ExceptionListener");
        display("Registered self as ConsoleOutputListener");
        display("Registered self as LocalConsoleOutputListener");

        boolean odd = true;

        while (isRunning()) {
            try {
                if (odd) {
//                    ExceptionEvent event1 = new ExceptionEvent(new Exception("Error from " + getName()));
//                    EventDispatcher.dispatch(event1);
//                    ConsoleOutputEvent event1 = new ConsoleOutputEvent("Shell stuff from " + getName());
//                    EventDispatcher.dispatch(event1);
                } else { // even
//                    LocalConsoleOutputEvent event2 = new LocalConsoleOutputEvent("Shell thing from " + getName());
//                    EventDispatcher.dispatch(event2);
                    SmartConsoleOutputEvent event3 = new SmartConsoleOutputEvent(this, "Shell thing from " + getName());
                    EventDispatcher.dispatch(event3);
                }

                odd = !odd;

                java.lang.Thread.sleep(2000);
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

    @Override
    public void onConsoleOutput(ConsoleOutputEvent event) {
        display("ConsoleOutputEvent received with ouput : " + event.getOutput());
    }

    @Override
    public void onLocalConsoleOutput(LocalConsoleOutputEvent event) {
        display("LocalConsoleOutputEvent received with ouput : " + event.getOutput());
    }
}
