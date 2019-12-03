package hw10Task1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Map;

/**
 * Задание 1. Разработать приложение - многопользовательский чат, в котором участвует произвольное количество клиентов.
 * Каждый клиент после запуска отправляет свое имя серверу. После чего начинает отправлять ему сообщения.
 * Каждое сообщение сервер подписывает именем клиента и рассылает всем клиентам (broadcast).
 *
 * Задание 2.  Усовершенствовать задание 1:
 * a. добавить возможность отправки личных сообщений (unicast).
 * b. добавить возможность выхода из чата с помощью написанной в чате команды «quit»
 */
public class Server {
    public static final Integer SERVER_PORT = 7000;
    public static void main(String args[]) {
        Map<String,String> users = new HashMap<>();
        try {
            DatagramSocket datagramSocket = new DatagramSocket(SERVER_PORT + 1);
            MulticastSocket multicastSocket = new MulticastSocket(SERVER_PORT);
            multicastSocket.setBroadcast(true);
//            InetAddress group = InetAddress.getByName("127.0.0.10");
//            multicastSocket.joinGroup(group);

            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
            System.out.println("Сервер готов. Ожидается подключение участников...");
            while(true) {
                multicastSocket.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());

                if (s.startsWith("[")) {
                    String newComer = s.substring(s.lastIndexOf("[") + 1, s.lastIndexOf("]"));
                    System.out.println("Приветствуем нового участника: " + newComer);
                    users.put(String.valueOf(incoming.getAddress()), newComer);

                    s = "Добро пожаловать в чат, " + newComer + "!";
//                    DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, incoming.getAddress(), incoming.getPort());
                    DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, incoming.getAddress(), incoming.getPort()); //group
                    datagramSocket.send(dp);
                } else if (!s.toLowerCase().equals("quit")) {
                    s = users.get(String.valueOf(incoming.getAddress()))  + " написал: " + s;
                    System.out.println(s);

                    DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, incoming.getAddress(), incoming.getPort());
                    multicastSocket.send(dp);
                } else {
                    s = "Вы вышли из чата. До новых встреч!";
                    DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, incoming.getAddress(), incoming.getPort());
                    multicastSocket.send(dp);
                    System.out.println(users.get(String.valueOf(incoming.getAddress())) + " покинул чат");
                    users.remove(String.valueOf(incoming.getAddress()));
                }
            }
//            multicastSocket.leaveGroup(group);
//            multicastSocket.close();
        }
        catch(IOException e) {
            System.err.println("IOException " + e);
        }

    }
}

package hw10Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client1 {
    public static String clientAddress = "127.0.0.1";  //"230.0.0.0"
    public static void main(String args[]) throws IOException {
        MulticastSocket multicastSocket;
        DatagramSocket datagramSocket;
        InetAddress group = InetAddress.getByName("127.0.0.1");

        String incomingMessage;
        String outcomingMessage;
        String myName;
        String myAddress = clientAddress;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Для подключения укажите ваше имя: ");
        myName = "[" + br.readLine() + "]";
        byte[] b = myName.getBytes();

        multicastSocket = new MulticastSocket(Server.SERVER_PORT);
//        datagramSocket = new DatagramSocket(Server.SERVER_PORT);
//        multicastSocket.joinGroup(group);
        DatagramPacket dp = new DatagramPacket(b, b.length, InetAddress.getByName(myAddress), Server.SERVER_PORT);
        multicastSocket.send(dp);

        byte[] buffer = new byte[65536];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        multicastSocket.receive(reply);
        byte[] data = reply.getData();
        incomingMessage = new String(data, 0, reply.getLength());
        System.out.println(incomingMessage);
//        System.out.println("Добро пожаловать, " + myName + "!");

        try {
            multicastSocket = new MulticastSocket(Server.SERVER_PORT);
            while (true) {
                outcomingMessage = br.readLine();
                b = outcomingMessage.getBytes();

                dp = new DatagramPacket(b, b.length, InetAddress.getByName(myAddress), Server.SERVER_PORT);
                multicastSocket.send(dp);

                buffer = new byte[65536];
                reply = new DatagramPacket(buffer, buffer.length);

                multicastSocket.receive(reply);
                data = reply.getData();
                incomingMessage = new String(data, 0, reply.getLength());
                System.out.println(incomingMessage);

                if (outcomingMessage.equals("quit")) {
                    multicastSocket.leaveGroup(group);
                    multicastSocket.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("IOException " + e);
        }
    }
}
