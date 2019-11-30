package Trab2;

public class Barbeiro extends Pessoa implements Runnable{
	
	public Barbeiro(int id) {
		super(id);
	}
	
	public int get_barbeiroID() {
		return super.getId();
	}

	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			System.out.println("barbeiro");
		}
	}
	
}
