

public class Schwefel extends fitnessFns {

	private int numOfVars;
 Range rangeOfVars;
	private Individual optimum;
			
	private Optimum type;
	String name="Schwefel";
	
	public Schwefel(){
		numOfVars = 10;
		rangeOfVars = new Range(-500f, 500f);
		optimum = new Individual(
				fillValue(new float[numOfVars]));
		type = Optimum.MIN;
		counter=0;
	}
	
	private float[] fillValue(float[] a) {
		for (int i = 0; i < a.length; i++)
			a[i] = 420.9687f;
		return a;
	}

	public String getName(){
		return name;
	}
	
	public double Fn(float[] parameters) {
		counter++;
		int n = parameters.length;
		double sum = 0;
		double prod=1;
		for (int i = 0; i < n; i++)
			sum = sum + (parameters[i] * Math.sin(Math.sqrt( Math.abs(parameters[i]))));
		double f = (418.9829 * n) - sum;
		
		return f;
	}

	public Individual getOptimumParameters() {
		return optimum;
	}

	public Optimum getOptimumType() {
		return type;
	}

	public int getNumOfVars() {
		return numOfVars;
	}

	public Range getRange() {
		return rangeOfVars;
	}

}
