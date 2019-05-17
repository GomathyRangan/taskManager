package com.cts.taskmanager.exception;

public class DAOException  extends TaskManagerException {
	
	public DAOException (String message) {
		super (message);
	}
	
	public DAOException (String message,Throwable throwable) {
		super (message,throwable);
	}

}
