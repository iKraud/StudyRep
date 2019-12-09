package hw10Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client2 {
    public static String clientAddress = "127.0.0.2";  //"230.0.0.0" //"127.0.0.1"
    public static void main (String[] args) throws IOException {
        MulticastSocket socket;
        String incomingMessage;
        String outcomingMessage;
        String myName;
        InetAddress group = InetAddress.getByName("230.0.0.0"); //255.255.255.255

        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

        System.out.print("Для подключения укажите ваше имя: ");
        myName = "[" + br.readLine() + "]";
        byte[] b = myName.getBytes();

        socket = new MulticastSocket(Server.PORT);
        socket.joinGroup(group);

        DatagramPacket dp = new DatagramPacket(b, b.length, InetAddress.getByName(clientAddress), Server.PORT);
        socket.send(dp);

        byte[] buffer = new byte[65536];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        socket.receive(reply);
        byte[] data = reply.getData();
        incomingMessage = new String(data, 0, reply.getLength());
        System.out.println(incomingMessage);

        try {
            while (true) {
                outcomingMessage = br.readLine();
                b = outcomingMessage.getBytes();

                dp = new DatagramPacket(b, b.length, InetAddress.getByName(clientAddress), Server.PORT);
                socket.send(dp);

                buffer = new byte[65536];
                reply = new DatagramPacket(buffer, buffer.length);

                socket.receive(reply);
                data = reply.getData();
                incomingMessage = new String(data, 0, reply.getLength());
                System.out.println(incomingMessage);

                if (outcomingMessage.equals("quit")) {
                    socket.close();
                    socket.leaveGroup(group);
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("IOException " + e);
        }
    }
}