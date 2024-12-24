public class Generador extends Thread{

	private Monitor monitor;
	private int fin, time;
	
	public Generador(Monitor monitor, int fin, int time) {
		this.monitor = monitor;
		this.time = time;
		this.fin = fin;
	}
	
	@Override
	public void run() {
		while(monitor.getCount() < fin) {
		monitor.entrada(0);
		monitor.entrada(1);
		monitor.setCount();
		try {
			sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
}
