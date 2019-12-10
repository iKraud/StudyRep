//package hw10Task1;
//
//import java.io.IOException;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//
//class UniIncomeThread extends Thread {
//    private byte[] buffer = new byte[65536];
//
//    public void run() {
//        DatagramSocket socket = null;
//        while (!UniIncomeThread.interrupted()) {
//            try {
//                socket = new DatagramSocket();
//                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
//
//                socket.receive(reply);
//                byte[] data = reply.getData();
//                String incomingMessage = new String(data, 0, reply.getLength());
//                System.out.println(incomingMessage);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        socket.close();
//    }
//}
