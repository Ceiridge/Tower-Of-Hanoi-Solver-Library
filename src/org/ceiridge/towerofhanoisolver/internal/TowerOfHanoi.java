package org.ceiridge.towerofhanoisolver.internal;

public class TowerOfHanoi {
	private int totalDisks;
	private TowerPeg targetPeg, middlePeg, originPeg;

	public TowerOfHanoi(int totalDisks) throws Exception {
		if (totalDisks <= 2) {
			throw new Exception("More than two disks required");
		}

		this.totalDisks = totalDisks;
		this.targetPeg = new TowerPeg(this.totalDisks, TowerState.TARGET);
		this.middlePeg = new TowerPeg(this.totalDisks, TowerState.MIDDLE);
		this.originPeg = new TowerPeg(this.totalDisks, TowerState.ORIGIN);
	}

	public void moveDisk(TowerPeg from, TowerPeg to) throws TowerInvalidMoveException {
		TowerDisk fromDisk = from.getHighestDisk();

		if (fromDisk == null) {
			throw new TowerInvalidMoveException("From-Peg is empty");
		}
		if (!to.canPutDisk(fromDisk)) {
			throw new TowerInvalidMoveException("Disk size doesn't fit");
		}

		to.putDisk(from.takeDisk());
	}

	public boolean isSolved() {
		if (this.targetPeg.getDisks().size() != this.totalDisks) {
			return false;
		}

		int lastSize = Integer.MAX_VALUE;

		for (TowerDisk disk : this.targetPeg.getDisks()) {
			int size = disk.getSize();

			if (size >= lastSize) {
				return false;
			}
			lastSize = size;
		}

		return true;
	}


	public int getTotalDisks() {
		return totalDisks;
	}

	public TowerPeg getTargetPeg() {
		return targetPeg;
	}

	public TowerPeg getMiddlePeg() {
		return middlePeg;
	}

	public TowerPeg getOriginPeg() {
		return originPeg;
	}

	public TowerPeg getPeg(TowerState state) {
		switch (state) {
			case TARGET:
				return this.targetPeg;
			case MIDDLE:
				return this.middlePeg;
			case ORIGIN:
				return this.originPeg;
			default:
				return null;
		}
	}
}
