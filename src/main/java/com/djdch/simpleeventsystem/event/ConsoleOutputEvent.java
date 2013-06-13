package com.djdch.simpleeventsystem.event;

import java.util.Date;

public class ConsoleOutputEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Date date;
    private String output;

    public ConsoleOutputEvent(Date date, String output) {
        super(Event.Type.CONSOLE_OUTPUT);

        this.date = date;
        this.output = output;
    }

    public Date getDate() {
        return date;
    }

    public String getOutput() {
        return output;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
