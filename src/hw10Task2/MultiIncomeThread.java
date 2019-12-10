package hw10Task1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

class MultiIncomeThread extends Thread {
    private byte[] buffer = new byte[65536];

    public void run() {
        MulticastSocket socket = null;
        InetAddress group = null;
        while (!MultiIncomeThread.interrupted()) {
            try {
                socket = new MulticastSocket(Server.MULTI_PORT);
                group = InetAddress.getByName("230.0.0.0");
                socket.joinGroup(group);
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                socket.receive(reply);
                byte[] data = reply.getData();
                String incomingMessage = new String(data, 0, reply.getLength());
                System.out.println(incomingMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            socket.leaveGroup(group);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
