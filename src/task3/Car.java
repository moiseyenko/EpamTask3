package task3;

public class Car implements Runnable{

	
	private Parking park;
	private String name;
	private long parkTime;
	private long waitTime;
	
	
	
	public Car(String name, Parking park, long parkTime, long waitTime) {
		this.name = name;
		this.park = park;
		this.parkTime = parkTime;
		this.waitTime = waitTime;
		new Thread(this).start();
	}
	
	public String getName() {
		return name;
	}
	
	public long getWaitTime() {
		return waitTime;
	}
	
	@Override
	public void run() {
		boolean isCarAdd;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isCarAdd = park.addCar(this);
		try {
			Thread.sleep(parkTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(isCarAdd) {
			park.removeCar(this);
		}
		
		
	}
}
