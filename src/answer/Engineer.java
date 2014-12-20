package answer;

public class Engineer {
	private static int NON_SUPER_BUSY_STATUS=Constants.NON_SUPER_BUSY_STATUS;
	private static int SUPER_BUSY_STATUS=Constants.SUPER_BUSY_STATUS;
	public static float timeStaySuperBusy;
	public static float chanceAnEngineerCanBeSuperBusy;
	private int id;
	private int busyStatus;
	private String startSuperBusyTime;
	private String endSuperBusyTime;
	private String enterQueueTime;
	
	Engineer(int id,int busyStatus){
		this.id=id;
		this.busyStatus=busyStatus;
	}
	
	//get, set Id
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	
	//get, set Joint Queue Time
	public void setEnterQueueTime(String jointQueueTime){
		this.enterQueueTime=jointQueueTime;
	}
	public String getJointQueueTime(){
		return enterQueueTime;
	}
	
	//get Start & End SuperBusyTime
	public String getEnterSuperBusyTime() {
		return startSuperBusyTime;
	}

	public String getEndSuperBusyTime() {
		return endSuperBusyTime;
	}
	
	//get, set  BusyStatus
	public int getBusyStatus(){
		return busyStatus;
	}

	public void setBusyStatus(int busyStatus){
		this.busyStatus=busyStatus;
	}
	
	/*
	 * Update the busy status of an engineer at the time he joint the queue
	 */
	public void updateBusyStatus(int currentHour){
		if(chanceAnEngineerCanBeSuperBusy>=1){ 
			setBusyStatus(SUPER_BUSY_STATUS); //the engineer is always super busy
			return;
		}
		
		/*
		 * If the engineer has been non super busy
		 * Set the engineer busy status based on the likelihood he can become super busy
		 * If the busy status is super busy, then the starting time that engineer becomes super busy is set
		 * Based on how long the engineer stays super busy, the time that the engineer is non super busy is then set
		 */
		if(busyStatus==NON_SUPER_BUSY_STATUS){ 
			double randomNumber = Math.random(); //0.0 <= randomNumber < 1.0.
			if (randomNumber <= chanceAnEngineerCanBeSuperBusy){
				String startedBusyTime=TimeUtils.generateTimeBasedOnHour(currentHour);
				this.startSuperBusyTime=startedBusyTime;
				this.endSuperBusyTime=TimeUtils.addMinutes(startedBusyTime,timeStaySuperBusy);				
				updateBusyStatusBasedOnTime(enterQueueTime,startSuperBusyTime, endSuperBusyTime);
			}
			return;
		}
		
		if(busyStatus==SUPER_BUSY_STATUS){ 
			updateBusyStatusBasedOnTime(enterQueueTime,startSuperBusyTime, endSuperBusyTime);
			return;
		}
	}
	
	private void updateBusyStatusBasedOnTime(String enterQueueTime,String startSuperBusyTime,String endSuperBusyTime){
		int enterQueueVSEndSuperBusyTime=TimeUtils.compareTime(enterQueueTime,endSuperBusyTime);
		int enterQueueVSStartSuperBusyTime=TimeUtils.compareTime(enterQueueTime,startSuperBusyTime);
		
		/*
		 * enterQueueTime < startSuperBusyTime Or enterQueueTime >= endSuperBusyTime  
		 */
		if((enterQueueVSStartSuperBusyTime<0) || (enterQueueVSEndSuperBusyTime>=0)){
			setBusyStatus(NON_SUPER_BUSY_STATUS);
		}
		
		/*
		 * enterQueueTime >= startSuperBusyTime Or enterQueueTime < endSuperBusyTime  
		 */
		if((enterQueueVSStartSuperBusyTime>=0) && (enterQueueVSEndSuperBusyTime<0)){
			setBusyStatus(SUPER_BUSY_STATUS);
		}
	}
}
