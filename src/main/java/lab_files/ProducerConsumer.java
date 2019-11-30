package lab_files;

import static java.lang.Thread.sleep;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumer{

    public static void main(String[] args) {
       
        MyBuffer b = new MyBuffer();
       
        Producer p1 = new Producer(b, 1);
        Consumer c1 = new Consumer(b, 1);
        Consumer c2 = new Consumer(b, 2);
        Consumer c3 = new Consumer(b, 3);
        Consumer c4 = new Consumer(b, 4);
        Consumer c5 = new Consumer(b, 5);
        Consumer c6 = new Consumer(b, 6);
       
        
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        c5.start();
        c6.start();
        
        p1.start();
        
    }
}   

class MyBuffer {
    private int contents, contCli = 0;
    private boolean available = false;
    Queue<Consumer> fila = new LinkedList<Consumer>();
    
    public synchronized int get(Consumer c) {
        contCli++;
        fila.add(c);
        System.out.format("Consumer %d waiting \n", c.getNumber());
        while (available == false) {
            try {
                wait();  //this.wait()
            } catch (InterruptedException e) { }
        }
        available = false;
        System.out.format("consumer %d \n", c.getNumber());
        //notifyAll();
        notify();
        contCli--;
        return contents;
    }
    public synchronized void put(int who, int value) {
        while(contCli > 1){
            while (available == true) {
                try {
                    wait();  //this.wait()
                } catch (InterruptedException e) { }
            }
            contents = value;
            available = true;
            System.out.format("Producer %d check ", who, contents);
            Consumer test = fila.poll();
            test.notify();
            //notifyAll();
        }
        
    }
}

class Producer extends Thread {

    private MyBuffer buffer;
    private int number;

    public Producer(MyBuffer b, int number) {
        buffer = b;
        this.number = number;
    }
    
    

    public void run() {
//        for (int i = 0; i < 10; i++) {
//            buffer.put(number, i);
//            try {
//                sleep((int)(Math.random() * 100));
//            } catch (InterruptedException e) { }
//        }
        
        buffer.put(number, number);
    }
}

class Consumer extends Thread {

    private MyBuffer buffer;
    private int number;

    public Consumer(MyBuffer b, int number) {
        buffer = b;
        this.number = number;
    }
    
    public int getNumber(){
        return number;
    }
    
    public void run() {
        int value = 0;
        value = buffer.get(this);
    }
}   