package net.proselyte.concurrency.threadsintro;

public class ThreadCounterWithDaemonFlagDemo {
    public static void main(String[] args) {
        ThreadCounterWithDaemonFlagWorker tcw1 = new ThreadCounterWithDaemonFlagWorker("A", 1000, true);
        ThreadCounterWithDaemonFlagWorker tcw2 = new ThreadCounterWithDaemonFlagWorker("B", 100, false);

        //здесь потоком выставляем флаг демон (вспомогательный) поток или нет


        // МНОГОПОТОЧНАЯ ОБРАБОТКА
        tcw1.start();
        tcw2.start();

        //не гарантируется порядок
        System.out.println("Process is finished!!!");
    }
}
