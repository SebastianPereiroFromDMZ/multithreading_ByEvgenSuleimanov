package net.proselyte.concurrency.synchronization;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FooSafe {
    //класс с логикой правильного запуска потоков
    private final ReentrantLock lock = new ReentrantLock();//внешний монитор с доп функционалом
    private final Condition firstMethodCalled = lock.newCondition();//доп функционал Condition это условие,
    //тоесть у нас есть ReentrantLock и мы даем ему условие когда лок захватить или освободить
    private final Condition secondMethodCalled = lock.newCondition();

    public void first() {
        lock.lock();
        try {
            System.out.println("first");
            firstMethodCalled.signal();//даем сигнал что условие соблюденно
        } finally {
            lock.unlock();
        }
    }

    public void second() {
        lock.lock();
        try {
            firstMethodCalled.await();//ждем пока условие не будет соблюденно(если выполнение этого потока начиловсь раньше чем дам сигнел на резрешение,
            //этот поток встает в чередь на ожидание этого сигнала), а будет оно соблюденно когда в методе first()
            //сработает firstMethodCalled.signal() когда сигнал получен (разрешение) выполнение кода идущего ниже продолжится
            System.out.println("second");
            secondMethodCalled.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void third() {
        lock.lock();
        try {
            secondMethodCalled.await();
            System.out.println("third");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
