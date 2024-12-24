import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class RdP {
	
	private int[][] matrizI;
	private int[][] matrizH;
	private int[][] matriz_I;
	private int[] vectorM;
	private boolean [] sensibilizadas;
	private logInvariantes logInv;

	public RdP(logInvariantes logInv) {
		matrizH = new int [16][15];
		matrizI = new int [16][15];
		matriz_I = new int [16][15];
		vectorM = new int [16];
		this.logInv = logInv;
		
		try {
			matrizI = matrixRead("C:/Users/usuario/Desktop/BlocsTP3/MatrizI.txt");
			matriz_I = matrixRead("C:/Users/usuario/Desktop/BlocsTP3/MatrizI-.txt");
			matrizH = matrixRead("C:/Users/usuario/Desktop/BlocsTP3/MatrizH.txt");
			vectorM = vectorRead("C:/Users/usuario/Desktop/BlocsTP3/VecMarcado.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sensibilizadas = new boolean[matrizI[0].length];
		actSensibilizadas ();
	}
	
	public int[][] matrixRead(String file) throws IOException{
		FileReader pnreader = null;
		BufferedReader matrix;
		String linea;
		int num = 0;
		int matriz[][] = new int[16][15];
		
		try {
			pnreader = new FileReader(file);
			matrix = new BufferedReader (pnreader);
			
			 while (((linea = matrix.readLine()) != null)) {

				 String a[]=linea.split("	");
		         
				 for (int l = 0; l < a.length; l++) {
		                matriz[num][l] = Integer.valueOf(a[l]);
		         }
		            num++;
		         }
			 pnreader.close();
			 matrix.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return matriz;	
		}
	
	public int[] vectorRead(String file) throws IOException{
		FileReader pnreader = null;
		BufferedReader matrix;
		String linea;
		int vector[] = new int[16];
		
		try {
			pnreader = new FileReader(file);
			matrix = new BufferedReader (pnreader);
			
			while (((linea = matrix.readLine()) != null)) {

				 String a[]=linea.split("	");
		         
				 for (int l = 0; l < a.length; l++) {
		                vector[l] = Integer.valueOf(a[l]);
		         }
		            
		         }
			 pnreader.close();
			 matrix.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return vector;	
		}
	
	public void actSensibilizadas () {
		for(int j=0;j<sensibilizadas.length;j++)
		{
			sensibilizadas[j] = true;
			
			for(int i=0;i<vectorM.length;i++)
			{	
				if((matriz_I [i][j] + vectorM[i]) < 0 )
				{
					sensibilizadas[j] = false;
					break;				
				}
				
				if(matrizH[i][j] == 1)
				{
					if(vectorM[i] - matrizH[i][j] < 0) {
						sensibilizadas[j] = true;						
					}
					else
					{
						sensibilizadas[j] = false;
						break;
					}
				}
			}		
		}
	}
	
	public void shoot(int f) {
		if(sensibilizadas[f]) {
			for(int i = 0;i<vectorM.length;i++) {
				vectorM [i] = vectorM [i] + matrizI[i][f];
			}
			logInv.writeTs(f);
			System.out.println("disparo: " + f +"");
			actSensibilizadas ();
		}
		logInv.cumplirInvPlazas(vectorM);
	}
			
	public boolean estaSensibilizada(int transicion) {
		return sensibilizadas[transicion];
	}
	
	public synchronized int getSizeB(int b) {
		int size = 0;
		switch(b){
		case 1:
			size = vectorM[8];
			break;
		case 2:
			size = vectorM[5];
			break;
		}
		return size;
	}
	
	public synchronized boolean getNoti(int id) {
		boolean notificacion = false;
		switch(id){
		case 1:
			if(vectorM[13] > 0) {
				notificacion= true;
			}
			break;
		case 2:
			if(vectorM[2] > 0) {
				notificacion= true;
			}
			break;
		}
		return notificacion;
	}
	
	public synchronized boolean getCPUState(int id) {
		boolean state = false;
		switch(id){
		case 1:
			if(vectorM[14] == 1) {
				state= true;
			}
			break;
		case 2:
			if(vectorM[4] == 1) {
				state= true;
			}
			break;
		}
		return state;
	}

	
	}