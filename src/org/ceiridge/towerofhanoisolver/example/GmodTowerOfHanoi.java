package org.ceiridge.towerofhanoisolver.example;

import org.ceiridge.towerofhanoisolver.internal.TowerOfHanoi;
import org.ceiridge.towerofhanoisolver.solving.solvers.TowerIterativeSolver;

public class GmodTowerOfHanoi { // Hardcoded test example
	
	public static void main(String[] args) throws Exception {
		Thread.sleep(5000l);
		System.out.println("Starting now...");
		
		TowerOfHanoi tower = new TowerOfHanoi(9); // Hardcoded
		TowerIterativeSolver solver = new TowerIterativeSolver(tower, new GmodTowerSolverRobot(250, 10));
		
		while(!tower.isSolved()) {
			solver.nextMove();
			System.out.println("Next move done");
		}
	}
}
