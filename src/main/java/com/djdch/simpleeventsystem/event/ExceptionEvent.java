package com.djdch.simpleeventsystem.event;

import com.djdch.simpleeventsystem.handlerlist.HandlerList;
import com.djdch.simpleeventsystem.handlerlist.SimpleHandlerList;

public class ExceptionEvent extends Event {
    private static final HandlerList handlers = new SimpleHandlerList();
    private Exception exception;

    public ExceptionEvent(Exception exception) {
        super(Event.Type.EXCEPTION);

        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
