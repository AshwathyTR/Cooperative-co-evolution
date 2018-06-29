
public abstract class fitnessFns {

	public static final int MAX=1;
	public static final int MIN=0;
	
	public int counter;
	
	enum Optimum{
		MAX,
		MIN
	}
	
	public abstract double  Fn(float[] parameters);
	public abstract Individual  getOptimumParameters();
	public abstract int getNumOfVars();
	public abstract Range getRange();
	public abstract Optimum getOptimumType();
	public abstract String getName();
	
	public double getFitness(Individual p){
		float[] parameters=p.getGene();
		double evaluation=Fn(parameters);
		double fitness=0.0;
		switch(getOptimumType()){
		case MAX:fitness=evaluation;
				 break;
		case MIN:fitness=1/(evaluation+1);
				 break;
		
		}
		return evaluation;	
		
	}
	
	public double getEval(Individual p){
		float[] parameters=p.getGene();
		double evaluation=Fn(parameters);
		
		return evaluation;	
		
	}
	
	public double getEval(float[] parameters){
		double evaluation=Fn(parameters);
		
		return evaluation;	
		
	}
	
	public double getFitness(float[] parameters){
		double evaluation=Fn(parameters);
		//System.out.println("evaluation is " + evaluation);
		double fitness=0.0;
		switch(getOptimumType()){
		case MAX:fitness=evaluation;
				 break;
		case MIN:fitness=(1/(evaluation+1)+1);//So there are no 0s or infinities
				 break;
		
		}
		return evaluation;	
		
	}
	
}
