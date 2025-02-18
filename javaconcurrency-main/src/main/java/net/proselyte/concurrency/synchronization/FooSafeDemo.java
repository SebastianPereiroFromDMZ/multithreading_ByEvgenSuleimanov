package net.proselyte.concurrency.synchronization;

public class FooSafeDemo {
    public static void main(String[] args) {

        //класс где у нас все идет так как мы задумали в отличие от класса FooUnsafeDemo,
        //здесь у нас обьект класса FooSafe именно там логика правильного исполнения потоков

        FooSafe foo = new FooSafe();

        Thread t1 = new Thread(foo::second);
        Thread t2 = new Thread(foo::first);
        Thread t3 = new Thread(foo::third);

        t1.setName("T1");
        t2.setName("T2");
        t3.setName("T3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
