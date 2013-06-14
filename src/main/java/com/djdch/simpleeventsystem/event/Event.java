package com.djdch.simpleeventsystem.event;

import com.djdch.simpleeventsystem.util.Validate;

public abstract class Event {
    private final Type type;

    protected Event(final Type type) {
        Validate.notNull(type, "Type cannot be null");

        this.type = type;
    }

    public abstract HandlerList getHandlers();

    public final Type getType() {
        return type;
    }

    public enum Type {
        EXCEPTION,
        CONSOLE_OUTPUT,
        LOCAL_CONSOLE_OUTPUT;
    }
}
