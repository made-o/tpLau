import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Monitor {
	
	private Semaphore mutex;
	private QueueSemaphore[] queues;
	private RdP red;
	private Politica politica;
	private int count;
			
	public Monitor(RdP red, Politica politica) {
		
		this.red = red;
		count = 0;
		mutex=new Semaphore(1,true);
		this.politica = politica;
		queues = new QueueSemaphore[15];
		for(int i =0;i<15;i++) 
		{
			queues[i] = new QueueSemaphore(mutex);
		}
	}
	
	public void entrada(int ts) {
		System.out.println("Entrada: " + ts +"");
		try {
			mutex.acquire();
			if(ts == 1) {
				ts = politica.queueBuffer();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(!red.estaSensibilizada(ts))
		{
			System.out.println("Delay: " + ts);
			queues[ts].delay();
		}
		red.shoot(ts);
		signal();          
        mutex.release(); 
	}
	
	private void signal() {
		ArrayList <Integer> desbloqueables = new ArrayList<Integer>(); 
		for(int i = 0;i<queues.length;i++)
		{
			if(queues[i].empty() && red.estaSensibilizada(i))
			{
				desbloqueables.add(i);
			}
		}
		if(!desbloqueables.isEmpty())
		{
			for(int a : desbloqueables)
			{
				System.out.println("Para desbloquear : " + a);
			}
			queues[politica.elegir(desbloqueables)].resume();
		}
	}
	
	public synchronized void setCount() {
		count++;
		System.out.println("Count: " + count);
		System.out.println("B1: " + red.getSizeB(1) + "    B2: " + red.getSizeB(2));
	}
	
	public int getCount() {
		return count;
	}
	
}
