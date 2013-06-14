package com.djdch.simpleeventsystem.listener;

import com.djdch.simpleeventsystem.event.SmartConsoleOutputEvent;

public interface SmartConsoleOutputListener extends Listener {
    public void onSmartConsoleOutput(SmartConsoleOutputEvent event);
}
