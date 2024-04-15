package net.proselyte.concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {

    private final Integer THRESHOLD = 1000; //THRESHOLD - порог

    private final int [] array;
    private final int start;
    private final int end;

    public SumTask(int[] array, int start, int end) {//при инициализации нового обьета класс SumTask в конструктор передаем массив,
        //начало этого массива и его конец.
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {//compute - вычислять
        if (end - start <= THRESHOLD) {//если разница между концом и началом массива(количество элементов) меньше нашего порога (THRESHOLD)
            //то выполняется обычный код
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {//иначе с использованием ForkJoin
            int mid = (start + end) / 2; //вычисляем середину переданного массива
            SumTask leftTask = new SumTask(array, start, mid);//левая часть массива (от начала до середины)
            SumTask rightTask = new SumTask(array, mid, end);//правая часть
            leftTask.fork();//форкаем левую часть
            long rightResult = rightTask.compute();//на правую часть мы опять вызываем метод compute (рекурсивные буду вызовы)
            long leftResult = leftTask.join();
            return leftResult + rightResult;
        }
    }
}
