import java.util.Arrays;


public class SA{
	
	int numFnEvals;
	fitnessFns f;
	double initT;
	double T;
	float[] best;
	int popSize;
	
	public SA(int numFnEvals,int popSize, fitnessFns f) {
		//System.out.println("Making Ga object");
		this.numFnEvals = numFnEvals;
		this.f = f;
		initT=50;
		this.popSize=popSize;
	}
	
	public void TSA(){
		int counter=0;
		f.counter=0;
		T=initT;
		int numParams = f.getNumOfVars();
		float[] gene = initRandom(numParams);
		while(counter<numFnEvals){
			gene = getNext(gene);
			T=T- (initT/((double)numFnEvals));
			counter++;
			
		}
		System.out.println(f.getEval(gene)+","+f.counter);
	}
	public float[] getNext(float[] gene){
		float[] next = mutate(gene);
		double prob = getProb(f.getEval(gene),f.getEval(next));
		double rand = utilities.getRandom(0, 1);
		if(prob>=rand)
			gene=next;
		return gene;
	}
	
	public float[] getNextOne(float[] gene, int index){
		float[] next = mutateOne(gene, index);
		double prob = getProb(f.getEval(gene),f.getEval(next));
		double rand = utilities.getRandom(0, 1);
		if(prob>=rand)
			gene=next;
		return gene;
	}
	
	public void CSA(){
		f.counter=0;
		int counter=0;
		double T=initT;
		int numParams = f.getNumOfVars();
		
		float[] gene = initRandom(numParams);
		best = new float[gene.length];
		best= Arrays.copyOf(gene, gene.length);
		
		for(int i=0;i<gene.length;i++){
		while(counter <( numFnEvals/gene.length)){
			gene = getNextOne(gene,i);
			if(f.getEval(gene)<=f.getEval(best))
				best[i]=gene[i];
			T=T-(initT/( numFnEvals/gene.length));
			counter++;
			
		}
		counter=0;
		T=initT;
		}
		System.out.println(f.getEval(best)+","+f.counter);
	}
	
	public void CSA2(){
		f.counter=0;
		int counter=0;
		int innercounter=0;
		double T=initT;
		int numParams = f.getNumOfVars();
		
		float[] gene = initRandom(numParams);
		best = new float[gene.length];
		best= Arrays.copyOf(gene, gene.length);
		
		while(counter<numFnEvals){
		for(int i=0;i<gene.length;i++){
		while(innercounter <( popSize)){
			gene = getNextOne(gene,i);
			if(f.getEval(gene)<=f.getEval(best))
				best[i]=gene[i];
			T=T-(initT/( numFnEvals/gene.length));
			counter++;
			innercounter++;
		}
		innercounter=0;
		T=initT;
		}}
		System.out.println(f.getEval(best)+","+f.counter);
	}
	
	public double getProb(double f1,double f2){
		if(f2<=f1)
			return 1;
		else
			return Math.exp((f1-f2)/T);
	}
	
	public float[] mutate(float[] s){
		int numParams = s.length;
		double mutationProb = 1.0 / (double) numParams;
		float[] child=new float[numParams];
		double rand;
		
		for(int i=0;i<numParams;i++){
			rand = utilities.getRandom(0, 1);
			if (rand < mutationProb){
				//System.out.print("mutating  "+i);
			//rand = utilities.getRandom(f.getRange().getLower(), f.getRange().getUpper());
			//child[i]=(float)rand;}
				float newVal=f.getRange().getUpper()+1;
				while(!f.getRange().inRange(newVal)){
					rand = utilities.getRandom(f.getRange().getLower(),f.getRange().getUpper());
					
			newVal = (float)rand;
			}
				child[i]=newVal;
				}
			else{
				child[i]=s[i];}
			
		}
		
		return child;
		
	}
	
	public float[] mutateOne(float[] s, int index){
		int numParams = s.length;
		float[] child=new float[numParams];
		double rand;
		for(int i=0;i<numParams;i++){
			if (i==index){
				//System.out.print("mutating  "+i);
			//rand = utilities.getRandom(f.getRange().getLower(), f.getRange().getUpper());
			//child[i]=(float)rand;}
				float newVal=f.getRange().getUpper()+1;
				while(!f.getRange().inRange(newVal)){
				 rand = utilities.getRandom(f.getRange().getLower(),f.getRange().getUpper());
			newVal = (float)rand;
			}
				child[i]=newVal;
				}
			else{
				child[i]=best[i];}
			
		}
		
		return child;
		
	}
	public float[] initRandom(int numParams){
		float[] gene= new float[numParams];

		for(int i=0;i<numParams;i++){
			float rand = utilities.getRandom(f.getRange().getLower(),f.getRange().getUpper());
		    gene[i]=rand;
		
		}
		return gene;
	}
}