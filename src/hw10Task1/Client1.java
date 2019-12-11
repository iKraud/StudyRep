package hw10Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client1 {
    public static void main (String[] args) throws IOException {
        String clientAddress = "192.168.0.11";
        String inMessage;
        String outMessage;
        String recipientName;
        InetAddress group = InetAddress.getByName("230.0.0.0");

        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

        //[senderAddress@recipientName]message - образец послания
        System.out.print("Для подключения укажите ваше имя: ");
        outMessage = "[" + clientAddress + "@Server" + "]" + br.readLine();

        MulticastSocket socket = new MulticastSocket(Server.MULTI_PORT);
        socket.joinGroup(group);

        DatagramPacket dp = new DatagramPacket(outMessage.getBytes(), outMessage.getBytes().length, InetAddress.getByName("localhost"), Server.MULTI_PORT);
        socket.send(dp);

        byte[] buffer = new byte[65536];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        socket.receive(reply);
        byte[] data = reply.getData();
        inMessage = new String(data, 0, reply.getLength());
        System.out.println(inMessage);

        MultiIncomeThread inThread = new MultiIncomeThread();
        inThread.start();
        try {
            while (true) {
                outMessage = br.readLine();
                if (outMessage.startsWith("@")) {
                    recipientName = outMessage.substring(1, outMessage.indexOf(" "));
                    outMessage = outMessage.substring(outMessage.indexOf(" ") + 1);
                } else {
                    recipientName = "Server";
                }
                outMessage = "[" + clientAddress + "@" + recipientName + "]" + outMessage;
                dp = new DatagramPacket(outMessage.getBytes(), outMessage.getBytes().length, InetAddress.getByName("localhost"), Server.MULTI_PORT);
                socket.send(dp);

                if (outMessage.substring(outMessage.indexOf("]") + 1).equals("quit")) {
                    inThread.interrupt();
                    socket.leaveGroup(group);
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("IOException " + e);
        }
    }
}