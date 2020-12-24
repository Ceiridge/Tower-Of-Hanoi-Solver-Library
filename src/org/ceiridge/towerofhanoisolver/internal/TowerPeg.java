package org.ceiridge.towerofhanoisolver.internal;

import java.util.ArrayList;

public class TowerPeg {
	private ArrayList<TowerDisk> disks;
	private TowerState state;

	public TowerPeg(int diskAmount, TowerState state) {
		this.disks = new ArrayList<>(diskAmount);
		this.state = state;

		if (state == TowerState.ORIGIN) { // Create origin disks
			for (int i = 0; i < diskAmount; i++) {
				this.disks.add(new TowerDisk(i + 1));
			}
		}
	}

	public boolean isEmpty() {
		return this.disks.isEmpty();
	}

	public TowerDisk getHighestDisk() {
		return this.isEmpty() ? null : this.disks.get(this.disks.size() - 1);
	}

	public boolean canPutDisk(TowerDisk disk) {
		return this.isEmpty() || this.getHighestDisk().getSize() < disk.getSize();
	}

	public TowerDisk takeDisk() {
		return this.disks.remove(this.disks.size() - 1);
	}

	public TowerDisk putDisk(TowerDisk disk) {
		this.disks.add(disk);
		return disk;
	}

	public ArrayList<TowerDisk> getDisks() {
		return disks;
	}

	public TowerState getState() {
		return state;
	}
}
