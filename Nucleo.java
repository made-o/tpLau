
public class Nucleo extends Thread {

	private Monitor monitor;
	private int time, fin, t1, t2, count, id;
	private logWriter log;
	private RdP red;
	private Crono cron;
	
	public Nucleo(Monitor monitor, int time, int fin, int t1, int t2,int id,logWriter log, RdP red, Crono cron){
		
		this.monitor = monitor;
		this.fin = fin;
		this.time = time;
		this.t1 = t1;
		this.t2 = t2;
		this.id = id;
		count = 0;
		this.log = log;
		this.red = red;
		this.cron = cron;

	}
	
	@Override
	public void run() {
		while(monitor.getCount() < fin || red.getSizeB(id) != 0){
		if(red.getSizeB(id) != 0) {
			monitor.entrada(t1);
			try {
				sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			monitor.entrada(t2);
			count++;
		}
	}
		log.setCountN(count, id);
		log.setN(id);
		cron.setN(id);
	}
	
	
}
