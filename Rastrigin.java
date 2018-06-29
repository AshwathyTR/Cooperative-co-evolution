public class Rastrigin extends fitnessFns {

	private int numOfVars;
	 Range rangeOfVars;
	private Individual optimum;
			
	private Optimum type;
	String name="Rastrigin";
	public Rastrigin(){
		
		numOfVars = 20;
		rangeOfVars = new Range(-5.12f, 5.12f);
		optimum = new Individual(
				fillZeroes(new float[numOfVars]));
		type = Optimum.MIN;
		counter=0;
	}
	
	private float[] fillZeroes(float[] a) {
		for (int i = 0; i < a.length; i++)
			a[i] = 0.0f;
		return a;
	}

	public String getName(){
		return name;
	}
	public double Fn(float[] parameters) {
		counter++;
		int n = parameters.length;
		double sum = 0;
		for (int i = 0; i < n; i++)
			//double a =sum;
			sum = sum + (parameters[i] * parameters[i])
					-  3.0*Math.cos(2 * Math.PI * parameters[i]);
			
		double f = 3.0 * n + sum;
		
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
