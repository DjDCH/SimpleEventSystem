package com.djdch.simpleeventsystem.event;

import java.util.ArrayList;

import com.djdch.simpleeventsystem.listener.Listener;

public class LocalHandlerList implements HandlerList {
    private final ThreadLocal<Listener[]> cachedListener;
    private final ThreadLocal<ArrayList<Listener>> listeners;

    public LocalHandlerList() {
        cachedListener = new ThreadLocal<Listener[]>();
        listeners = new ThreadLocal<ArrayList<Listener>>();
    }

    @Override
    public synchronized void register(Listener listener) {
        ArrayList<Listener> listenersCopy = listeners.get();

        if (listenersCopy == null) {
            listenersCopy = new ArrayList<Listener>();
        }

        if (listenersCopy.contains(listener)) {
            throw new IllegalStateException("This listener is already registered");
        }

        listenersCopy.add(listener);
        listeners.set(listenersCopy);

        cachedListener.remove();
    }

    @Override
    public synchronized void unregister(Listener listener) {
        ArrayList<Listener> listenersCopy = listeners.get();

        if (listenersCopy == null) {
            listenersCopy = new ArrayList<Listener>();
        }

        if (listenersCopy.remove(listener)) {
            listeners.set(listenersCopy);

            cachedListener.remove();
        }
    }

    private synchronized void bake() {
        Listener[] cachedListenerCopy = cachedListener.get();

        if (cachedListenerCopy != null) {
            return; // Don't re-bake when still valid
        }

        ArrayList<Listener> listenersCopy = listeners.get();

        if (listenersCopy == null) {
            listenersCopy = new ArrayList<Listener>();
        }

        cachedListenerCopy = listenersCopy.toArray(new Listener[listenersCopy.size()]);

        cachedListener.set(cachedListenerCopy);
    }

    @Override
    public Listener[] getListeners() {
        Listener[] listeners;

        while ((listeners = this.cachedListener.get()) == null) {
            bake(); // This prevents fringe cases of returning null
        }

        return listeners;
    }
}
