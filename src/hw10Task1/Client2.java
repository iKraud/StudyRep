package hw10Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client2 {
    public static final Integer CLIENT_PORT = 7002;
    public static String clientAddress = "127.0.0.1";  //"230.0.0.0"
    public static void main(String args[]) throws IOException {
        DatagramSocket datagramSocket;
//        MulticastSocket multicastSocket;
//        InetAddress group = InetAddress.getByName("127.0.0.1");

        String incomingMessage;
        String outcomingMessage;
        String myName;
        String myAddress = clientAddress;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Для подключения укажите ваше имя: ");
        myName = "[" + br.readLine() + "]";
        byte[] b = myName.getBytes();

        datagramSocket = new DatagramSocket(CLIENT_PORT);
//        multicastSocket.joinGroup(group);
        DatagramPacket dp = new DatagramPacket(b, b.length, InetAddress.getByName(myAddress), Server.SERVER_DG_PORT);
        datagramSocket.send(dp);

        byte[] buffer = new byte[65536];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        datagramSocket.receive(reply);
        byte[] data = reply.getData();
        incomingMessage = new String(data, 0, reply.getLength());
        System.out.println(incomingMessage);

        try {
//            datagramSocket = new DatagramSocket(CLIENT_PORT);
            while (true) {
                outcomingMessage = br.readLine();
                b = outcomingMessage.getBytes();

                dp = new DatagramPacket(b, b.length, InetAddress.getByName(myAddress), Server.SERVER_DG_PORT);
                datagramSocket.send(dp);

                buffer = new byte[65536];
                reply = new DatagramPacket(buffer, buffer.length);

                datagramSocket.receive(reply);
                data = reply.getData();
                incomingMessage = new String(data, 0, reply.getLength());
                System.out.println(incomingMessage);

                if (outcomingMessage.equals("quit")) {
//                    multicastSocket.leaveGroup(group);
//                    multicastSocket.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("IOException " + e);
        }
    }
}