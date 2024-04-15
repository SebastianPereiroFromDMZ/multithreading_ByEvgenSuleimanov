package net.proselyte.concurrency.collections;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayDemo {
    public static void main(String[] args) {

        //пример конкареда массива (потокобезопасная реализация ArrayList):
        //WriteToCOWArrayWorker заменяет рандомный элемент коллекции на рандомное число
        //ReadFromCOWArrayWorker просто выводит в печать коллекцию
        //Запускаем 3 потока: 2 меняют элементы, 1 печатает коллекцию

        List<Integer> list = new CopyOnWriteArrayList<>();
        list.addAll(Arrays.asList(100500, 200800, 100, 200, 300, 500));

        WriteToCOWArrayWorker writeToCOWArrayWorker = new WriteToCOWArrayWorker(list);
        ReadFromCOWArrayWorker readFromCOWArrayWorker = new ReadFromCOWArrayWorker(list);

        Thread t1 = new Thread(writeToCOWArrayWorker);
        Thread t2 = new Thread(writeToCOWArrayWorker);
        Thread t3 = new Thread(readFromCOWArrayWorker);

        t1.start();
        t2.start();
        t3.start();

        System.out.println("main finished");

    }
}
