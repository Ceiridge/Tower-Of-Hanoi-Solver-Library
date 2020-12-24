package org.ceiridge.towerofhanoisolver.solving;

import org.ceiridge.towerofhanoisolver.internal.TowerInvalidMoveException;
import org.ceiridge.towerofhanoisolver.internal.TowerOfHanoi;

public abstract class TowerSolver {
	protected TowerOfHanoi tower;
	protected ITowerSolverRobot robot;
	
	public TowerSolver(TowerOfHanoi tower, ITowerSolverRobot robot) {
		this.tower = tower;
		this.robot = robot;
	}
	
	public abstract void nextMove() throws TowerInvalidMoveException;
}
