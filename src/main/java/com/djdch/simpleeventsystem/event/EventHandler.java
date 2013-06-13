package com.djdch.simpleeventsystem.event;

import com.djdch.simpleeventsystem.listener.ConsoleOutputListener;
import com.djdch.simpleeventsystem.listener.ExceptionListener;
import com.djdch.simpleeventsystem.listener.Listener;

public class EventHandler {
    public static synchronized void dispatch(Event event) {
        HandlerList handlers = event.getHandlers();
        Listener[] listeners = handlers.getListeners();

        for (Listener listener : listeners) {
            try {
                callEvent(listener, event);
            } catch (Throwable ex) {
                // TODO: Handle this
            }
        }
    }

    private static void callEvent(Listener listener, Event event) {
        switch (event.getType()) {
            case EXCEPTION:
                ((ExceptionListener) listener).onException((ExceptionEvent) event);
                break;

            case CONSOLE_OUTPUT:
                ((ConsoleOutputListener) listener).onConsoleOutput((ConsoleOutputEvent) event);
                break;
        }
    }
}
