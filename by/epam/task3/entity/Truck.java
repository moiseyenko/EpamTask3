package by.epam.task3.entity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import by.epam.task3.util.type.OperationType;

public class Truck extends Thread {

	private String truckName;
	private Base base;
	private int truckCapacity;
	private int cargo;
	private OperationType operation;
	private boolean perishable;

	public Truck(String truckName, Base base, int truckCapacity, OperationType operation, boolean perishable) {
		this.truckName = truckName;
		this.base = base;
		this.truckCapacity = truckCapacity;
		this.operation = operation;
		this.perishable = perishable;
		if (this.operation == OperationType.UNLOAD || this.operation == OperationType.UNLOAD_LOAD) {
			cargo = new Random().nextInt(truckCapacity - 1) + 1;
		}
	}

	public boolean isPerishable() {
		return perishable;
	}

	public String getTruckName() {
		return truckName;
	}

	public int getTruckCapacity() {
		return truckCapacity;
	}

	public int getCargo() {
		return cargo;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}

	public OperationType getOperation() {
		return operation;
	}

	@Override
	public void run() {
		// start load/unload operation;
		base.startProcessTruck(this);
		// loading/unloading truck;
		try {
			TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5000));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		// finish load/unload operation;
		base.endtProcessTruck(this);
	}
}
