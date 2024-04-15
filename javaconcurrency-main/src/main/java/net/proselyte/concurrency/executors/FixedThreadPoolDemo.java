package net.proselyte.concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {

    //класс демонстрации фиксированного количества потоков, тут также интересует время выполнения программы
    //(здесь потоков будет у нас от количества процессоров на компьютере допустим 4 в отличие от предыдущего примера(SingleThreadPoolDemo) где поток всего 1)
    //соответственно время выполнения программы будет быстрее
    public static void main(String[] args) {

        int cores = Runtime.getRuntime().availableProcessors();//количество доступных процессоров (ядер)
        System.out.println(cores);

        long start = System.currentTimeMillis();

        try (ExecutorService executorService = Executors.newFixedThreadPool(cores -1)) {//можно поставить 100, выполнение будет гораздо быстрее
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
