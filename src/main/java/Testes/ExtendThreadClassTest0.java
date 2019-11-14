package Testes;

public class ExtendThreadClassTest0 {

    public static void main(String args[]) {
	// Create object of a class that is subclass of Thread class
  	System.out.println("Creating PrintNameThread object instance..");
        PrintNameThread pnt1 = new PrintNameThread("A");
        // Start the thread execution by invoking start() 
        //System.out.println("Calling start() method of " + pnt1.getName() + "\n"); 
        
        		
        System.out.println("Creating PrintNameThread object instance..");
        PrintNameThread pnt2 = new PrintNameThread("B");
        //System.out.println("Calling start() method of " + pnt2.getName() + "\n");
        
              
        System.out.println("Creating PrintNameThread object instance..");
        PrintNameThread pnt3 = new PrintNameThread("C");
        //System.out.println("Calling start() method of " + pnt3.getName() + "\n");
        pnt3.start(); 
        pnt2.start(); 
        pnt1.start();
    }
}

class PrintNameThread extends Thread {
    PrintNameThread(String name) {
        super(name);
    }

    // Override the run() method of the Thread class.
    // This method gets executed when start() method is invoked.
    @Override
    public void run() {
        System.out.println("run() method of the " + 	
                         this.getName() + " thread is called" );
        for (int i = 0; i < 10; i++) {
            System.out.print(this.getName());
        }
    }
}
