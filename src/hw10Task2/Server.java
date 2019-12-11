package hw10Task1;

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
    static final Integer MULTI_PORT = 7000;
    public static void main (String[] args) {
        Map<String,String> users = new HashMap<>();
        byte[] buffer = new byte[65536];
        DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
        try {
            MulticastSocket multiSocket = new MulticastSocket(MULTI_PORT);
            System.out.println("Добро пожаловать!");
            System.out.println("Для персональных сообщений - '@ИмяАдресата Текст'");

            //[senderAddress@recipientName]message - образец послания
            while (true) {
                multiSocket.receive(incoming);
                String inMessage = new String(incoming.getData(), 0, incoming.getLength());
                String senderAddress = inMessage.substring(inMessage.indexOf("[") + 1, inMessage.indexOf("@"));
                String recipientName = inMessage.substring(inMessage.indexOf("@") + 1, inMessage.indexOf("]"));
                String message = inMessage.substring(inMessage.indexOf("]") + 1);

                if (!(users.containsKey(senderAddress))) { //если пользователя с таким адресом ещё нет - добавляем
                    users.put(senderAddress, message);
                    message = "Приветствуем нового участника: " + message;
                    sendMessage(message);
                } else if (!(message.toLowerCase().equals("quit"))) { //если сообщение не quit - отправляем ->
                    if (recipientName.equals("Server")) { //-> сообщение на всех
                        message = users.getOrDefault(senderAddress, "") + " написал: " + message;
                        sendMessage(message);
                    } else { //-> сообщение на конкретного пользователя
                        message = users.getOrDefault(senderAddress, "") + " написал Вам: " + message;
                        for (Map.Entry<String,String> el : users.entrySet()) { //ищем по имени адрес получателя
                            if (el.getValue().equals(recipientName)) {
                                InetAddress recipientAddress = InetAddress.getByName(el.getKey());
                                sendMessage(message, recipientAddress);
                                break;
                            }
                        }
                    }
                } else { //если сообщение равно quit - уведомляем и удаляем
                    message = users.getOrDefault(senderAddress,"ПользовательНеНайден") + " покинул чат";
                    sendMessage(message);
                    users.remove(senderAddress);
                }
            }
        }
        catch (IOException e) {
            System.err.println("IOException " + e);
        }
    }
    private static void sendMessage(String mes) throws IOException { // для массовой рассылки
        MulticastSocket multiSocket = new MulticastSocket(MULTI_PORT);
        InetAddress group = InetAddress.getByName("230.0.0.0");
        System.out.println(mes);
        DatagramPacket dp = new DatagramPacket(mes.getBytes(), mes.getBytes().length, group, MULTI_PORT);
        multiSocket.send(dp);
        multiSocket.close();
    }
    private static void sendMessage(String mes, InetAddress inetAddress) throws IOException { //для точечной рассылки
        MulticastSocket multiSocket = new MulticastSocket(MULTI_PORT);
        DatagramPacket dp = new DatagramPacket(mes.getBytes(), mes.getBytes().length, inetAddress, MULTI_PORT);
        multiSocket.send(dp);
        multiSocket.close();
    }
}
