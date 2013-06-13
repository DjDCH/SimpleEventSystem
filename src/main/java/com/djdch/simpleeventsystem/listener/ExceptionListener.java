package com.djdch.simpleeventsystem.listener;

import com.djdch.simpleeventsystem.event.ExceptionEvent;

public interface ExceptionListener extends Listener {
    public void onException(ExceptionEvent event);
}
