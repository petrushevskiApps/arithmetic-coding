import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class encoder {

	static double high = 1;
	static double low = 0;
	static double high_new = 0;
	static double low_new = 0;
	static double[][] bukva_prob;
	static ArrayList<Pairs> pairs = new ArrayList<>();
	
	///MULTI
	/// 0.1 0.3 0.3 0.2 0.1
	
	public static void main(String[] args) 
	{

		low = 0.0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Vnesete tekst za kodiranje: ");
		String input = scanner.nextLine();
		char[] bukva = input.toCharArray(); 
		Double[] prob = new Double[bukva.length];

		int k=0;
		System.out.println("Vnesete gi verojatnostite na bukvite: ");
		do
		{
			Double inputDouble = scanner.nextDouble();
			prob[k++] = inputDouble;
		}while(k<bukva.length);
		
		scanner.close();
		
		/* Matrichna struktura za kombinacija Bukva - Verojatnos */
		bukva_prob = new double[bukva.length][bukva.length]; 
		
		for(int i=0; i<bukva.length; i++)
		{
			bukva_prob[0][i] = bukva[i];
			bukva_prob[1][i] = prob[i];
		}

		// SORT - Opagjachki redosled
		bubble_srt(bukva.length);

		// Proverka na Bubble Sort
		//for(int i=0; i<zbor.length; i++) System.out.println((char)zbor_prob[0][i] + " " + zbor_prob[1][i]);
		
		for(int i=0; i<bukva.length; i++)
		{
			pairs.add(new Pairs(low,bukva_prob[1][i],(char)bukva_prob[0][i]));
			low = pairs.get(i).getHigh();

		}
		
		high = 1;
		low  = 0;
		
		/* Ciklus Kodiranje */
		
		for(int i=0; i<bukva.length; i++)
		{
			int j=0;
			while(j<bukva.length)
			{
				if(bukva[i] == (char)bukva_prob[0][j])
				{
					encoding(pairs.get(j).getHigh(),pairs.get(j).getLow());
					break;
				}
				else j++;
			}

		}
		System.out.println(low + " " + high);

	}

	private static void encoding(double high_bukva, double low_bukva)   
	{
		
		double range = calcSub(high,low);
		high = calcSum(low,calcMult(range,high_bukva));
		low = calcSum(low,calcMult(range,low_bukva));
	}
	private static double calcSum(double a1, double a2)
	{
		BigDecimal bd = new BigDecimal(Double.toString(a1));
		BigDecimal bd2 = new BigDecimal(Double.toString(a2));
		return bd.add(bd2).doubleValue();
	}
	private static double calcSub(double a1, double a2)
	{
		BigDecimal bd = new BigDecimal(Double.toString(a1));
		BigDecimal bd2 = new BigDecimal(Double.toString(a2));
		return bd.subtract(bd2).doubleValue();
	}
	private static double calcMult(double a1, double a2)
	{
		BigDecimal bd = new BigDecimal(Double.toString(a1));
		BigDecimal bd2 = new BigDecimal(Double.toString(a2));
		return bd.multiply(bd2).doubleValue();
	}
	
	public static void bubble_srt(int length) 
	{
        int k;
        for (int m = length; m >= 0; m--) 
        {
            for (int i = 0; i < length - 1; i++) 
            {
                k = i + 1;
                if (bukva_prob[1][i] < bukva_prob[1][k]) 
                {
                	double temp,temp2;
                    temp = bukva_prob[0][i];
                    temp2 = bukva_prob[1][i];
                    bukva_prob[0][i] = bukva_prob[0][k];
                    bukva_prob[1][i] = bukva_prob[1][k];
                    bukva_prob[0][k] = temp;
                    bukva_prob[1][k] = temp2;
                }
            }
        }
    }
	
}

