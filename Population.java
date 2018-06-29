public class Population {

	private int size;
	Individual[] pop;

	public Population(int size) {
		this.size = size;
		pop = new Individual[size];
	}
	
	public Population(Individual[] pop) {
		
		this.pop = pop;
		size=pop.length;
	}

	public void initRandom(fitnessFns f, int numOfVars) {
        pop=new Individual[size];
		for (int i = 0; i < size; i++) {
			pop[i]=new Individual(f,numOfVars);
			
		}
		

	}
	

	public Individual[] getIndividuals() {
		return pop;
	}

	public int getSize() {
		return size;
	}

	public Individual getIndividual(int id) {
		return pop[id];
	}
	
	public Individual setIndividual(Individual in, int id) {
		return pop[id]=in;
	}

	/*public Individual getBest() {
		double maxFitness = - (Double.MAX_VALUE);
		Individual best = null;

		for (int i = 0; i < size; i++) {
			double fitness=pop[i].getFitness();
			if (fitness > maxFitness) {
				best = pop[i];
				maxFitness = pop[i].getFitness();
			}
		}
		return best;
	}
	
	public int getWorstIndex() {
		double minFitness = Double.MAX_VALUE;
		int index = -1;
        double fitness=0;
		for (int i = 0; i < size; i++) {
			fitness=pop[i].getFitness();
			if (fitness < minFitness) {
				index = i;
				minFitness = pop[i].getFitness();
			}
		}
		return index;
	}*/
	
	public Individual getBest() {
		double minFitness = (Double.MAX_VALUE);
		Individual best = null;

		for (int i = 0; i < size; i++) {
			double fitness=pop[i].getFitness();
			
			if (fitness < minFitness) {
				best = pop[i];
				minFitness = pop[i].getFitness();
			}
		}
		return best;
	}
	
	public int getWorstIndex() {
		double minFitness = - (Double.MAX_VALUE);
		int index = -1;
        double fitness=0;
		for (int i = 0; i < size; i++) {
			fitness=pop[i].getFitness();
			if (fitness > minFitness) {
				index = i;
				minFitness = pop[i].getFitness();
			}
		}
		return index;
	}

	public void updateFitness(fitnessFns f) {
		for (int i = 0; i < size; i++)
			pop[i].evalFitness(f);
	}

	public void updatePop(fitnessFns f, Individual[] pop) {
		this.pop=pop;
		size=pop.length;
		updateFitness(f);
	}
	
	public void normalizeFitness(){
		double lowerBound=1;
		double upperBound=100;
		double minFit=getBest().getFitness();
		double correctLower=lowerBound-minFit;
		for(int i=0;i<size;i++)
			{
			Individual current=getIndividual(i);
			double fitness=current.getFitness();
			double normalized= fitness+correctLower;
			current.setNormalizedFitness(normalized);
			}
		double maxFit=getIndividual(getWorstIndex()).getFitness();
		double correctUpper=upperBound/(maxFit);
		for(int i=0;i<size;i++)
		{
		Individual current=getIndividual(i);
		double fitness=current.getFitness();
		double normalized= fitness*correctUpper;
		current.setNormalizedFitness(normalized);
		}
		
		
	}
	
	
}