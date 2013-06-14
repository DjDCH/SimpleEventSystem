package com.djdch.simpleeventsystem.thread;

import com.djdch.simpleeventsystem.dispatcher.EventDispatcher;
import com.djdch.simpleeventsystem.event.ConsoleOutputEvent;
import com.djdch.simpleeventsystem.event.ExceptionEvent;
import com.djdch.simpleeventsystem.event.LocalConsoleOutputEvent;
import com.djdch.simpleeventsystem.event.SmartConsoleOutputEvent;
import com.djdch.simpleeventsystem.listener.ConsoleOutputListener;
import com.djdch.simpleeventsystem.listener.ExceptionListener;
import com.djdch.simpleeventsystem.listener.LocalConsoleOutputListener;
import com.djdch.simpleeventsystem.listener.SmartConsoleOutputListener;

public class SimpleThread extends Thread implements ExceptionListener, ConsoleOutputListener, LocalConsoleOutputListener, SmartConsoleOutputListener {

    public SimpleThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        display("Hi");

        EventDispatcher.registerListener(this);
        display("Registered self as ExceptionListener");
        display("Registered self as ConsoleOutputListener");
        display("Registered self as LocalConsoleOutputListener");

        while (isRunning()) {
            try {
//                ExceptionEvent event = new ExceptionEvent(new Exception("Empty"));
//                EventHandler.dispatch(event);

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

    @Override
    public void onSmartConsoleOutput(SmartConsoleOutputEvent event) {
        display("SmartConsoleOutputEvent received with ouput : " + event.getOutput());
    }
}
