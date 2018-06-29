import java.util.ArrayList;
import java.util.Random;

public class utilities{
	
	public static float getRandom(float min, float max){
		
		Random r=new Random();
		float value = min + (max - min) * r.nextFloat();
		return value;
		
	}
	
	public static void displaySpecies(Population[] speciesPop, int numOfVars){
		for (int speciesIndex = 0; speciesIndex < numOfVars; speciesIndex++) 
		{
		System.out.println("-- Species "+speciesIndex+"---");
		utilities.displayPop(speciesPop[speciesIndex]);
		}
	}
	public static void displayGene(Individual ind){
		for(int i=0;i<ind.getSize();i++)
			System.out.print("< "+ind.getGene()[i]+", ");
		System.out.print(" > ");
	}
	
	public static void displayGene(float[] ind){
		for(int i=0;i<ind.length;i++)
			System.out.print("< "+ind[i]+", ");
		System.out.print(" > ");
	}
	
	public static void displayPop(Population p){
		System.out.println("----- start pop ----- ");
		for(int i=0;i<p.getSize();i++)
			{
			Individual ind=p.getIndividual(i);
			displayGene(ind);
			System.out.println(" fitness: "+ind.getFitness());
			}
		System.out.println("----- end pop ----- ");
		
	}
	
	public static void displayWheel(ArrayList p){
		System.out.println("----- start wheel ----- ");
		for(int i=0;i<p.size();i++)
			{
			Individual ind=(Individual)p.get(i);
			displayGene(ind);
			System.out.println(" fitness: "+ind.getFitness());
			}
		System.out.println("----- end wheel ----- ");
		
	}
	
	public static boolean validChild(Individual child, fitnessFns f){
		if(child==null)
			return false;
		if(Double.isNaN(child.getFitness()))
				return false;
		if(!(Double.isFinite(child.getFitness())))
			
			return false;
			
		for(int i=0;i<child.getGene().length;i++){
			if (!(f.getRange().inRange(child.getGene()[i])))
				return false;
		}
		return true;
	}
	
	/*public static float bitRepToFloat(String s){
		long l = Long.parseLong(s,2);
		return Float.intBitsToFloat((int)l);
	}
	
	public static String floatToBitRep(float d){
		int s=Float.floatToRawIntBits(d);
		String r=Integer.toBinaryString(s);
		while(r.length()<32){
			r="0"+r;
		}
		return r;
	}*/
	
	public static float bitRepToFloat(String s){
		int l;
		
			 l = Integer.parseInt(s,2);
		
		return (((float)l)/10000f)-1000f ;
	}
	
	public static String floatToBitRep(float d){
		d=d+1000f;
		int s=(int)( d*10000f);
		String r=Integer.toBinaryString(s);
		while(r.length()<32){
			r="0"+r;
		}
		return r;
	}
}