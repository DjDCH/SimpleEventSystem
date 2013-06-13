package com.djdch.simpleeventsystem.listener;

import com.djdch.simpleeventsystem.event.ConsoleOutputEvent;

public interface ConsoleOutputListener extends Listener {
    public void onConsoleOutput(ConsoleOutputEvent event);
}
