package net.proselyte.concurrency.synchronization;

public class IncrementCounterSynchronizedDemo {
    static Integer counter = 0;

    public static void main(String[] args) {

        //класс с синхринизированным методом инкремента increment()
        //происходит запуск 2 потоков имеющие доступ к общему ресурсу, но засщет синхронизации сщетчик раз за разов отдает корректные значения
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                increment();
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
        System.out.println("Time elapsed: " + duration);
    }

    private static synchronized void increment() {
        counter++;
    }
}
