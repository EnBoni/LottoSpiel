package Stand;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ZufalligerZahl {

	public static void main(String[] args) {
	}	
	public static int[] createCombination() {
		ArrayList numbs= new ArrayList<Integer>();
		int[] combi=new int[6];
		for(int i=0;i<6;i++) {
			
			Random r= new Random();
			int zufall = r.nextInt(48) + 1;
			
			boolean isthere=numbs.contains(zufall);
			
			if(isthere) {
				i--;
			}
			else {
				numbs.add(zufall);
				combi[i]=zufall;
			}
			;
			
		}
		System.out.println("\n");
		return combi;
	}

}
