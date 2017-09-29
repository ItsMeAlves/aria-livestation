package com.home.module;

import java.util.Vector;

public abstract class Node {
    protected Thread service;
    private Vector<Node> senders;
    private Vector<Node> receivers;

    public Node() {
        this.senders = new Vector<>();
        this.receivers = new Vector<>();
    }

    public final void addSender(Node sender) {
        this.senders.add(sender);
    }
    public final void addReceiver(Node receiver) {
        this.receivers.add(receiver);
    }

    public final void pipeTo(Node receiver) {
        this.addReceiver(receiver);
        receiver.addSender(this);
    }

    protected final void send(byte[] data) {
        for(Node receiver : this.receivers)
            receiver.receive(this, data);
    }

    public abstract void receive(Node source, byte[] data);
}
