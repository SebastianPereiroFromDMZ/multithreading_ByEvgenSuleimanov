package net.proselyte.concurrency.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixedThreadPoolCallableDemo {
    public static void main(String[] args) {

        int cores = Runtime.getRuntime().availableProcessors();

        List<Future<Integer>> futures = new ArrayList<>();//Future интерфейс который позволяет работать с отложенным результатом

        long start = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newFixedThreadPool(cores * 100)) {
            for (int i = 0; i < 10000; i++) {
                futures.add(executorService.submit(new GenerateRandomIntegerCallableTask()));
            }
        }

        futures.forEach(integerFuture -> {
            try {
                System.out.println(integerFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Processed in: " + duration + " ms");
    }

}
