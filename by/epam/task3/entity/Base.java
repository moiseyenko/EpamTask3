package by.epam.task3.entity;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Base {

	private final static Logger LOG = LogManager.getLogger(Base.class);

	private int baseCapacity;
	private int currentCargo;
	private Queue<Terminal> terminals = new ArrayDeque<>();
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	private Base() {
	}

	private static class Holder {
		private static final Base INSTANCE = new Base();
	}

	public static Base getInstance() {
		return Holder.INSTANCE;
	}

	public void setBaseCapacityAndTerminalSize(int baseCapacity, int terminalSize) {
		this.baseCapacity = baseCapacity;
		currentCargo = new Random().nextInt(this.baseCapacity - 1) + 1;
		for (int i = 0; i < terminalSize; i++) {
			terminals.add(new Terminal(i));
		}
	}

	public int getBaseCapacity() {
		return baseCapacity;
	}

	public void setCurrentCargo(int currentCargo) {
		this.currentCargo = currentCargo;
	}

	public int getCurrentCargo() {
		return currentCargo;
	}

	Terminal terminal;

	public void startProcessTruck(Truck truck) {
		try {
			lock.lock();
			// trucks wait if base is full;
			if (terminals.isEmpty()) {
				try {
					if (LOG.isDebugEnabled()) {
						LOG.debug("{} await", truck.getTruckName());
					}
					if (LOG.isInfoEnabled()) {
						LOG.info("{} await", truck.getTruckName());
					}
					condition.await();
					if (LOG.isDebugEnabled()) {
						LOG.debug("{} go!!!", truck.getTruckName());
					}
					if (LOG.isInfoEnabled()) {
						LOG.info("{} go!!!", truck.getTruckName());
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			// terminal is occupied;
			terminal = terminals.poll();
			if (LOG.isDebugEnabled()) {
				LOG.debug("{}: current free terminals: {}", truck.getTruckName(), terminals.size());
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("{}: current free terminals: {}", truck.getTruckName(), terminals.size());
			}
			// load/unload operations;
			switch (truck.getOperation()) {
			case LOAD:
				if (LOG.isDebugEnabled()) {
					LOG.debug("{} has started load in terminal #{}", truck.getTruckName(), terminal.getNumber());
				}
				if (LOG.isInfoEnabled()) {
					LOG.info("{} has started load in terminal #{}", truck.getTruckName(), terminal.getNumber());
				}
				terminal.loadTruck(truck);
				break;
			case UNLOAD:
				if (LOG.isDebugEnabled()) {
					LOG.debug("{} has started unload in terminal #{}", truck.getTruckName(), terminal.getNumber());
				}
				if (LOG.isInfoEnabled()) {
					LOG.info("{} has started unload in terminal #{}", truck.getTruckName(), terminal.getNumber());
				}
				terminal.unloadTruck(truck);
				break;
			case UNLOAD_LOAD:
				if (LOG.isDebugEnabled()) {
					LOG.debug("{} has started unload/load in terminal #{}", truck.getTruckName(), terminal.getNumber());
				}
				if (LOG.isInfoEnabled()) {
					LOG.info("{} has started unload/load in terminal #{}", truck.getTruckName(), terminal.getNumber());
				}
				terminal.unloadLoadTruck(truck);
				break;
			}
			// change base cargo amount after load/unload operations;
			currentCargo += terminal.getTerminalCargo();
			// vacate terminal;
			terminal.setTerminalCargo(0);
		} finally {
			lock.unlock();
		}
	}

	public void endtProcessTruck(Truck truck) {
		try {
			lock.lock();
			if (LOG.isDebugEnabled()) {
				LOG.debug("{} has finished process in terminal #{}", truck.getTruckName(), terminal.getNumber());
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("{} has finished process in terminal #{}", truck.getTruckName(), terminal.getNumber());
			}
			// return terminal for use;
			if (terminals.offer(terminal)) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("{} signal", truck.getTruckName());
				}
				if (LOG.isInfoEnabled()) {
					LOG.info("{} signal", truck.getTruckName());
				}
				// notify waiting truck;
				condition.signal();
			}
		} finally {
			lock.unlock();
		}
	}

	// sort trucks according to perishables;
	public static void sortTrucks(List<Truck> trucks) {
		Collections.sort(trucks, (a, b) -> {
			Truck aTruck = (Truck) a;
			Truck bTruck = (Truck) b;
			return (aTruck.isPerishable() == bTruck.isPerishable() ? 0 : (aTruck.isPerishable() ? -1 : 1));
		});
	}

}
