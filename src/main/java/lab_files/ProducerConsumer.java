package lab_files;

import static java.lang.Thread.sleep;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerConsumer{

    public static void main(String[] args) throws InterruptedException {
       
        int num_barbeiros = 2, num_cadeiras = 3, total_clientes = 6; //acho q isso vai virar atributo do monitor e n mais variavel na maind
	Scanner leitor = new Scanner(System.in); 
                
        //System.out.println("Quantos barbeiros? ");
        //num_barbeiros = leitor.nextInt();
        //System.out.println("Quantas cadeiras? ");
        //num_cadeiras = leitor.nextInt();
        //System.out.println("Quantos clientes? ");
        //total_clientes = leitor.nextInt();
        
        MyBuffer b = new MyBuffer(num_cadeiras);
        
        Producer p1 = new Producer(b, 1);      
        p1.start();
        
        Producer[] vetBar = new Producer[total_clientes+1];
        for(int i = 1; i <= num_barbeiros; i++){
            vetBar[i] = new Producer(b,i);
            vetBar[i].start();
        }
        
        Consumer[] vetCli = new Consumer[total_clientes+1];
        for(int i = 1; i <= total_clientes; i++){
            vetCli[i] = new Consumer(b,i);
            vetCli[i].start();
        }
        
        
        
    }
}   

class MyBuffer {
    private int contents;
    private boolean available = false;
    Queue<Consumer> fila = new LinkedList<Consumer>();
    int num_cadeiras;
    
    public MyBuffer(int num_cadeiras){
        this.num_cadeiras = num_cadeiras;
    }
    
    public synchronized int get(Consumer c) throws InterruptedException {
        while(true){
            if(fila.size() == num_cadeiras){
                System.out.format("Cliente %d tentou entrar na barbearia, mas está lotada... indo dar uma voltinha\n", c.getNumber());
                notifyAll();
            }else{
                System.out.format("Cliente %d esperando corte...\n", c.getNumber());
                break;
            }
            sleep(300);
        }
            
        notifyAll();
        fila.add(c);
        c.setValid(false);
        //System.out.format("Consumer %d waiting \n", c.getNumber());
        while(!c.getValid()){
            try {
                wait();  //this.wait()
            } catch (InterruptedException e) { }
        }
        
        System.out.format("Cliente %d terminou o corte...saindo da barbearia!\n", c.getNumber());
        
        //notifyAll();
        //notify();
        return contents;
    }
    
    public synchronized void put(Producer p) throws InterruptedException {
//        System.out.format("test\n");
//        while(contCli > 1){
//            while (available == true) {
//                tr1y {
//                    wait();  //this.wait()
//                } catch (InterruptedException e) { }
//            }
//            contents = value;
//            available = true;
//            System.out.format("Producer %d check ", who, contents);
//            //Consumer test = fila.poll();
//            //ystem.out.println(test.getNumber());
//            //test.notify();
//            notifyAll();
//        }
//          “Barbeiro Y indo dormir um pouco... não há clientes na barbearia...”
//◦           “Barbeiro Y acordou! Começando os trabalhos!”

          //System.out.println("Vamo mano namoral");
          //System.out.println("cade/n");
          while(true){
              if(fila.isEmpty()){
                  System.out.format("Barbeiro %d indo dormir um pouco... não há clientes na barbearia...\n", p.getNumber());
                  wait();
              }
              System.out.format("Barbeiro %d acordou! Começando os trabalhos!\n", p.getNumber());
              synchronized(fila){
                Consumer a = fila.poll();
                if(a == null) continue;
                a.setValid(true);
                System.out.format("Cliente %d cortando cabelo com Barbeiro %d///////%d\n", a.getNumber(), p.getNumber(), a.getNumber());
                notifyAll();
              }
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
    
    public int getNumber(){
        return number;
    }

    public void run() {
        try {
            //        for (int i = 0; i < 10; i++) {
//            buffer.put(number, i);
//            try {
//                sleep((int)(Math.random() * 100));
//            } catch (InterruptedException e) { }
//        }

    buffer.put(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}

class Consumer extends Thread {

    private MyBuffer buffer;
    private int number;
    private boolean valid;
    
    public void setValid(boolean b){
       valid = b;
    }
    
    public boolean getValid(){
        return valid;
    }

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