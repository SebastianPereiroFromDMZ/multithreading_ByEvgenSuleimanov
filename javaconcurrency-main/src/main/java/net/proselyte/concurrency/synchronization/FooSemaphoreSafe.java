package net.proselyte.concurrency.synchronization;

import java.util.concurrent.Semaphore;

public class FooSemaphoreSafe {
    //итак семафор, ниже создали 2 семафора с 0 permits (разрешения) соответственно когда поток встает у семафора
    //у которого 0 пермишенсов он стоит и ждет разрешения betweenFirstAndSecond.acquire(),
    //разрешение выдается betweenFirstAndSecond.release()(этот метод увеличивыет сщетчик разрешений на 1 у семафора,
    //соответсвенно ожидающий поток может продолжить выполнение)
    private final Semaphore betweenFirstAndSecond = new Semaphore(0);
    private final Semaphore betweenSecondAndThird = new Semaphore(0);

    public void first() {
            System.out.println("first");
            betweenFirstAndSecond.release(); //release - свобождать
    }

    public void second() {
        try {
            betweenFirstAndSecond.acquire();//acquire - получить
            System.out.println("second");
            betweenSecondAndThird.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void third() {
        try {
            betweenSecondAndThird.acquire();
            System.out.println("third");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
