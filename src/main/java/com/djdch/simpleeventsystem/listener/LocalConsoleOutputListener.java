package com.djdch.simpleeventsystem.listener;

import com.djdch.simpleeventsystem.event.LocalConsoleOutputEvent;

public interface LocalConsoleOutputListener extends Listener {
    public void onLocalConsoleOutput(LocalConsoleOutputEvent event);
}
