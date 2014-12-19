package answer;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	private static int START_WORKING_TIME=9,WORKING_DURATION=8;
	private static double MAKE_ONE_COFFEE_TIME=2.5;
	
	public static void main(String[] args) throws IOException {
		int numberOfEngineers;
		float chanceAnEngineerCanBeSuperBusy,timeStayBusy;
		
		//The user starts typing inputs
		Scanner in = new Scanner(System.in);
		numberOfEngineers=(int) inputScanner(in,"Please enter a valid (integer) number of engineers:",false);
		chanceAnEngineerCanBeSuperBusy=(Float) inputScanner(in,"Please enter the chance (from 0.0 to 1.0) that an engineer becomes super-busy in some unit of time:",true);
		timeStayBusy=(Float) inputScanner(in,"Please enter how long they stay super-busy: (in minutes)",true);

		//Start running the simulator, plus calculate its executed time (including time needed to display on the console)
		long startTime = System.nanoTime();
		
		//Use this when the time of making a cup of coffee doesn't matter
		//new CoffeeMachineSimulator(START_WORKING_TIME,WORKING_DURATION).runCoffeeMachineSimulator(numberOfEngineers,chanceAnEngineerCanBeSuperBusy,timeStayBusy);
		//Use this when the time of making a cup of coffee is taken into account
		new CoffeeMachineSimulator(START_WORKING_TIME,WORKING_DURATION,MAKE_ONE_COFFEE_TIME).runCoffeeMachineSimulator(numberOfEngineers,chanceAnEngineerCanBeSuperBusy,timeStayBusy);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("Execution time: "+duration/1000000+" milliseconds");
	}


	public static Object inputScanner(Scanner in,String inputMessage,boolean isReturnTypeFloat){
		if(isReturnTypeFloat){
			Float resultFloat;
			do{
				System.out.println(inputMessage);
			    while(!in.hasNextFloat()){
			        System.out.println(inputMessage);in.next(); 
			    }
			    resultFloat = in.nextFloat();
			} while(resultFloat < 0);
			return resultFloat;
		}else{
			int resultInt;
			do{
				System.out.println(inputMessage);
			    while(!in.hasNextInt()){
			        System.out.println(inputMessage);in.next(); 
			    }
			    resultInt = in.nextInt();
			} while(resultInt < 0);
			return resultInt;
		}
	}
	

}