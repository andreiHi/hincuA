package ru.job4j.serializable;

import java.io.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 14.06.2019.
 * @version $Id$.
 * @since 0.1.
 */
public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        serialize("temp.out");
        TestSerial ts = desirealize("temp.out");
        System.out.println(ts.version);

    }

    private static void serialize(String fileName) throws IOException {
        TestSerial ts = new TestSerial();
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(ts);
        oos.flush();
        oos.close();
    }
    
    private static TestSerial desirealize(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)));
        TestSerial ts = (TestSerial) ois.readObject();
        return ts;
    }
}
