
public class Alimentador extends Thread{

	private Monitor monitor;
	private int fin, t1, t2, t3, id;
	private RdP red;
	
	public Alimentador(Monitor monitor, int fin, int t1, int t2, int t3, int id, RdP red) {
		this.monitor = monitor;
		this.fin = fin;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.red = red;
		this.id = id;
	}
	
	@Override
	public void run() {
		while(monitor.getCount() < fin || red.getSizeB(id) != 0) {
			if(red.getSizeB(id) != 0) {
				monitor.entrada(t1);
				monitor.entrada(t2);
				monitor.entrada(t3);
			}
		}
}
}
