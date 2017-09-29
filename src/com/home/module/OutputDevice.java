package com.home.module;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class OutputDevice extends Device<SourceDataLine> {
    private byte[] data;

    public OutputDevice() throws LineUnavailableException {
        super(SourceDataLine.class);
    }

    public void start() {
        this.line.start();
        this.service = new Thread(() -> {

            while(!Thread.currentThread().isInterrupted()) {
                if(data == null) continue;
                this.line.write(data, 0, data.length);
                this.send(data);
                data = null;
            }
        });

        this.service.start();
    }

    public void stop() {
        this.line.stop();
        this.service.interrupt();
        this.service = null;
    }

    @Override
    public void receive(Node source, byte[] data) {
        if(this.data == null) this.data = data;
    }
}
