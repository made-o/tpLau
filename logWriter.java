import java.io.FileWriter;
import java.io.IOException;

public class logWriter extends Thread {
	
	private int time;
	private FileWriter log;
	private Monitor monitor;
	private RdP red;
	private int time1;
	private int time2;
	private int time3;
	private logInvariantes logInv;
	private int hora,min,seg,milis;
	private int produc_final_n1;
	private int produc_final_n2;
	private boolean n1, n2;

	public logWriter(int time, int time1, int time2, int time3, Monitor monitor, RdP red, logInvariantes logInv) {
		
		this.time = time;
		this.time1 = time1;
		this.time2 = time2;
		this.time3 = time3;
		this.monitor = monitor;
		this.red = red;
		this.logInv = logInv;
		try {
			log = new FileWriter("C:/Users/usuario/Desktop/logTP3.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		n1 = true;
		n2 = true;
	}
	
	@Override
	public void run()  {
		try {
			log.write("Tiempo Generador: " + time1 + "ms  Tiempo Nucleo 1: " + time2 + "ms Tiempo Nucleo 2: " + time3 +"ms \r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(n1 || n2) {
		try {
			sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			log.write("Count:  " + monitor.getCount());
			log.write("  Buffer 1: " + red.getSizeB(1) + "  Buffer 2: " + red.getSizeB(2) + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		try {	
			log.write("\r\n");
			tiempoTotal();
			log.write("\r\n" + "Tareas generadas: " + monitor.getCount() + "\r\n");
			log.write("Tareas producidas por nucleo 1: " + produc_final_n1 + "\r\n");
			log.write("Tareas producidas por nucleo 2: " + produc_final_n2 + "\r\n");
			log.close();
			System.out.println("Log Finalizado");
			System.out.println("Producidos por Nucleo 1: " + produc_final_n1);
			System.out.println("Producidos por Nucleo 2: " + produc_final_n2);
			} catch (IOException e) {
			e.printStackTrace();
		}
		logInv.close();
		try {
			sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
}
	
	public void tiempoTotal() {

		try {
			log.write("Tiempo de ejecucuion: " + hora + " Horas ; " + min + " Minutos ; " + seg + " Segundos ; " + milis + " MiliSegundos" + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setTimes(int [] aux) {
		hora = aux[0];
		min = aux [1];
		seg = aux[2];
		milis = aux[3];
	}
	
	public void setCountN(int p, int id) {
		switch(id) {
		case 1:
			produc_final_n1 = p;
			break;
		case 2:
			produc_final_n2 = p;
			break;
		}
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
