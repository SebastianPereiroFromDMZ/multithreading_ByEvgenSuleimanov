package net.proselyte.concurrency.synchronization;

public class IncrementTwoCountersSynchronizedDemo {
    static Integer counter = 0;
    static Integer anotherCounter = 0;

    public static void main(String[] args) {
        //каждый из потоков изменяет свой сщетчик НО у нас 2 метода синхринизированные, азначит обьект лока будет ЭТОТ КЛАСС!!!
        //что влечет за собой хоть и потоки работают со своим отдельным сщетчиком, обьект блокировки у них общий!!!
        //значит когда 1 поток заходит в свой инкремент метод и захватывает лок по обьекту класса, 2 поток ждет освобождения этого монитора
        //отсюда вытекает увеличенное время выполнения кода


        long start = System.currentTimeMillis();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                incrementCounter();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                incrementAnotherCounter();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Counter: " + counter);
        System.out.println("Another counter: " + anotherCounter);
        System.out.println("Time elapsed: " + duration);
    }

    private static synchronized void incrementCounter() {
        counter++;
    }

    private static synchronized void incrementAnotherCounter() {
        anotherCounter++;
    }
}
