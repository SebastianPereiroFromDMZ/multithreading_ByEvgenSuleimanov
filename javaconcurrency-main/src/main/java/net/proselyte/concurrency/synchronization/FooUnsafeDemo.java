package net.proselyte.concurrency.synchronization;

public class FooUnsafeDemo {
    public static void main(String[] args) {

        //класс где у нас идет не так как мы хотели бы (потки в коде идут последовательно и у каждого логика просто вызова
        //печати в кнсоль:first,second,third, мы бы хотели что бы эти потоки запускались также последовательно и выводили в консоль
        //печать именно в таком порядке, однако какой то поток может заснуть или операционка их вызовет в рандомном порядке и в печать пойдет рандомные записи)

        FooUnsafe foo = new FooUnsafe();
        Thread t1 = new Thread(foo::first);
        Thread t2 = new Thread(foo::second);
        Thread t3 = new Thread(foo::third);

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
