package com.home.module;

import javax.sound.sampled.*;

public abstract class Device<T extends DataLine> extends Node {
    protected T line;
    private AudioFormat format;
    private DataLine.Info info;

    private final AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
    private final float sampleRate = 44100;
    private final int sampleSizeInBits = 16;
    private final int channels = 2;
    private final int frameSize = 4;
    private final float frameRate = 44100;
    private final boolean bigEndian = false;
    private final int bufferSize = 2500;

    public Device(Class<?> lineClass) throws LineUnavailableException {
        this.format = new AudioFormat(encoding, sampleRate, sampleSizeInBits,
                channels, frameSize, frameRate, bigEndian);

        this.info = new DataLine.Info(lineClass, this.format, this.bufferSize);
        this.line = (T) AudioSystem.getLine(info);
        this.line.open();
    }
}
