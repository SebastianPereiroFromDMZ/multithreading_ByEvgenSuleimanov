package net.proselyte.concurrency.synchronization;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicInteger;

public class ExchangerDemo {
    public static void main(String[] args) {

        //класс для демонстрации обмена информацией между потоками, нет синхронизации!!! ее надо доделывать
        //Тоесть класс Exchanger он позволяет обмениваться информацией между потоками но синхронизация должны обеспечить именно мы
        AtomicInteger counter = new AtomicInteger();

        Exchanger<AtomicInteger> exchanger = new Exchanger<>();//сам обьект обмена (в алмазных скобках тип каким можем обмениваться),
        //общий этот обьект передается в конструкторы разных потоков (PingWorker и PongWorker) и в этом обьекте они будут встречаться и обмениваться данными

        PingWorker pingWorker = new PingWorker(counter, exchanger);
        PongWorker pongWorker = new PongWorker(counter, exchanger);

        Thread t1 = new Thread(pingWorker);
        Thread t2 = new Thread(pongWorker);

        t1.start();
        t2.start();

    }
}
