import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class logInvariantes {

	private FileWriter logInv;
	private String sequence;
	private String[] trans_inv1;
	private String[] trans_inv2;
	private String[] trans_inv3;
	private String[] trans_inv4;
	private String[] trans_inv5;
	private	String [] trans5;
	private	String [] trans9;
	private ArrayList <Integer[]> plazas_inv;
	private boolean [] p_inv;
	private boolean [] t_inv;
	private int count_n1, count_n2, count_g, count_a1, count_a2, countTrans, count_v1, count_v2;
	
public logInvariantes() {
		
		try {
			logInv = new FileWriter("C:/Users/usuario/Desktop/logInvTP3.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sequence = "";
		count_g = 0;
		count_n1 = 0;
		count_n2 = 0;
		count_a1 = 0;
		count_a2 = 0;
		count_v1 = 0;
		count_v2 = 0;
		countTrans = 0;
		trans_inv1 = new String[] {"4","6","7"};
		trans_inv2 = new String[] {"8","10","12"};
		trans_inv3 = new String[] {"2","3"};
		trans_inv4 = new String[] {"13","11"};
		trans_inv5 = new String[] {"0","1", "14"};
		trans5 = new String[] {"5"};
		trans9 = new String[] {"9"};
		plazas_inv = new ArrayList<Integer[]>();
		plazas_inv.add(new Integer [] {0,1});        //Plazas 0 y 1 del generador
		plazas_inv.add(new Integer [] {11,12,14});   //Plazas 5, 6 y 8 del CPU 1
		plazas_inv.add(new Integer [] {3,4,15});	 //Plazas 9, 11 y 12 del CPU 2
		plazas_inv.add(new Integer [] {9,10});		 //Plazas 3 y 4 del Nucleo 1
		plazas_inv.add(new Integer [] {6,7});        //Plazas 14 y 15 del Nucleo 2
		p_inv = new boolean [] {true,true,true,true,true};
		t_inv = new boolean [7];

	}
	
public void writeTs(int ts) {
	sequence = sequence.concat("T" + ts);
	countTrans++;
}

public void close() {
	try {
		revision();
		
		logInv.write("\r\n");
		logInv.write("Invariantes de plazas 0, 1: " + p_inv[0] + "\r\n");
		logInv.write("Invariantes de plazas 5, 6, 8: " + p_inv[1] + "\r\n");
		logInv.write("Invariantes de plazas 9, 11, 12: " + p_inv[2] + "\r\n");
		logInv.write("Invariantes de plazas 3, 4: " + p_inv[3] + "\r\n");
		logInv.write("Invariantes de plazas 14, 15: " + p_inv[4] + "\r\n");
		logInv.write("\r\n");
		
		logInv.write("Invariantes de transicion 4, 6, 7: " + t_inv[0] + "\r\n");
		logInv.write("Invariantes de transicion 8, 10, 12: " + t_inv[1] + "\r\n");
		logInv.write("Invariantes de transicion 2, 3: " + t_inv[2] + "\r\n");
		logInv.write("Invariantes de transicion 13, 11: " + t_inv[3] + "\r\n");
		logInv.write("Invariantes de transicion 0, 1, 14: " + t_inv[4] + "\r\n");
		logInv.write("\r\n");

		logInv.write("Tareas Generadas: " + count_g + "\r\n");
		logInv.write("Tareas Producidos por n1: " + count_n1 + "\r\n");
		logInv.write("Tareas Producidos por n2: " + count_n2 + "\r\n");
		logInv.write("Encendidos a1: " + count_a1 + "\r\n");
		logInv.write("Encendidos a2: " + count_a2 + "\r\n");
		logInv.write("Tareas Recibidas v1: " + count_v1 + "\r\n");
		logInv.write("Tareas Recibidas v2: " + count_v2 + "\r\n");
		logInv.write("\r\n");

		logInv.write("Numero total de disparos: " + countTrans +"\r\n");
		logInv.write("\r\n");

		logInv.write("Disparos por Tareas Generadas:          " + String.format("%5d", count_g) + " (Tareas) x 2 (Transiciones) = "+ count_g * 2 + "\r\n");
		logInv.write("Disparos por Tareas Producidos por n1:  " + String.format("%5d", count_n1) + " (Tareas) x 2 (Transiciones) = "+ count_n1 * 2 + "\r\n");
		logInv.write("Disparos por Tareas Producidos por n2:  " + String.format("%5d", count_n2) + " (Tareas) x 2 (Transiciones) = "+ count_n2 * 2 + "\r\n");
		logInv.write("Disparos por Encendidos CPU1:           " + String.format("%5d", count_a1) + " (Tareas) x 3 (Transiciones) = "+ count_a1 * 3 + "\r\n");
		logInv.write("Disparos por Encendidos CPU2:           " + String.format("%5d", count_a2) + " (Tareas) x 3 (Transiciones) = "+ count_a2 * 3 + "\r\n");
		logInv.write("Disparos por Tareas Recibidas v1:       " + String.format("%5d", count_v1) + " (Tareas) x 1 (Transiciones) = "+ count_v1 + "\r\n");
		logInv.write("Disparos por Tareas Recibidas v2:       " + String.format("%5d", count_v2) + " (Tareas) x 1 (Transiciones) = "+ count_v2 + "\r\n");
		logInv.write(String.format("%80s", "----") + "\r\n");
		logInv.write("Suma Total:" + String.format("%69d", (count_g * 2 + count_n1 * 2 + count_n2 * 2 + count_a1 * 3 + count_a2 * 3 + count_v1 + count_v2)) +"\r\n");

		logInv.close();
		System.out.println("Logs finalizados");
	} catch (IOException e) {
		e.printStackTrace();
	}
}

public void revision(){
	String[] seque = sequence.split("T");
	ArrayList <String> seque_list = listador(seque);        //v2	
	count_a1 = cumplirInvTrans(seque_list, trans_inv1, 1);     //v2
	count_a2 = cumplirInvTrans(seque_list, trans_inv2, 2);     //v2
	count_n1 = cumplirInvTrans(seque_list, trans_inv3, 3);     //v2
	count_n2 = cumplirInvTrans(seque_list, trans_inv4, 4);	 //v2
	//count_g1 = cumplirInvTrans(seque_list, trans_inv5_1, 5);   //v2
	//count_g2 = cumplirInvTrans(seque_list, trans_inv5_2, 5);  //v2
	count_g = cumplirInvTransConflict(seque_list, trans_inv5, 5);  //v2
	
	count_v1 = cumplirInvTrans(seque_list, trans5, 6);
	count_v2 = cumplirInvTrans(seque_list, trans9, 7);
}

private int cumplirInvTrans(ArrayList<String> seque, String[] trans, int inv) { //v2
	
	ArrayList<String> sequeInv = subListador(seque, trans);
	int count = 0;
	boolean cond = true;
	boolean key = true;
	t_inv[inv-1] = true;
	
	while(cond && !sequeInv.isEmpty()) {
		for(int i=0;i<trans.length;i++) {
			if(!sequeInv.get(i).equals(trans[i])) {
				key = false;
				break;
			}
		}
		if(key) {
			for(int i=0;i<trans.length;i++) {
				sequeInv.remove(0);
		} 
		} else {
			cond = false;
		}
		count++;
	}		
	try {
		logInv.write("Secuencia: "+ seque + "\r\n");
		logInv.write("Resto Invariante: " + sequeInv + "\r\n");
		logInv.write("\r\n");
	} catch (IOException e) {
		System.err.println(e.getMessage());
	}
	if(sequeInv.isEmpty()) {
		t_inv[inv-1] = true;
	} else {
		t_inv[inv-1] = false;
	}
//	if (count > 0) {
//		count--;
//	}
	return count;
}

private int cumplirInvTransConflict(ArrayList<String> seque, String[] trans, int inv) { //v2
	
	ArrayList<String> sequeInv = subListador(seque, trans);
	int count = 0;
	boolean cond = true;
	boolean key = true;
	t_inv[inv-1] = true;
	
	while(cond && !sequeInv.isEmpty()) {
		for(int i=0;i<2;i++) {
			switch(i) {
			case 0:
				if(!sequeInv.get(i).equals(trans[i])) {
					key = false;
				}
				break;
			case 1:
				if(!(sequeInv.get(i).equals(trans[i]) || sequeInv.get(i).equals(trans[i+1]))) {
					key = false;
				}
				break;
			}
		}
		if(key) {
			for(int i=0;i<2;i++) {
				sequeInv.remove(0);
		} 
		} else {
			cond = false;
		}
		count++;
	}		
	try {
		logInv.write("Secuencia: "+ seque + "\r\n");
		logInv.write("Resto Invariante: " + sequeInv + "\r\n");
		logInv.write("\r\n");
	} catch (IOException e) {
		System.err.println(e.getMessage());
	}
	if(sequeInv.isEmpty()) {
		t_inv[inv-1] = true;
	} else {
		t_inv[inv-1] = false;
	}
//	if (count > 0) {
//		count--;
//	}
	return count;
}

private ArrayList<String> listador(String [] seque){ //v2
	ArrayList <String> seque_list = new ArrayList<String>();
	for(int i = 0;i<seque.length;i++) {
		seque_list.add(seque[i]);
	}
	return seque_list;
}

private ArrayList<String> subListador(ArrayList<String> seque, String [] inv){
	
	ArrayList<String> seque2 = new ArrayList<String>(); 
	boolean cond = false;
	int j = 0;
	while(j<seque.size()) {
	for(int i = 0;i < inv.length;i++) {
		cond = false;
		if(seque.get(j).equals(inv[i])) {
			seque2.add(seque.get(j));
			seque.remove(j);
			cond = true;
			break;
		}
	}
		if(!cond) {
			j++;
		}
	}
	return seque2;
}

public void cumplirInvPlazas(int [] vectorM) {
	
	int tokens = 0;	
	for(Integer [] a : plazas_inv) {
		tokens = 0;
		for(int i = 0;i<a.length;i++) {
				tokens += vectorM[a[i]];
			}
		if(tokens != 1) {
			p_inv [plazas_inv.indexOf(a)] = false;
		}
	}

}

}
