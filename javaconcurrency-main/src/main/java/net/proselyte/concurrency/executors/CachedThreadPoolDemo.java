package net.proselyte.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CachedThreadPoolDemo {
    public static void main(String[] args) {

        //демонстрация исполнителя потоков который сам автоматически расширяется когда нагрузка увеличивается

        long start = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 400; i++) {
                executorService.submit(new GenerateRandomIntegerTask());
            }
            System.out.println("TEST");
        } finally {
            long end = System.currentTimeMillis();
            long duration = end - start;
            System.out.println("Processed in: " + duration + " ms");
        }
    }
}
