
public class Verificador extends Thread{
	
	private Monitor monitor;
	private int fin, t, id;
	private RdP red;

	
	public Verificador(Monitor monitor, int fin, int t, int id, RdP red) {
		
		this.monitor = monitor;
		this.fin = fin;
		this.t = t;
		this.id = id;
		this.red = red;

	}
	
	@Override
	public void run() {
		while(monitor.getCount() < fin || red.getNoti(id)){ // 
			if(red.getCPUState(id) && red.getNoti(id)) { // Esto esta por si en el ultimo producto ambos V entran y solo uno le llega el producto para dispararse
			monitor.entrada(t);
			}
		}
	}
	
}
