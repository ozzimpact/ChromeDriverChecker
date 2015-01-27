package Managers;

import Interface.ILogger;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by oguzhan.demir on 27.01.2015.
 */
public class Logger implements ILogger {

    private org.apache.log4j.Logger _logger;

    public Logger() {
        PropertyConfigurator.configure("log4j.properties");
        _logger = org.apache.log4j.Logger.getRootLogger();

    }

    @Override
    public void setLogLevel(Level logLevel) {
        _logger.setLevel(logLevel);
    }

    @Override
    public void trace(String log) {
        _logger.trace(log);
    }

    @Override
    public void debug(String log) {
        _logger.debug(log);
    }

    @Override
    public void info(String log) {
        _logger.info(log);
    }

    @Override
    public void warn(String log) {
        _logger.warn(log);
    }

    @Override
    public void error(String log) {
        _logger.error(log);
    }

    @Override
    public void fatal(String log) {
        _logger.fatal(log);
    }


}
