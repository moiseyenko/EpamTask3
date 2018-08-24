package by.epam.task3.entity;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Terminal {

	private final static Logger LOG = LogManager.getLogger(Terminal.class);

	private int number;
	private int terminalCargo;

	public Terminal(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public int getTerminalCargo() {
		return terminalCargo;
	}

	public void setTerminalCargo(int terminalCargo) {
		this.terminalCargo = terminalCargo;
	}

	//load operation;
	public void loadTruck(Truck truck) {
		int loadedCargo = new Random().nextInt(truck.getTruckCapacity() - 1) + 1;
		truck.setCargo(terminalCargo);
		terminalCargo -= loadedCargo;
		if (LOG.isDebugEnabled()) {
			LOG.debug("Terminal: {} is loading truck #{} with cargo ({})", number, truck.getTruckName(), terminalCargo);
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("Terminal: {} is loading truck #{} with cargo ({})", number, truck.getTruckName(), terminalCargo);
		}
	}

	//unload operation;
	public void unloadTruck(Truck truck) {
		terminalCargo += truck.getCargo();
		truck.setCargo(0);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Terminal: {} is unloading truck #{} with cargo ({})", number, truck.getTruckName(),
					terminalCargo);
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("Terminal: {} is unloading truck #{} with cargo ({})", number, truck.getTruckName(),
					terminalCargo);
		}
	}

	//unload/load operation;
	public void unloadLoadTruck(Truck truck) {
		terminalCargo += truck.getCargo();
		truck.setCargo(0);
		int loadedCargo = new Random().nextInt(truck.getTruckCapacity() - 1) + 1;
		truck.setCargo(loadedCargo);
		if (LOG.isDebugEnabled()) {
			LOG.debug(
					"Terminal: {} is unloading truck #{} with cargo ({})\nTerminal: {} is loading truck #{} with cargo (-{})",
					number, truck.getTruckName(), terminalCargo, number, truck.getTruckName(), loadedCargo);
		}
		if (LOG.isInfoEnabled()) {
			LOG.info(
					"Terminal: {} is unloading truck #{} with cargo ({})\nTerminal: {} is loading truck #{} with cargo (-{})",
					number, truck.getTruckName(), terminalCargo, number, truck.getTruckName(), loadedCargo);
		}
		terminalCargo -= loadedCargo;
	}

	@Override
	public String toString() {
		return "Terminal #" + number;
	}
}