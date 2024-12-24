import java.util.concurrent.Semaphore;

public class QueueSemaphore {
	private Semaphore mutex;
	private Semaphore cond;
	private int blocked;
	
	public QueueSemaphore(Semaphore mutex) {
		this.mutex = mutex;
		cond = new Semaphore(0,true);
        blocked=0;
	}
	
	public void delay() {
	      blocked++;
	        mutex.release();
	        try {
	            cond.acquire();
	            mutex.acquire();
	        } catch (InterruptedException e) {
	        }
	}
	
	public void resume() {
		if(blocked>0)
		blocked--;
		cond.release();
	}

	public boolean empty() {
		if(blocked > 0) {
			return true;}
			else {
				return false;}
	}
}
