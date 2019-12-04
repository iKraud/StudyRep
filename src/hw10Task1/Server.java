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
    public static final Integer SERVER_DG_PORT = 7000;
    public static final Integer SERVER_MC_PORT = 7001;
    public static void main(String args[]) {
        Map<String,String> users = new HashMap<>();
        try {
            DatagramSocket datagramSocket = new DatagramSocket(SERVER_DG_PORT);
            MulticastSocket multicastSocket = new MulticastSocket(SERVER_MC_PORT);

            datagramSocket.setBroadcast(true);

            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
            System.out.println("Сервер готов. Ожидается подключение участников...");
            while(true) {
                datagramSocket.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());

                if (s.startsWith("[")) {
                    String newComer = s.substring(s.lastIndexOf("[") + 1, s.lastIndexOf("]"));
                    System.out.println("Приветствуем нового участника: " + newComer);
                    users.put(String.valueOf(incoming.getPort()), newComer);

                    s = "Добро пожаловать в чат, " + newComer + "!";
//                    DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, incoming.getAddress(), incoming.getPort());
                    DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, incoming.getAddress(), incoming.getPort()); //group
                    datagramSocket.send(dp);
                } else if (!s.toLowerCase().equals("quit")) {
                    s = users.get(String.valueOf(incoming.getPort()))  + " написал: " + s;
                    System.out.println(s);

                    DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, incoming.getAddress(), incoming.getPort()); //incoming.getPort()
                    multicastSocket.send(dp);
                } else {
                    s = "Вы вышли из чата. До новых встреч!";
                    DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, incoming.getAddress(), incoming.getPort());
                    datagramSocket.send(dp);
                    System.out.println(users.get(String.valueOf(incoming.getPort())) + " покинул чат");
                    users.remove(String.valueOf(incoming.getPort()));
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
