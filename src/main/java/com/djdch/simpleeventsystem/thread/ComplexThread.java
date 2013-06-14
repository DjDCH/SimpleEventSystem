package com.djdch.simpleeventsystem.thread;

import java.util.ArrayList;

import com.djdch.simpleeventsystem.event.ConsoleOutputEvent;
import com.djdch.simpleeventsystem.event.EventDispatcher;
import com.djdch.simpleeventsystem.event.ExceptionEvent;
import com.djdch.simpleeventsystem.event.LocalConsoleOutputEvent;
import com.djdch.simpleeventsystem.event.SmartConsoleOutputEvent;
import com.djdch.simpleeventsystem.listener.ConsoleOutputListener;
import com.djdch.simpleeventsystem.listener.ExceptionListener;
import com.djdch.simpleeventsystem.listener.LocalConsoleOutputListener;
import com.djdch.simpleeventsystem.listener.SmartConsoleOutputListener;

public class ComplexThread extends Thread implements ExceptionListener, ConsoleOutputListener, LocalConsoleOutputListener, SmartConsoleOutputListener {
    ComplexSubThread runnable1;
    ComplexSubThread runnable2;
    java.lang.Thread thread1;
    java.lang.Thread thread2;
    ArrayList<Runnable> runnables;

    public ComplexThread(String name) {
        super(name);

        runnable1 = new ComplexSubThread(getName() + "-Child1");
        runnable2 = new ComplexSubThread(getName() + "-Child2");

        thread1 = new java.lang.Thread(runnable1);
        thread2 = new java.lang.Thread(runnable2);

        runnables = new ArrayList<Runnable>();
        runnables.add(runnable1);
        runnables.add(runnable2);
    }

    @Override
    public void run() {
        display("Hi");

        EventDispatcher.registerListener(this);
        display("Registered self as ExceptionListener");
        display("Registered self as ConsoleOutputListener");
        display("Registered self as LocalConsoleOutputListener");

        thread1.start();
        thread2.start();

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
    public void setRunning(boolean running) {
        super.setRunning(running);

        runnable1.setRunning(running);
        runnable2.setRunning(running);
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
        if (runnables.contains(event.getSource())) {
            display("SmartConsoleOutputEvent received with ouput : " + event.getOutput());
        }
    }
}
