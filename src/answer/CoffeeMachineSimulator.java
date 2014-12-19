package answer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CoffeeMachineSimulator {
	private int	startWorkingTime,endWorkingTime;
	private double makeOneCupOfCoffeeTime;
	JointQueueTimeComparator jointQueueTimeComparator;
	BusyStatusComparator busyStatusComparator;
	
	CoffeeMachineSimulator(int startWorkingTime, int workingDuration){
		this.startWorkingTime=startWorkingTime;
		this.endWorkingTime=startWorkingTime+workingDuration;
	}
	
	CoffeeMachineSimulator(int startWorkingTime, int workingDuration,double makeOneCupOfCoffeeTime){
		this.startWorkingTime=startWorkingTime;
		this.endWorkingTime=startWorkingTime+workingDuration;
		this.makeOneCupOfCoffeeTime=makeOneCupOfCoffeeTime;
	}
	
	public void runCoffeeMachineSimulator (int numberOfEngineers,Float chanceAnEngineerCanBeSuperBusy,Float timeStayBusy){
		long startTime = System.nanoTime();
		jointQueueTimeComparator=new JointQueueTimeComparator();
		busyStatusComparator=new BusyStatusComparator(makeOneCupOfCoffeeTime);
		
		System.out.println("\nA sample activity log of coffee machine in a working day starting from "+startWorkingTime+":00 to "+endWorkingTime+":00\n");
		
		//Initialize engineerList
		Engineer.timeStaySuperBusy= timeStayBusy;
		Engineer.chanceAnEngineerCanBeSuperBusy= chanceAnEngineerCanBeSuperBusy;
	    ArrayList<Engineer> engineerList = new ArrayList<Engineer>();
	    for(int i=0;i<numberOfEngineers;i++){
	    	 engineerList.add(new Engineer(i+1,BusyStatus.NON_SUPER_BUSY_STATUS.getWorkingStatus())); //Every engineer is non super busy at first
	    }
	    
	    //Display the table on the console
	    int startedWorkTime=startWorkingTime, 
	    		endedWorkTime=endWorkingTime;
		for(int i=startedWorkTime;i<endedWorkTime;i++){
	    	System.out.println("\nFrom "+i+":00 to "+(i+1)+":00");
	    	displayActivitiesInOneWorkingHour(i,engineerList);
	    	System.out.println("===================================================================================================================================================================");
	    }
		
		//Print out execution time (including time needed to display on the console)
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("Execution time: "+duration/1000000+" milliseconds");
	}

	private void displayActivitiesInOneWorkingHour(int currentHour,ArrayList<Engineer> engineerList){    
		//Update working status every one hour
		Engineer engineer;
		String jointQueueTime;
		int engineerListSize=engineerList.size();
		for (int i = 0; i < engineerListSize; i++){
			jointQueueTime =TimeUtils.generateTimeBasedOnHour(currentHour);
			engineer = engineerList.get(i);
			engineer.setEnterQueueTime(jointQueueTime);
			engineer.updateBusyStatus(currentHour);
			engineerList.set(i, engineer);
		}	
	    printOutTableOnConsoleWindow(engineerList);
	}

	private void printOutTableOnConsoleWindow(ArrayList<Engineer> engineerList){
		Object[][] table = new String[engineerList.size()][8];
	    
	   	//Sort the queue list based on the time an engineer joints the queue to create a first-come-first-server queue
	   	Collections.sort(engineerList, jointQueueTimeComparator);
	    table= populateDataIntoTable(engineerList,table,false);

	    //Sort the list based on the busy status
	    Collections.sort(engineerList, busyStatusComparator);
	    table=populateDataIntoTable(engineerList,table,true);
	    
	    //Display the table in columns
	    String columnsFormat="%-20.20s %-20.20s %-20.20s %-20.20s %-20.20s %-20.20s %-20.20s %-20.20s %n";
	    System.out.printf(columnsFormat, "","","NORMAL QUEUE", "","", "| ","PRIORITIZED QUEUE",""); 
	    System.out.printf(columnsFormat, "Engineer-Id","Start-Super-Busy-At","End-Super-Busy-At", "Enter-Queue-At","Status-On-Queue", "| Engineer-Id","Status-On-Queue","Enter-Queue-At"); 
	    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	    for (final Object[] row : table) {
	    	System.out.printf(columnsFormat, row[0], row[1],row[2], row[3],row[4], row[5],row[6],row[7]);
	    } 
	}

	private Object[][] populateDataIntoTable(ArrayList<Engineer> engineerList, Object[][] table, boolean isPrioritizedList){
		Engineer engineer;
		int engineerListSize=engineerList.size();
	    for (int i = 0; i <engineerListSize; i++){
	    	engineer = engineerList.get(i);

			if(!isPrioritizedList){
				table[i][0] = (i+1)+". Engineer "+engineer.getId();
				table[i][1] = engineer.getEnterSuperBusyTime() == null ? "" : engineer.getEnterSuperBusyTime();
				table[i][2] = engineer.getEndSuperBusyTime() == null ? "" : engineer.getEndSuperBusyTime();
				table[i][3] = engineer.getJointQueueTime();
				table[i][4] = engineer.getBusyStatus() == 0 ? "Super-Busy" : "Non-Super-Busy";
			}else{
				table[i][5] = "| "+(i+1)+". Engineer "+engineer.getId();
				table[i][6] = engineer.getBusyStatus() == 0 ? "Super-Busy" : "Non-Super-Busy";
				table[i][7] = engineer.getJointQueueTime();
			}
		}
	    return table;
	}
	
	private class BusyStatusComparator implements Comparator<Engineer> {
		
		private double makeOneCupOfCoffeeTime;
		BusyStatusComparator(double BusyStatusComparator){
			this.makeOneCupOfCoffeeTime=BusyStatusComparator;
		}
	    @Override
	    public int compare(Engineer e1, Engineer e2) {
	    	
	    	if(makeOneCupOfCoffeeTime>0){ //The time of producing a cup of coffee is considered
	    		String time1=e1.getJointQueueTime(),time2=e2.getJointQueueTime();
	    		/*
	    		 * If the difference between 2 joint queue time is greater than the time of producing a cup of coffee,
	    		 * then busy status does not take into account
	    		 */
	    		if(TimeUtils.differentBetweenTime(time1, time2)>makeOneCupOfCoffeeTime){ 
	    			return TimeUtils.compareTime(time1,time2);	
	    		}
	    	}
	        return Integer.compare( e1.getBusyStatus(), e2.getBusyStatus());
	    }
	}
	private class JointQueueTimeComparator implements Comparator<Engineer> {
	    @Override
	    public int compare(Engineer e1, Engineer e2) {
	        return TimeUtils.compareTime(e1.getJointQueueTime(), e2.getJointQueueTime());
	    }
	}
}
