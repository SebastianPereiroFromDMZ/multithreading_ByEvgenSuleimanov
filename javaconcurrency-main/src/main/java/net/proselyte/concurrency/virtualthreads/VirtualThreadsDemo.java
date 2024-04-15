package net.proselyte.concurrency.virtualthreads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadsDemo {
    public static void main(String[] args) {

        //класс демонстрация работы с виртуальными потоками!!!
        //Обычный поток он мапится на поток операционной системы и так он идет в CP (цестральный процессор)
        //тоесть 1 поток JVM на 1 поток OS
        //при виртуальных потоках все иначе они легковесны и их может множество намапится на 1 поток OS
        //тоесть n виртульных потоков JVM на 1 поток OS
        //плюс такого подхода при создании большого количества задач и помощения этих задач в виртуальные потоки мы не получим ошибку по памяти

        List<GenerateRandomNumberTask> tasks = generateTasks();
        long start = System.currentTimeMillis();
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            tasks.forEach(executorService::submit);
        }

        long end = System.currentTimeMillis();
        long duration = end - start;
        System.out.println("Processed in: " + duration + " ms");
    }

    private static List<GenerateRandomNumberTask> generateTasks () {
        List<GenerateRandomNumberTask> tasks = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            tasks.add(new GenerateRandomNumberTask());
        }
        return tasks;
    }
}
