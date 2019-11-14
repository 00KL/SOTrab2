package Testes;



public class TestSync {
   public static void main(String [] args  )  {
	 MyMonitor_TestSync m1= new MyMonitor_TestSync();
	 MyMonitor_TestSync m2= new MyMonitor_TestSync();
	 MyThread_TestSync t1 = new MyThread_TestSync(m1,1);
	 MyThread_TestSync t2 = new MyThread_TestSync(m2,1);
         //MyThread t2 = new MyThread(m1,2);
         
   }
}//TODO pesquisar sobre metodos e objetos estáticos

class MyThread_TestSync extends Thread {
    MyMonitor_TestSync monitor; 
    int method;

    MyThread_TestSync(MyMonitor_TestSync monitor, int method) {
        this.monitor=monitor; this.method = method; start();
    }

    MyThread_TestSync(Account_TestSync accountObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void run() {
       if(method==1) monitor.method1();
       else if (method==2) monitor.method2();
    }
}

class MyMonitor_TestSync {   // the 'monitor'
    public synchronized void method1( ) {
       for (int i = 0; i < 1000; i++) {
          System.out.print("1"); 
       }

     } //... 

    public synchronized void method2( ) {
       for (int i = 0; i < 1000; i++){ 
          System.out.print("2");
       }
    }
}

class Account_TestSync {   // the 'monitor'
   int balance;

   // if 'synchronized' is removed, the outcome is unpredictable
   //public synchronized void deposit(int deposit_amount ) {
   public void deposit(int deposit_amount ) {
     // METHOD BODY : 
	balance += deposit_amount;
   } 

   //public synchronized void withdraw(int deposit_amount ) {
   public void withdraw(int deposit_amount ) {
	 // METHOD BODY: balance -= deposit_amount;
	balance -= deposit_amount;
   } 

   //public synchronized void enquire( ) {
   public  void enquire( ) {
	 // METHOD BODY: display balance.
	System.out.println("Current balance = " + balance); 
   }
}