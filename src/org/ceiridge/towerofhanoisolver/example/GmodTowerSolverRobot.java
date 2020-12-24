package org.ceiridge.towerofhanoisolver.example;

import java.awt.AWTException;
import java.awt.Robot;
import org.ceiridge.towerofhanoisolver.internal.TowerState;
import org.ceiridge.towerofhanoisolver.solving.ITowerSolverRobot;
import com.sun.glass.events.KeyEvent;

// Pegs:
// T M O
//   Starting here at M
public class GmodTowerSolverRobot implements ITowerSolverRobot {
	private Robot robot;
	private TowerState cursor = TowerState.MIDDLE;
	private int holdDelay, nextDelay;

	public GmodTowerSolverRobot(int holdDelay, int nextDelay) throws AWTException {
		this.robot = new Robot();
		this.holdDelay = holdDelay;
		this.nextDelay = nextDelay;
	}

	@Override
	public void moveTo(TowerState state) {
		System.out.println("Moving from " + this.cursor.name() + " to " + state.name());
		
		while(this.cursor != state) {
			if(this.cursor == TowerState.TARGET && state == TowerState.ORIGIN) { // "Edge" cases
				this.pressKey(KeyEvent.VK_A);
				this.cursor = state;
				continue;
			}
			if(this.cursor == TowerState.ORIGIN && state == TowerState.TARGET) {
				this.pressKey(KeyEvent.VK_D);
				this.cursor = state;
				continue;
			}
			
			int ordCursor = this.cursor.ordinal();
			int ordState = state.ordinal();
			
			if(ordCursor > ordState) {
				this.pressKey(KeyEvent.VK_A);
				this.cursor = TowerState.values()[ordCursor - 1];
			} else {
				this.pressKey(KeyEvent.VK_D);
				this.cursor = TowerState.values()[ordCursor + 1];
			}
		}
	}

	@Override
	public void pickUpDisk() {
		System.out.println("Picking up");
		this.pressKey(KeyEvent.VK_W);
	}

	@Override
	public void dropDisk() {
		System.out.println("Dropping");
		this.pressKey(KeyEvent.VK_S);
	}

	private void pressKey(int keyCode) {
		try {
			this.robot.keyPress(keyCode);
			Thread.sleep(this.holdDelay);
			this.robot.keyRelease(keyCode);
			Thread.sleep(this.nextDelay);
		} catch (Exception e) {
		}
	}
}
