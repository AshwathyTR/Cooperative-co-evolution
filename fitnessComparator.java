import java.util.Comparator;

public class fitnessComparator implements Comparator<Individual>{
	@Override
	public int compare(Individual i1, Individual i2) {
		
        return Double.compare(i2.getFitness(), i1.getFitness());
		
		
	}
}