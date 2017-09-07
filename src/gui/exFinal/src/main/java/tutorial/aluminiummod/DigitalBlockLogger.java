package tutorial.aluminiummod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DigitalBlockLogger {
	
	public static Logger logger = LogManager.getLogger("DigitalClock");
	 
	public static void trace(String msg) {
		DigitalBlockLogger.logger.trace(msg);
	}
 
	public static void info(String msg) {
		DigitalBlockLogger.logger.info(msg);
	}
 
	public static void warn(String msg) {
		DigitalBlockLogger.logger.warn(msg);
	}
}
