package net.proselyte.concurrency.synchronization;

public class IncrementCounterSynchronizedBlockDemo {

    //этот класс и IncrementTwoCountersSynchronizedOnObjectDemo класс показывают разницу во времени выполнении кода:
    //здесь в этом клессе есть 2 синхронизованных метода инкремента и оба они завязанны на одном и томже отбьекте (обьекте этого класса)
    //и хоть и потоки инкрементируют каждый свой сщетчик МОНиТОР у них один, тоесть если один поток захватил монитор у этого обьекта
    //воторой ждет пока освободится этот монитор
    static Integer counter = 0;
    static Integer anotherCounter = 0;

    public static void main(String[] args) {
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
        System.out.println("Time elapsed: " + duration);    }

    private static void incrementCounter() {
        synchronized (IncrementCounterSynchronizedBlockDemo.class) {
            counter++;
        }
    }

    private static void incrementAnotherCounter() {
        synchronized (IncrementCounterSynchronizedBlockDemo.class) {
            anotherCounter++;
        }
    }
}
