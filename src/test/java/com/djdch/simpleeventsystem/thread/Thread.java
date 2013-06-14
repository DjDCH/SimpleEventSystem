package com.djdch.simpleeventsystem.thread;

public abstract class Thread implements Runnable {
    private String name;
    private boolean running;

    public Thread(String name) {
        this.name = name;
        this.running = true;
    }

    public void display(String msg) {
        System.out.println(String.format("[%s] %s", name, msg));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
