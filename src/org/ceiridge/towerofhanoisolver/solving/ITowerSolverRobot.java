package org.ceiridge.towerofhanoisolver.solving;

import org.ceiridge.towerofhanoisolver.internal.TowerState;

public interface ITowerSolverRobot {
	public void moveTo(TowerState state);
	
	public void pickUpDisk();
	public void dropDisk();
}
