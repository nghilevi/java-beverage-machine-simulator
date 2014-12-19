package answer;

public enum BusyStatus {
	NON_SUPER_BUSY_STATUS(1),SUPER_BUSY_STATUS(0);
	
	private int busyStatus;
	 
	private BusyStatus(int busyStatus) {
		this.busyStatus = busyStatus;
	}
 
	public int getWorkingStatus() {
		return busyStatus;
	}
}


