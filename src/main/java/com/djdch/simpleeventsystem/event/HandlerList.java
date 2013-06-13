package com.djdch.simpleeventsystem.event;

import java.util.ArrayList;

import com.djdch.simpleeventsystem.listener.Listener;

// Inspired by https://github.com/Bukkit/Bukkit/blob/master/src/main/java/org/bukkit/event/HandlerList.java
public class HandlerList {
    private volatile Listener[] cachedListener = null;
    private final ArrayList<Listener> listeners;

    public HandlerList() {
        listeners = new ArrayList<Listener>();
    }

    public synchronized void register(Listener listener) {
        if (listeners.contains(listener)) {
            throw new IllegalStateException("This listener is already registered");
        }

        listeners.add(listener);
        cachedListener = null;
    }

    public synchronized void unregister(Listener listener) {
        if (listeners.remove(listener)) {
            cachedListener = null;
        }
    }

    private synchronized void bake() {
        if (cachedListener != null) {
            return; // Don't re-bake when still valid
        }

        cachedListener = listeners.toArray(new Listener[listeners.size()]);
    }

    public Listener[] getListeners() {
        Listener[] listeners;

        while ((listeners = this.cachedListener) == null) {
            bake(); // This prevents fringe cases of returning null
        }

        return listeners;
    }
}
