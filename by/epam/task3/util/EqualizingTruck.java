package by.epam.task3.util;

import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.task3.entity.Base;

public class EqualizingTruck extends TimerTask {

	private final static Logger LOG = LogManager.getLogger(EqualizingTruck.class);

	Base base;

	public EqualizingTruck(Base base) {
		this.base = base;
	}

	/*if current base cargo is more or equals 90 percent of base capacity
	 * or if current base cargo is less or equals 15 percent of base capacity
	 * then new truck brings or takes cargo to make current base cargo equals
	 * to 50 percent of base capacity;
	*/
	@Override
	public void run() {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Check base currentCargo: {}", base.getCurrentCargo());
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("Check base currentCargo: {}", base.getCurrentCargo());
		}
		if (base.getCurrentCargo() > (int) (base.getBaseCapacity() * 0.9)
				|| base.getCurrentCargo() < (int) (base.getBaseCapacity() * 0.15)) {
			base.setCurrentCargo((int) (base.getBaseCapacity() * 0.5));
			if (LOG.isDebugEnabled()) {
				LOG.debug("!!!Equalized currentCargo: {}", base.getCurrentCargo());
			}
			if (LOG.isInfoEnabled()) {
				LOG.info("!!!Equalized currentCargo: {}", base.getCurrentCargo());
			}
		}
	}

}
