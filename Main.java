public class Main {
	
	private final static int fin = 300;
	private final static int time = 2000; //tiempo del log
	
	private final static int time1 = 60; //tiempo del generador
	private final static int time2 = 90; //tiempo del nucleo 1
	private final static int time3 = 100; //tiempo del nucleo 2

	
	public static void main(String[] args) {
		
		logInvariantes logInv = new logInvariantes();
		RdP red = new RdP(logInv);
		Politica politica = new Politica(red, time2, time3);
		Monitor monitor = new Monitor(red,politica);
		
		logWriter log = new logWriter(time, time1, time2, time3, monitor, red, logInv);
		Thread logt = new Thread(log);
		Crono cron = new Crono(monitor, fin, log);
		Thread cront = new Thread(cron);
		
		Generador g = new Generador(monitor, fin, time1);
		Alimentador a1 = new Alimentador(monitor, fin, 4, 6, 7, 1, red);
		Alimentador a2 = new Alimentador(monitor, fin, 8, 10 ,12, 2, red); 
		Verificador v1 = new Verificador(monitor, fin, 5, 1, red);
		Verificador v2 = new Verificador(monitor, fin, 9, 2, red);
		Nucleo n1 = new Nucleo(monitor, time2, fin, 2, 3, 1, log, red, cron);
		Nucleo n2 = new Nucleo(monitor, time3, fin, 13, 11, 2, log, red, cron);
		
		logt.start();
		cront.start();
		
		g.start();
		a1.start();
		a2.start();
		v1.start();
		v2.start();
		n1.start();
		n2.start();
		
//		try {
//			logt.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("Estado de g: " + g.getState());
//		System.out.println("Estado de v1: " + v1.getState());
//		System.out.println("Estado de v2: " + v2.getState());
//		System.out.println("Estado de a1: " + a1.getState());
//		System.out.println("Estado de a2: " + a2.getState());
//		System.out.println("Estado de n1: " + n1.getState());
//		System.out.println("Estado de n2: " + n2.getState());
//		System.out.println("Estado de crono: " + cron.getState());
//		System.out.println("Estado de log: " + log.getState());
	}
}