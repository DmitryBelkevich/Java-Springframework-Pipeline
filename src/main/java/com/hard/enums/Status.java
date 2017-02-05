package com.hard.enums;

public enum Status {
	PENDING,
	IN_PROGRESS,
	SKIPPED,
	FAILED,
	COMPLETED;
	
	public static Status getRandom() {
		int from = 1;
		int to = values().length;
		int randomIndex = (int) (Math.random() * (to - from) + from);
		
		return values()[randomIndex];
	}
}