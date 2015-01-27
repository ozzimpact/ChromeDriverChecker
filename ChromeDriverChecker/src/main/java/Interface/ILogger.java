package Interface;

import org.apache.log4j.Level;

/**
 * Created by oguzhan.demir on 27.01.2015.
 */
public interface ILogger {

    void setLogLevel(Level logLevel);
    void trace(String log);
    void debug(String log);
    void info(String log);
    void warn(String log);
    void error(String log);
    void fatal(String log);

}
