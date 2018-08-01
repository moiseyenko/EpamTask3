package task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {
	private ReentrantLock locker;
	private Condition condition;
	private int capacity;
	private List<Car> list = new ArrayList<>();

	public Parking(int capacity) {
		this.capacity = capacity;
		locker = new ReentrantLock();
		condition = locker.newCondition();
	}

	public int getCapacity() {
		return capacity;
	}

	public int getCurrentSize() {
		return list.size();
	}

	boolean addCar(Car car) {
		
		try {
			locker.lock();
			if (list.size() >= capacity) {
				System.out.println("Park full! Car " + car.getName() + " is waiting parkplace");
				if(condition.await(car.getWaitTime(), TimeUnit.MILLISECONDS)) {
					System.out.println("Car " + car.getName() + " got parkplace successfully");
					boolean result = list.add(car);
					System.out.println("Car " + car.getName() + " add: " + result);
					return result;
				}else {
					System.out.println("Car  "+ car.getName() + " did not wait for the parking space");
					return false;
				}
			}else {
				boolean result = list.add(car);
				System.out.println("Car " + car.getName() + " add: " + result);
				return result;
			}

			//condition.signal();

		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Car " + car.getName() + " has unlocked");
			locker.unlock();
		}
		return false;
	}

	void removeCar(Car car) {
		
		try {
			locker.lock();
			System.out.println("Car " + car.getName() + " has locked for remove");
			/*while (list.size() < 1) {
				System.out.println("Park empty!!! Car " + car.getName() + " is waiting car");
				condition.await();
			}*/

			boolean result = list.remove(car);
			System.out.println("Car " + car.getName() + " remove: " + result);
			condition.signal();

		}/*catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}*/finally {
			System.out.println("Car " + car.getName() + " has unlocked after remove");
			locker.unlock();
		}

	}
}
