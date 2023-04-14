package com.github.bartimaeusnek.MyMilightLib;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class NetworkingThread implements Runnable {
    private static final Semaphore lock = new Semaphore(1);
    private static final ExecutorService pool = Executors.newSingleThreadExecutor();
    private final String ip;
    private final int port;
    private final byte[] payload;

    private NetworkingThread(String ip, int port, byte[] payload) {
        this.ip = ip;
        this.port = port;
        this.payload = payload;
    }

    public static void sendPayloadTo(String ip, int port, NetworkPayloadBuilder builder) {
        new NetworkingThread(ip, port, builder.build()).submit();
    }

    private void submit() {
        pool.submit(this);
    }

    @Override
    public void run() {
        try {
            lock.acquire(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            socket.connect(new InetSocketAddress(ip, port));
            socket.send(new DatagramPacket(payload, payload.length));
            socket.disconnect();
        } catch (IOException ignored) {
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                socket = null;
            }
            lock.release();
        }
    }
}
