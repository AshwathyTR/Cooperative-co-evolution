public class Griewangk extends fitnessFns {

	private int numOfVars;
	Range rangeOfVars;
	private Individual optimum;

	private Optimum type;
	String name = "Griewangk";

	public Griewangk() {
		numOfVars = 10;
		rangeOfVars = new Range(-60f, 60f);
		optimum = new Individual(fillValue(new float[numOfVars]));
		type = Optimum.MIN;
		counter = 0;
	}

	private float[] fillValue(float[] a) {
		for (int i = 0; i < a.length; i++)
			a[i] = 0;
		return a;
	}

	public String getName() {
		return name;
	}

	public double Fn(float[] parameters) {
		counter++;
		int n = parameters.length;
		double sum = 0;
		double prod = 1;
		for (int i = 0; i < n; i++) {
			sum = sum + (parameters[i] * parameters[i]);
			prod = prod * Math.cos(parameters[i] / Math.sqrt(i + 1));
		}
		double f = 1 + sum / 4000f - prod;
		/*
		 * if(!Double.isFinite(f)) f=0;
		 */
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
