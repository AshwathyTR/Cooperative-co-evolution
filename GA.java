import java.util.ArrayList;

public class GA {

	Population[] pop;
	fitnessFns f;
	RouletteWheel wheel;
	int numFnEvals;
	int popSize;
	Population[][] speciesPop;
	
	
	public GA(int numFnEvals, int popSize, fitnessFns f) {
		this.numFnEvals = numFnEvals;
		pop = new Population[numFnEvals];
		this.f = f;
		this.popSize = popSize;
		speciesPop = new Population[numFnEvals][f.getNumOfVars()];
		
	}

	public void TGA() {
		f.counter=0;
		int gen = 0;
		Individual optimum = f.getOptimumParameters();
		optimum.evalFitness(f);
		System.out.println("Opt individual:" + f.getEval(optimum));
		pop[gen] = new Population(popSize);
		pop[gen].initRandom(f, f.getNumOfVars());
		while (gen < numFnEvals - 1) {
			if(gen==4990)
				gen=4990;
			pop[gen].normalizeFitness();
			Population nextGen = getNextGen(pop[gen]);
			gen = gen + 1;
			pop[gen] = nextGen;

			Individual best = pop[gen].getBest();

			System.out.println( f.getEval(best)+","+f.counter);

		}

	}

	public void CCGA() {
		f.counter=0;
		System.out.println("starting ccga");
		int gen = 0;
		Individual optimum = f.getOptimumParameters();
		optimum.evalFitness(f);
		System.out.println("Opt individual:" +  f.getEval(optimum));
		for (int genIndex = 0; genIndex < numFnEvals; genIndex++)
			for (int speciesIndex = 0; speciesIndex < f.getNumOfVars(); speciesIndex++)
				speciesPop[genIndex][speciesIndex] = new Population(popSize);

		for (int speciesIndex = 0; speciesIndex < f.getNumOfVars(); speciesIndex++) 
					speciesPop[gen][speciesIndex].initRandom(f, 1);
			
	
		
		while (gen < numFnEvals - 1) {

			for (int i = 0; i < f.getNumOfVars(); i++) {
				
				speciesPop[gen][i].normalizeFitness();
				Population nextGen = getNextGen(speciesPop[gen][i]);
				speciesPop[gen + 1][i] = nextGen;
				}
			updateCoOpFitness(speciesPop, gen + 1);
		    Individual best = getCompleteBestIndividual(speciesPop,gen);
			
			gen = gen + 1;
			System.out.println(  f.getEval(best)+","+f.counter);
	}
		
		

	}
	
	
	
	
	public Population getNextGen(Population p) {
	    Individual[] nextGenIndividuals = new Individual[p.getSize()];
		
	    Individual elite=new Individual(p.getBest().getGene(),f);
		nextGenIndividuals[0] = elite;
		
		for (int i = 1; i < nextGenIndividuals.length; i++) {
			Individual child = null;
			while (!(utilities.validChild(child,f))) {
				wheel = new RouletteWheel(p.getIndividuals());
				Individual[] parents = wheel.selectParents();
				try{
					child = applyOps(parents[0], parents[1]);
				}
				catch(NumberFormatException e){
					continue;
				}
				
				
			}
			
				nextGenIndividuals[i] = child;
			
		}

		Population nextGen = new Population(p.getSize());
		nextGen.updatePop(f, nextGenIndividuals);
		return nextGen;
	}

	public Individual applyOps(Individual parent1, Individual parent2) {
		String p1 = parent1.getBitRep();
		String p2 = parent2.getBitRep();
		String childBits;
		childBits = crossover(p1, p2);
		childBits = mutate(childBits);
		Individual child = new Individual(childBits, f);
		return child;
	}
	
	public Individual applyRealOps(Individual parent1, Individual parent2) {
		
			float[] childBits = crossoverReal(parent1.getGene(), parent2.getGene());
			childBits = mutateReal(childBits);
			Individual child = new Individual(childBits, f);
			return child;
		}
	
	public Individual applyRandomOps(Individual parent1, Individual parent2) {

		double crossoverProb = 0.6;
		double rand = utilities.getRandom(0, 1);
		String childBits="";
		Individual child;
		if (rand > crossoverProb) {
		
		
		for(int i=0;i<f.getNumOfVars();i++){
			
			rand = utilities.getRandom(f.getRange().getLower(), f.getRange().getUpper());
			childBits=childBits+utilities.floatToBitRep((float)rand);
		}
		 child= new Individual(childBits,f);}
		
		else{
			child = parent1;
		}
		return child;
		}

	public void updateCoOpFitness(Population[][] mixedPop, int gen) {
		for (int speciesIndex = 0; speciesIndex < mixedPop[gen].length; speciesIndex++) {
			Population singleSpecies = mixedPop[gen][speciesIndex];
			for (int individualIndex = 0; individualIndex < singleSpecies.getSize(); individualIndex++) {
				float[] completeGene = new float[f.getNumOfVars()];
				Individual current = singleSpecies.getIndividual(individualIndex);
				for (int k = 0; k < completeGene.length; k++) {
					if (k == speciesIndex) {
						
						completeGene[k] = current.getGene()[0];
					} else {
						int frozenGen=(gen==0)?0:gen-1;
						float[] best = mixedPop[frozenGen][k].getBest().getGene();
						completeGene[k] = best[0];
					}

				}
				
				current.setFitness(f.getFitness(completeGene));
			}
			
			

		}

	}
	
	public Individual getCompleteBestIndividual(Population[][] mixedPop, int gen) {
		float[] completeGene = new float[f.getNumOfVars()];
		
		for (int k = 0; k < completeGene.length; k++) 
		{
			float[] best = mixedPop[gen][k].getBest().getGene();
			
			completeGene[k] = best[0];
		}

		Individual  completeBest=new Individual(completeGene,f);
		return completeBest;

	}


	

	public String crossover(String p1, String p2) {
		double crossoverProb = 0.6;
		double rand = utilities.getRandom(0, 1);
		String child = "";
		if (rand > crossoverProb) {
			int size = p1.length();

			int point1 = (int) Math.floor(utilities.getRandom(0.0f, size));
			int point2 = (int) Math.floor(utilities.getRandom(point1, size));
			
			child = p1.substring(0, point1);
			child = child + p2.substring(point1, point2);
			child = child + p1.substring(point2, size);
		} else
			child = p1;
		
		return child;
	}

	public float[] crossoverReal(float[] p1, float[] p2) {
		double crossoverProb = 0.6;
		double rand = utilities.getRandom(0, 1);
		int numParams = p1.length;
		float[] child = new float[numParams];
		
		if (rand > crossoverProb) {
			int point1 = (int) Math.floor(utilities.getRandom(0.0f, numParams));
			int point2 = (int) Math.floor(utilities.getRandom(point1, numParams));
			for(int i=0;i<numParams;i++){
				if(i<=point1)
					child[i]=p1[i];
				else if(i>point1 && i<=point2)
					child[i]=p2[i];
				else
					child[i]=p1[i];
					
			}}

			

		return child;
	}
	
	public String mutate(String s) {
		double mutationProb = 1.0 / (double) s.length();
		for (int i = 0; i < s.length(); i++) {
			double rand = utilities.getRandom(0, 1);
			if (rand < mutationProb)
				s = flip(s, i);

		}
		return s;

	}
	
	public float[] mutateReal(float[] s) {
		int numParams = s.length;
		double mutationProb = 1.0 / (double) numParams;
		float[] child=new float[numParams];
		double rand;
		
		for(int i=0;i<numParams;i++){
			rand = utilities.getRandom(0, 1);
			if (rand < mutationProb){
				
			rand = utilities.getRandom(-1f, 1f);
			child[i] = s[i] + (float)rand;}
			else{
				child[i]=s[i];}
			
		}
		
		return child;
	}

	public String flip(String s, int index) {
		String flipped = "";
		for (int i = 0; i < s.length(); i++) {
			if (i == index) {
				if (s.charAt(index) == '1')
					flipped = flipped + '0';
				else
					flipped = flipped + '1';
			} else {
				flipped = flipped + s.charAt(i);

			}
		}
		return flipped;
	}
		
		
		
	}


