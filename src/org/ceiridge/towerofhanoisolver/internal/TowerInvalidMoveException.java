package org.ceiridge.towerofhanoisolver.internal;

public class TowerInvalidMoveException extends Exception {
	private static final long serialVersionUID = 9102803506967011014L;

	public TowerInvalidMoveException() {}

	public TowerInvalidMoveException(String error) {
		super(error);
	}
}
