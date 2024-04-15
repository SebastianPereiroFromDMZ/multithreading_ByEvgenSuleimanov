package net.proselyte.concurrency.threadsintro;

public class RunnableCounterDemo {
    public static void main(String[] args) {
        RunnableCounterWorker scw1 = new RunnableCounterWorker("A", 150);
        RunnableCounterWorker scw2 = new RunnableCounterWorker("B", 150);


        // МНОГОПОТОЧНАЯ ОБРАБОТКА
        Thread t1 = new Thread(scw1);
        Thread t2 = new Thread(scw2);
        t1.start();
        t2.start();
    }
}
