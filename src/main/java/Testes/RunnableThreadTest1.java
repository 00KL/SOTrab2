package Testes;

public class RunnableThreadTest1 {
    public static void main(String args[]) {
        PrintNameRunnable pnt1 = new PrintNameRunnable("A");
        Thread t1 = new Thread(pnt1);
        
       
        PrintNameRunnable pnt2 = new PrintNameRunnable("B");
        Thread t2 = new Thread(pnt2);
        t2.start();
      
        PrintNameRunnable pnt3 = new PrintNameRunnable("C");
        Thread t3 = new Thread(pnt3);
        t3.start();
        t1.start();
        
    }
}

class PrintNameRunnable implements Runnable {
    String name;
    PrintNameRunnable(String name) {
        this.name = name;
    }
    
    // Implementation of the run() defined in the
    // Runnable interface.
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.print(name);
        }
    }
}
