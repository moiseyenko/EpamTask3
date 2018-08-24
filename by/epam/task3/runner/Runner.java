package by.epam.task3.runner;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.task3.entity.Base;
import by.epam.task3.entity.Truck;
import by.epam.task3.util.EqualizingTruck;
import by.epam.task3.util.type.OperationType;

public class Runner {

	private final static Logger LOG = LogManager.getLogger(Runner.class);

	public static void main(String[] args) {
		//create base;
		Base base = Base.getInstance();
		//set base capacity;
		base.setBaseCapacityAndTerminalSize(100, 3);
		//check necessity of equalizing truck;
		Timer timer = new Timer(true);
		timer.schedule(new EqualizingTruck(base), 0, 2000);
		//set trucks;
		List<Truck> trucks = Arrays.asList(
				new Truck("zero", base, 7, OperationType.UNLOAD, false),
				new Truck("one", base, 7, OperationType.LOAD, false),
				new Truck("two", base, 7, OperationType.UNLOAD, false),
				new Truck("three", base, 7, OperationType.UNLOAD_LOAD, false),
				new Truck("four", base, 7, OperationType.LOAD, false),
				new Truck("fivePerishable", base, 7, OperationType.UNLOAD_LOAD, true),
				new Truck("sixPerishable", base, 7, OperationType.UNLOAD, true),
				new Truck("seven", base, 7, OperationType.UNLOAD, false),
				new Truck("eight", base, 7, OperationType.LOAD, false),
				new Truck("nine", base, 7, OperationType.UNLOAD_LOAD, false),
				new Truck("tenPerishable", base, 7, OperationType.UNLOAD_LOAD, true),
				new Truck("elevenPerishable", base, 7, OperationType.UNLOAD, true));
		//sort trucks according to perishables;
		Base.sortTrucks(trucks);
		//start threads;
		for (Truck truck : trucks) {
			truck.start();
			try {
				TimeUnit.MILLISECONDS.sleep(1);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				if (LOG.isErrorEnabled()) {
					LOG.error(e);
				}

			}
		}
	}
}
