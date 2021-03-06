package hw09Task1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("hw09Task1.SomeClass".equals(name)) {
            return findClass(name);
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if ("hw09Task1.SomeClass".equals(name)) {
            try {
                byte[] bytes = Files.readAllBytes(Paths.get("C:\\Java\\IdeaProjects\\untitled\\src\\hw09Task1\\SomeClass.class"));
//                byte[] bytes = Files.readAllBytes(Paths.get("R:\\ССиОР\\СРТиС\\03 - Разработка\\Тимохин И.В\\055_IdeaProjects\\src\\hw09Task1\\SomeClass.class"));
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.findClass(name);
    }
}