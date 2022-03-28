package ru.job4j.pecs;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
//        String hashName = "967b38d5-26c2-42fc-b12a-a49133c62f5q";
//        File file = Paths.get("/opt/region-server/files/").resolve(hashName.substring(0, 1)).resolve(hashName).toFile();
//        byte[] fileContent = Files.readAllBytes(file.toPath());
//        File dest = new File(file.getAbsolutePath() + ".jpg");
//        boolean b = file.renameTo(dest);
//
//        System.out.println(dest.exists());
//        InputStream io = new FileInputStream(dest);
//        while (io.read() != -1) {
//            int read = io.read();
//            System.out.println(read);
//        }
//        System.out.println(dest.getAbsolutePath());
//        System.out.println(b);
        Stream.of(true, false, true)
                .filter(b -> b)
                .forEach(System.out::println);

//        Boolean b = null;
//        if (b) {
//            System.out.println("sddsd");
//        }
//
//        Class3[] arr = new Class3[10];
//        arr[0] = new Class3();
//        arr[0] = new Class4();
//
//        List<Class3>list = new ArrayList<>();
//        list.add(new Class4());

    }

    public void someMethod(List<? extends Class3> list, List<? super Class3> list2) {
        for (Class3 class3 : list) {
            list2.add(class3);
        }
        Class3 class3 = list.get(0);
        Class2 class2 = list.get(0);
        Class1 class1 = list.get(0);
        Object o = list.get(0);
        // можно достать объекты приведенные к любому классу предку, но не к наследнику
        // Class4 class4 = list.get(0); не компилируется
        // не возможно ничего добавить в такой список list.add(new Class3());
        // так как не известно что в неб будет, может Class100500 или Class3

        /*
        Почему в List<? extends Class3> list нельзя положить объекты суперклассов (Class0, Class1, Class2), думаю,
        очевидно: негоже объекту-наследнику ссылаться на объект-предок.  В List<Integer> list нельзя положить объект,
         имеющий тип Number, можно только наоборот. Но что нам мешает, добавить в List<? extends Class3> list объект
         типа Class4 или Class5? Да всё тот же самый принцип! В момент компиляции JVM не знает, что во время выполнения
         программы будет скрываться под маской List<? extends Class3>. Может это будет List<Class4>,
         а может быть List<Class100500>. И если это действительно окажется List<Class100500>,
         а вы будете добавлять туда элемент, имеющий тип Class3 или Class4, это будет равносильно тому,
         что вы добавляете элемент с типом Number в List<Integer>. Вот, если бы компилятор был уверен,
         что List<? extends Class3> во время выполнения программы окажется либо листом элементов типа Class3,
         либо листом элементов типа-предка Class3, то он бы не возражал, против того, чтобы добавить в лист любых
         потомков Class3.
         */
    }

    public void someMethod2(List<? super Class3> list) {
        list.add(new Class4());
        list.add(new Class3());
        Object object = list.get(0);
        // не компилируется list.add(new Class2())
    }

    public void someMethod3(List<? extends Class3> list) {
       // list.add(new Class4());
    }

}
