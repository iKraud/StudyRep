package hw10Task2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author "Timohin Igor"
 * Задание 1. Разработать приложение - многопользовательский чат, в котором участвует произвольное количество клиентов.
 * Каждый клиент после запуска отправляет свое имя серверу. После чего начинает отправлять ему сообщения.
 * Каждое сообщение сервер подписывает именем клиента и рассылает всем клиентам (broadcast).
 *
 * Задание 2.  Усовершенствовать задание 1:
 * a. добавить возможность отправки личных сообщений (unicast).
 * b. добавить возможность выхода из чата с помощью написанной в чате команды «quit»
 */
public class Server {
    public static final Integer PORT = 7000;
    public static void main (String[] args) {
        Map<String,String> users = new HashMap<>();
        try {
            MulticastSocket socket = new MulticastSocket(PORT);
            InetAddress group = InetAddress.getByName("230.0.0.0"); //255.255.255.255
            socket.setBroadcast(true);

            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
            System.out.println("Сервер готов. Ожидается подключение участников...");

            while (true) {
                socket.receive(incoming);
                String s = new String(incoming.getData(), 0, incoming.getLength());
                if (s.startsWith("[")) {
                    String newComer = s.substring(s.lastIndexOf("[") + 1, s.lastIndexOf("]"));
                    s = "Приветствуем нового участника: " + newComer;
                    System.out.println(s);
                    users.put(String.valueOf(incoming.getAddress()), newComer);
                    DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, group, PORT); //InetAddress.getByName("255.255.255.255")
                    socket.send(dp);
                } else if (!s.toLowerCase().equals("quit")) {
//                    s = users.get(String.valueOf(incoming.getPort())) + " написал: " + s;
                    s = users.get(String.valueOf(incoming.getAddress())) + " написал: " + s;
                    System.out.println(s);
                    DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, group, PORT); //incoming.getPort()
                    socket.send(dp);
                } else {
                    s = "Вы вышли из чата. До новых встреч!";
                    DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, incoming.getAddress(), incoming.getPort());
                    socket.send(dp);
                    System.out.println(users.get(String.valueOf(incoming.getAddress())) + " покинул чат");
                    users.remove(String.valueOf(incoming.getAddress()));
                }
            }
        }
        catch (IOException e) {
            System.err.println("IOException " + e);
        }
    }
}