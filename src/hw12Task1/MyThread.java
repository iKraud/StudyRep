package hw12Task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class MyThread extends Thread {
    public void run() {
        List<MyClass> arr = new ArrayList<>();
        int total = 10;
        Random rnd = new Random();
        while (true) {
            for (int i = 0; i < total; i++) {
                total++;
                MyClass cls = null;
                try {
                    cls = (MyClass) ClassLoader.getSystemClassLoader().loadClass("hw12Task1.MyClass").newInstance();
                } catch (InstantiationException | ClassNotFoundException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                arr.add(cls);

                if ((arr.size() % (rnd.nextInt(total) + 1)) == 0) {
                    arr.remove(i);
                    i--;
                }
            }
        }
    }
}
