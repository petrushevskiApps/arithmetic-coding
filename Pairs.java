import java.math.BigDecimal;

public class Pairs {

	double low = 0;
	double high = 0;
	double prob = 0;
	char bukva;
	
	Pairs(double low, double prob, char bukva)
	{
		this.low = low;
		this.prob = prob;
		this.bukva = bukva;
	}
	private double calcHigh()
	{
		BigDecimal bd = new BigDecimal(Double.toString(prob));
		BigDecimal bd2 = new BigDecimal(Double.toString(low));
		return bd.add(bd2).doubleValue();
	}
	
	public double getLow() { return low; }
	public double getHigh() { return calcHigh();}
	public double getProb() { return prob;}
}
