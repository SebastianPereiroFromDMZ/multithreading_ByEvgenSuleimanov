package net.proselyte.concurrency.collections;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {

        //пример демонстрации потокобезопасной очереди (атомик очереди) в безконечном цикле закидываем числа (емкость очереди 100) и вытаскиваем

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);

        WriteToQueueWorker writeToQueueWorker = new WriteToQueueWorker(queue);
        ReadFromQueueWorker readFromQueueWorker = new ReadFromQueueWorker(queue);

        Thread t1 = new Thread(writeToQueueWorker);
        Thread t2 = new Thread(readFromQueueWorker);

        t1.start();
        t2.start();
    }
}
