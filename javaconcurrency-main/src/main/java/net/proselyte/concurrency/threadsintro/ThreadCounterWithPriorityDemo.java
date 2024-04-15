package net.proselyte.concurrency.threadsintro;

public class ThreadCounterWithPriorityDemo {
    public static void main(String[] args) {
        ThreadCounterWithPriorityWorker tcw1 = new ThreadCounterWithPriorityWorker("A", 5, 10);
        ThreadCounterWithPriorityWorker tcw2 = new ThreadCounterWithPriorityWorker("B", 5, 1);

        //здесь создаем потоки с приорететом!! Приоритет задается от 1 до 10(где 1 это минимальный приоритет а 10 максимальный, дефолтный приоритет 5)
        //Важно что решение о приоритете потока все равно не гарантирует именно такой приоритет как мы поставим!!!
        //Все равно приоритет выставляем ОС и плевать она хотела что мы поставили,но знать особенность приоритет потоков необходимо: приоритет их соблюдает JVM
        //и приоритет не работает с виртуальныйми потоками!!!


        // МНОГОПОТОЧНАЯ ОБРАБОТКА
        tcw1.start();
        tcw2.start();

        // НЕ ГАРАНТиРУЕТСЯ ПОРЯДОК
        System.out.println("Process is finished!!!");
    }
}
