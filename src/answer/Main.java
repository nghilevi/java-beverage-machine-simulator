package answer;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	private static int START_WORKING_TIME=9,WORKING_DURATION=8;
	private static double MAKE_ONE_CUP_OF_COFFEE_TIME=2.5;
	
	public static void main(String[] args) throws IOException {
		int numberOfEngineers;
		float chanceAnEngineerCanBeSuperBusy,timeStayBusy;
		CoffeeMachineSimulator coffeeMachineSimulator;
		Scanner in = new Scanner(System.in);
		
		//The user starts typing inputs via instructions
		numberOfEngineers=(int) inputScanner(in,"Please enter a valid (integer) number of engineers:",false);
		chanceAnEngineerCanBeSuperBusy=(float) inputScanner(in,"Please enter the chance (from 0.0 to 1.0) that an engineer becomes super-busy in some unit of time:",true);
		timeStayBusy=(float) inputScanner(in,"Please enter how long they stay super-busy: (in minutes)",true);

		//Start running the simulator
		//Use this when the time of making a cup of coffee is taken into consideration:
		coffeeMachineSimulator=new CoffeeMachineSimulator(START_WORKING_TIME,WORKING_DURATION,MAKE_ONE_CUP_OF_COFFEE_TIME);
		//Use this line below when the time of making a cup of coffee is NOT taken into consideration:
		//coffeeMachineSimulator=new CoffeeMachineSimulator(START_WORKING_TIME,WORKING_DURATION);
		
		coffeeMachineSimulator.runCoffeeMachineSimulator(numberOfEngineers,chanceAnEngineerCanBeSuperBusy,timeStayBusy);
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