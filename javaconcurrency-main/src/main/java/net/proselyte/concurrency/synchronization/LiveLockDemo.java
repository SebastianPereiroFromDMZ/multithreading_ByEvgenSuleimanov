package net.proselyte.concurrency.synchronization;

public class LiveLockDemo {
    public static void main(String[] args) {

        //класс демонстрации LiveLock:
        //Есть 2 обьекта класса Eater, есть 1 обьект класса Spoon
        //В классе Eater в цикле метода eatWith меняется вледелец класса Spoon(ложки) без конца
        //тоесть 2 потока постоянно передают друг другу ложку)) это и есть LiveLock вроде бы что то делается а результата нет
        final Eater husband = new Eater("Bob");
        final Eater wife = new Eater("Alice");

        final Spoon s = new Spoon(husband);

        new Thread(() -> husband.eatWith(s, wife)).start();
        new Thread(() -> wife.eatWith(s, husband)).start();
    }
}
