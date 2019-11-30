package Trab2;

public class Cliente extends Pessoa implements Runnable{

	public Cliente(int id) {
		super(id);
	}
	
	public int get_clienteID() {
		return super.getId();
	}

	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {
			System.out.println("cliente");
		}
	}

	

}
