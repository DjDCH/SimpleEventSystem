package com.djdch.simpleeventsystem.event;

import java.util.Date;

public class SmartConsoleOutputEvent extends ConsoleOutputEvent implements Sourceable {
    private static final HandlerList handlers = new SimpleHandlerList();
    private Object source;

    public SmartConsoleOutputEvent(Object source, String output) {
        this(source, new Date(), output);
    }

    public SmartConsoleOutputEvent(Object source, Date date, String output) {
        super(Event.Type.SMART_CONSOLE_OUTPUT, date, output);

        this.source = source;
    }

    @Override
    public Object getSource() {
        return source;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
