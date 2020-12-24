package org.ceiridge.towerofhanoisolver.solving.solvers;

import org.ceiridge.towerofhanoisolver.internal.TowerInvalidMoveException;
import org.ceiridge.towerofhanoisolver.internal.TowerOfHanoi;
import org.ceiridge.towerofhanoisolver.internal.TowerPeg;
import org.ceiridge.towerofhanoisolver.solving.ITowerSolverRobot;
import org.ceiridge.towerofhanoisolver.solving.TowerSolver;

public class TowerIterativeSolver extends TowerSolver {
	private boolean evenAmount;

	public TowerIterativeSolver(TowerOfHanoi tower, ITowerSolverRobot robot) {
		super(tower, robot);
		this.evenAmount = tower.getTotalDisks() % 2 == 0; // Even disk amount
	}

	/***
	 * Wikipedia explanation:
	 * 
	 * For an even number of disks: make the legal move between pegs A and B (in either direction),
	 * make the legal move between pegs A and C (in either direction), make the legal move between
	 * pegs B and C (in either direction), repeat until complete.
	 * 
	 * For an odd number of disks: make the legal move between pegs A and C (in either direction),
	 * make the legal move between pegs A and B (in either direction), make the legal move between
	 * pegs B and C (in either direction), repeat until complete.
	 * 
	 * In each case, a total of 2^n - 1 moves are made.
	 * 
	 * @throws TowerInvalidMoveException
	 */
	@Override
	public void nextMove() throws TowerInvalidMoveException {
		if (this.tower.isSolved()) {
			return;
		}

		TowerPeg origin = this.tower.getOriginPeg();
		TowerPeg middle = this.tower.getMiddlePeg();
		TowerPeg target = this.tower.getTargetPeg();

		if (evenAmount) {
			this.makeMove(origin, middle);
			this.makeMove(origin, target);
			this.makeMove(middle, target);
		} else {
			this.makeMove(origin, target);
			this.makeMove(origin, middle);
			this.makeMove(middle, target);
		}
	}

	private boolean isPutOnFirstLegal(TowerPeg first, TowerPeg second) {
		return !second.isEmpty() && first.canPutDisk(second.getHighestDisk());
	}

	private void makeMove(TowerPeg first, TowerPeg second) throws TowerInvalidMoveException {
		if (isPutOnFirstLegal(first, second)) {
			this.robot.moveTo(second.getState());
			this.robot.pickUpDisk();
			this.robot.moveTo(first.getState());
			this.robot.dropDisk();

			this.tower.moveDisk(second, first);
		} else {
			this.robot.moveTo(first.getState());
			this.robot.pickUpDisk();
			this.robot.moveTo(second.getState());
			this.robot.dropDisk();

			this.tower.moveDisk(first, second);
		}
	}

	/// This returns the amount of required moves to win
	public int getTotalRequiredMoves() {
		return (int) (Math.pow(2, this.tower.getTotalDisks()) - 1);
	}
}
