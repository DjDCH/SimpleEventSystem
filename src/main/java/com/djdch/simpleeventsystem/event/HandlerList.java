package com.djdch.simpleeventsystem.event;

import java.util.ArrayList;

import com.djdch.simpleeventsystem.listener.Listener;

public class HandlerList {
    private final ArrayList<Listener> listeners;

    public HandlerList() {
        listeners = new ArrayList<Listener>();
    }

    public Listener[] getListeners() {
        return listeners.toArray(new Listener[listeners.size()]);
    }
}
