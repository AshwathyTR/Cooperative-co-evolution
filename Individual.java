import java.util.Comparator;

public class Individual {

	private int size;
	private float[] gene;
	private double fitness;
	private double normalizedFitness;
	private String bitRepresentation;

	public Individual(float[] gene,fitnessFns f) {
		size = gene.length;
		this.gene = gene;
		evalBitRep(gene);
		evalFitness(f);
		normalizedFitness=0;
	}
	public Individual(fitnessFns f, int numOfVars) {
		initRandom(f,numOfVars);
		size = gene.length;
		evalBitRep(gene);
		evalFitness(f);
		normalizedFitness=0;
	}
	
	public Individual(float[] gene) {
		size = gene.length;
		this.gene = gene;
		evalBitRep(gene);
        fitness=0.0;
        normalizedFitness=0;
	}
	
	
	public Individual(float[] gene,double fitness) {
		size = gene.length;
		this.gene = gene;
		evalBitRep(gene);
		this.fitness=fitness;
		normalizedFitness=0;
	}
	
	public Individual(String geneBits,fitnessFns f) {
		this.bitRepresentation = geneBits;
		evalFloat(geneBits);
		size = gene.length;
		
		evalFitness(f);
		normalizedFitness=0;
	}

	public float[] getGene() {
		return gene;
	}
	
	public double getNormalizedFitness() {
		return normalizedFitness;
	}
	public void setNormalizedFitness(double val) {
		 normalizedFitness=val;
	}

	public String getBitRep() {
		return bitRepresentation;
	}

	public void evalBitRep(float[] values) {
		String rep = "";
		for (int i = 0; i < values.length; i++) {
			rep = rep + utilities.floatToBitRep(values[i]);
		}
		bitRepresentation = rep;
	}
	public void setAllele(float allele, int id) {
		gene[id]=allele;
	}

	public void evalFloat(String values) {
		int bitsPerVar=32;
		int numOfVars=values.length()/bitsPerVar;
		float[] rep = new float[numOfVars];
		int seeker=0;
		for (int i = 0; i < rep.length; i++) {
			String sub=values.substring(seeker,seeker+bitsPerVar);
			float a=utilities.bitRepToFloat(sub);
			rep[i]=a;
			seeker=seeker+bitsPerVar;
			
		}
		gene = rep;
	}
	
	
	public void initRandom(fitnessFns f, int size) {
		Range r = f.getRange();
		gene=new float[size];
		for (int i = 0; i < size; i++) {
			gene[i] = utilities.getRandom(r.getLower(), r.getUpper());

		}
		
	}

	public double getFitness() {
		return fitness;
	}

	public double getSize() {
		return size;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public void evalFitness(fitnessFns f) {
		fitness = f.getFitness(gene);
	}
	

}


