package Trab2;

import Trab2.Cliente;
import Trab2.Barbeiro;

public class Barbearia { //acho q barbearia tem q virar um monitor, ai as classes ficam chamando cada metodo dentro do monitor(q vao ser sincronizados)

	public static void main(String[] args) {
		int num_barbeiros, num_cadeiras, total_clientes; //acho q isso vai virar atributo do monitor e n mais variavel na maind
		                
                System.out.println("Quantos barbeiros? ");
                

		Barbeiro roberval = new Barbeiro(10);
		Cliente regata = new Cliente(20);
		
		Thread t1 = new Thread(roberval);
		t1.start();
		Thread t2 = new Thread(regata);
		t2.start();
		
		
//		System.out.println("roberval: " + roberval.get_barbeiroID() + "\nregata: " + regata.get_clienteID());
//		System.out.println("barbeiros: " + num_barbeiros + "\n" + "cadeiras: " + num_cadeiras + "\n" + "total clientes: " + total_clientes );
	}

//		TODO funçoes do monitor (synchronized)
//		public boolean cortaCabelo(Cliente c) {}
//		public Cliente proximoCliente() {}
//		public void corteTerminado(Cliente c){}
	
}
