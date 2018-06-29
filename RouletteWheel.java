import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;

public class RouletteWheel{
	
	Individual[] candidates;
	int size;
	ArrayList<Individual> corrected;
	
	public RouletteWheel(Individual[] candidates){
		
		this.candidates=candidates;
		size=candidates.length;
		corrected=new ArrayList<Individual>();
	}
	
	public ArrayList<Individual> correctWheel(){
		//System.out.println("Correcting wheel to be fitness proportionate");
		double totalFitness= 0;
		int repeatCount= 0;
		
			Arrays.sort( candidates,new fitnessComparator());
		
		for(int i=0;i<size;i++){
			double f=candidates[i].getNormalizedFitness();
			totalFitness=totalFitness+f;
			//System.out.println("Total fitness is "+totalFitness+" and f is "+f);
		}
		for(int i=0;i<size;i++){
			double fitnessProportion=candidates[i].getNormalizedFitness()/(double)totalFitness;
			repeatCount= (int) Math.ceil(fitnessProportion* size);
			for(int j=0;j<repeatCount;j++){
				corrected.add(candidates[i]);
				
			}
		}
	//	utilities.displayWheel(corrected);
		if(corrected.size()==0){
			int a=10;
		}
		return corrected;
		
	}
	
	public Individual[] selectParents(){
	//	System.out.println("Selecting parents");
		ArrayList<Individual> pool=correctWheel();
		int numParents=2;
		Individual[] parents=new Individual[numParents];
		
			if(pool.size()==0)
				pool=null;
		for(int i=0;i<numParents;i++){
			int rand=(int)Math.floor(utilities.getRandom(0, pool.size()));
			
				parents[i]=pool.get(rand);
		
			
			
		}
	//	System.out.println("Selected parents with normalized fitness"+ parents[0].getNormalizedFitness()+" and "+parents[1].getNormalizedFitness());
		return parents;
		
	}
}