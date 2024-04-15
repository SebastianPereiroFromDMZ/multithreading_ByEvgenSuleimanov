package net.proselyte.concurrency.collections;

import java.util.concurrent.BlockingQueue;

public class WriteToQueueWorker implements Runnable {

    private final BlockingQueue<Integer> queue;

    public WriteToQueueWorker(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int counter = 0;
        while (true) {//в безконечном цикле
            try {
                System.out.println("Put: " + counter);
                queue.put(counter++);//закидываем число и инкрементируем его
                Thread.sleep(100);//засыпаем на 0.1 сек
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
