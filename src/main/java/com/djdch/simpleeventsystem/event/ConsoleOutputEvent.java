package com.djdch.simpleeventsystem.event;

import java.util.Date;

import com.djdch.simpleeventsystem.handlerlist.HandlerList;
import com.djdch.simpleeventsystem.handlerlist.SimpleHandlerList;

public class ConsoleOutputEvent extends Event {
    private static final HandlerList handlers = new SimpleHandlerList();
    private Date date;
    private String output;

    public ConsoleOutputEvent(String output) {
        this(new Date(), output);
    }

    public ConsoleOutputEvent(Date date, String output) {
        super(Event.Type.CONSOLE_OUTPUT);

        this.date = date;
        this.output = output;
    }

    protected ConsoleOutputEvent(Event.Type type, Date date, String output) {
        super(type);

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
