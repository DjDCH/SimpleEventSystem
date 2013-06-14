package com.djdch.simpleeventsystem.event;

import com.djdch.simpleeventsystem.listener.Listener;

public interface HandlerList {

    public abstract void register(Listener listener);

    public abstract void unregister(Listener listener);

    public abstract Listener[] getListeners();

}
