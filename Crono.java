public class Crono extends Thread{
	
	private long Tbeging, Tend, Ttot;
	private Monitor monitor;
	private int fin;
	private logWriter log;
	private boolean n1, n2;
	
	public Crono(Monitor monitor, int fin, logWriter log) {
		Tbeging = System.currentTimeMillis();
		this.monitor = monitor;
		this.fin = fin;
		this.log = log;
		n1 = true;
		n2 = true;
	}
	
	@Override
	public void run() {
		while(monitor.getCount() < fin || (n1 || n2)) {	
			try {
				sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Tend = System.currentTimeMillis();
		Ttot = Tend - Tbeging;
		int milis = (int) (Ttot % 1000);
		Ttot /= 1000;
		int hora = (int) (Ttot / 3600);
		int min1 = (int) (Ttot % 3600);
		int min = (int) (min1 / 60);
		int seg = (int) (Ttot % 60);
		int[] aux = {hora,min,seg,milis};
		log.setTimes(aux);
		System.out.printf("Tiempo de ejecucion: %02d:%02d:%02d:%03d %n", hora, min, seg, milis);		
	}	
	
	public void setN(int id) {
		switch(id) {
		case 1:
			n1 = false;
			break;
		case 2:
			n2 = false;
			break;
		}
}
}
