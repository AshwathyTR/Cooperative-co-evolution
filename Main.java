import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void setLog(){
		String s="log.txt";
		PrintWriter writer;
		try {
			writer = new PrintWriter(s, "UTF-8");
			writer.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			System.setOut(new PrintStream(new File(s)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		
		ArrayList<fitnessFns> fns = new ArrayList<fitnessFns>(Arrays.asList(new Rastrigin(), new Schwefel(), new Griewangk(), new Ackley()));
		setLog();
		for(int i=0;i<fns.size();i++){
			
			System.out.println("|||||||||| "+fns.get(i).getName()+"|||||||||||||");
			GA ga=new GA(1000,20,fns.get(i));
			System.out.println("########  TGA ##########");
			ga.TGA();
			ga=new GA(100,20,fns.get(i));
			System.out.println("########  CCGA ##########");
			ga.CCGA();
			
			System.out.println("########  TSA ##########");
			SA sa;
			for(int j=0;j<100000;j=j+1000){
				sa=new SA(j,20,fns.get(i));
				sa.TSA();
			}
			
			
			System.out.println("########  CSA ##########");
			for(int j=0;j<100000;j=j+1000){
				sa=new SA(j,20,fns.get(i));
				sa.CSA();
			}
			System.out.println("########  CSA2 ##########");
			for(int j=0;j<100000;j=j+1000){
				sa=new SA(j,20,fns.get(i));
				sa.CSA2();
			}
			System.out.println("########  CCGA ##########");
			
		}

		
		
		
	}

}
