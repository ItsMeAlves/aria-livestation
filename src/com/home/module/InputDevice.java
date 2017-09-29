package com.home.module;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class InputDevice extends Device<TargetDataLine> {

    public InputDevice() throws LineUnavailableException {
        super(TargetDataLine.class);
    }

    public void start() {
        this.line.start();
        service = new Thread(() -> {
            int bufferSize = this.line.getBufferSize();
            byte[] data = new byte[bufferSize];

            while(!Thread.currentThread().isInterrupted()) {
                this.line.read(data, 0, bufferSize);
                this.send(data);
            }
        });

        this.service.start();
    }

    public void stop() {
        this.line.stop();
        service.interrupt();
        service = null;
    }

    @Override
    public void receive(Node source, byte[] data) {

    }
}
