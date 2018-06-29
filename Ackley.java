


public class Ackley extends fitnessFns {

	private int numOfVars;
Range rangeOfVars;
	private Individual optimum;
			
	private Optimum type;
	String name="Ackley";
	
	public Ackley(){
		numOfVars = 30;
		rangeOfVars = new Range(-30f, 30f);
		optimum = new Individual(
				fillValue(new float[numOfVars]));
		type = Optimum.MIN;
		counter=0;
	}
	
	private float[] fillValue(float[] a) {
		for (int i = 0; i < a.length; i++)
			a[i] = 0;
		return a;
	}

	public String getName(){
		return name;
	}
	public double Fn(float[] parameters) {
		counter++;
		float n = (float)parameters.length;
		double sum1 = 0;
		double sum2=0;
		for (int i = 0; i < n; i++){
			sum1 = sum1 + (parameters[i] * parameters[i]);
			sum2=sum2+Math.cos(2*Math.PI*parameters[i]);
		}
		double f = 20 -( 20 * Math.exp( -0.2 * Math.sqrt((1.0/n) *sum1))) +Math.E - Math.exp((1.0/n)*sum2);
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
