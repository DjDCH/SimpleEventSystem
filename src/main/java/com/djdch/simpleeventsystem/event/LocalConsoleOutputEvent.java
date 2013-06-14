package com.djdch.simpleeventsystem.event;

import java.util.Date;

import com.djdch.simpleeventsystem.handlerlist.HandlerList;
import com.djdch.simpleeventsystem.handlerlist.LocalHandlerList;

public class LocalConsoleOutputEvent extends ConsoleOutputEvent {
    private static final HandlerList handlers = new LocalHandlerList();

    public LocalConsoleOutputEvent(String output) {
        this(new Date(), output);
    }

    public LocalConsoleOutputEvent(Date date, String output) {
        super(Event.Type.LOCAL_CONSOLE_OUTPUT, date, output);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
