package cli_csv_tool;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
	public String currentInput = "";
	
	//This logger object is universal, also used in other places.
    Logger log = Logger.getLogger("ExceptionHandler");
    
    public void uncaughtException(final Thread t, final Throwable e) {
        String msg = String.format("Thread %s: %s",t.getName(), e.getMessage());
        LogRecord lr = new LogRecord(Level.SEVERE, msg);
        lr.setThrown(e);
        log.log(lr);
    }
}