package Testes;
        
import java.util.logging.Level;
import java.util.logging.Logger;

public class InternetBankingSystem {
  public static void main(String [] args  ) {
    Account accountObject = new Account (); 
    Thread t1 = new Thread(new MyThread(accountObject));
    Thread t2 = new Thread(new YourThread(accountObject));
    Thread t3 = new Thread(new HerThread(accountObject));

    t1.start();
    t2.start();

    try
    {
            Thread.sleep(5000);
    }
    catch (InterruptedException e)
    {
           // TODO Auto-generated catch block
            e.printStackTrace();
    }

    t3.start();
    


   } // end main()
} 


class Account {   // the 'monitor'
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



class MyThread implements Runnable {
    Account account;
    MyThread (Account c) {
        account = c;
    }
    
    public void run() {
	for(int i=0; i<100; i++)
	    account.deposit(100); 
    }
}

class YourThread implements Runnable {
    Account account;
    YourThread (Account s) {
        account = s;
    }
    
    public void run() {
	for(int i=0; i<100; i++)
		account.withdraw(100);
    }
}

class HerThread implements Runnable {
    Account account;
    HerThread (Account s) {
        account = s;
    }
    
    public void run() {
        account.enquire();
    }
}
