package net.proselyte.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadPoolDemo {
    public static void main(String[] args) {

        //пример исполнителя (Executor) который обслуживает в еденицу времени только 1 поток
        //(ждет когда исполняемый поток закончит работу и если стоит задача запустить еще потоки он запустит еще 1)
        //здесь важно обратить внимание на затраченное время исполнения 100 потоков

        long start = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            for (int i = 0; i < 100; i++) {
                executorService.submit(new GenerateRandomIntegerTask());
            }
        } finally {
            long end = System.currentTimeMillis();
            long duration = end - start;
            System.out.println("Processed in: " + duration + " ms");
        }
    }
}
