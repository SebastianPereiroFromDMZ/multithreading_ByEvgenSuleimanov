package net.proselyte.concurrency.synchronization;

import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicInteger;

public class PingWorker implements Runnable {

    private final AtomicInteger counter;
    private final Exchanger<AtomicInteger> exchanger;



    public PingWorker(AtomicInteger counter, Exchanger<AtomicInteger> exchanger) {
        this.counter = counter;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            try {
                AtomicInteger exchangedCounter = exchanger.exchange(counter);//Ожидает прибытия другого потока в эту точку обмена (если только текущий поток не прерван),
                //а затем передает ему данный объект, получая взамен его объект. Тоесть exchangedCounter ЭТО СЩЕТЧиК КОТОРЫЙ МЫ ПОЛУЧиЛи иЗ ДРУГОГО ПОТОКА

                System.out.println("PING: " + exchangedCounter.getAndIncrement());

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
