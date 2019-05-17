package com.cts.taskmanager.exception;

public class TaskManagerException  extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaskManagerException (String message) {
		super (message);
	}

	public TaskManagerException (String message,Throwable throwable) {
		super (message,throwable);
	}

}
