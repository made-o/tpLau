import java.util.ArrayList;
import java.util.Random;

public class Politica {
	private Random aleatorio;
	private RdP red;
	private int time_n1 ,time_n2;
	private int b;
	
	public Politica(RdP red, int time_n1, int time_n2) {
		this.red = red;
		aleatorio = new Random();
		this.time_n1 = time_n1;
		this.time_n2 = time_n2;
		b = 0;
	}
	
	public int elegir(ArrayList <Integer> sensibles) {
		if(sensibles.size() > 1)
		{
			int elec = aleatorio.nextInt(sensibles.size()-1);
			System.out.println(sensibles.get(elec));
			return sensibles.get(elec);
		}
		else {
			System.out.println(sensibles.get(0));
			return sensibles.get(0);
		}
		
	}
	
	public int queueBuffer() {
		if(time_n1 != time_n2) {
			if(red.getSizeB(1) < red.getSizeB(2))
			{
				return 14;
			}
			else {
				if(red.getSizeB(1) > red.getSizeB(2))
				{
					return 1;
				}
				else {
					if(time_n1 < time_n2)
					{
						return 14;
					}
					else {
						return 1;
					}
				}
			}
		}
		else {
			if(b%2 == 0) {
				b++;
				return 14;
			}
			else {
				b++;
				return 1;
			}
			
		}
		
	}
}
