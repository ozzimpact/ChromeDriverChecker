package Managers;

import Interface.ILogger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by oguzhan.demir on 27.01.2015.
 */
public class Logger implements ILogger {

    private org.apache.log4j.Logger _traceLogger;
    private org.apache.log4j.Logger _debugLogger;
    private org.apache.log4j.Logger _infoLogger;
    private org.apache.log4j.Logger _warnLogger;
    private org.apache.log4j.Logger _errorLogger;
    private org.apache.log4j.Logger _fatalLogger;

    public Logger() {
        PropertyConfigurator.configure("log4j.properties");
        _traceLogger = org.apache.log4j.Logger.getLogger("trace");
        _debugLogger = org.apache.log4j.Logger.getLogger("debug");
        _infoLogger = org.apache.log4j.Logger.getLogger("info");
        _warnLogger = org.apache.log4j.Logger.getLogger("warn");
        _errorLogger = org.apache.log4j.Logger.getLogger("error");
        _fatalLogger = org.apache.log4j.Logger.getLogger("fatal");

      }

    @Override
    public void trace(String log) {
        _traceLogger.trace(log);
    }

    @Override
    public void debug(String log) {
        _debugLogger.debug(log);
    }

    @Override
    public void info(String log) {
        _infoLogger.info(log);
    }

    @Override
    public void warn(String log) {
        _warnLogger.warn(log);
    }

    @Override
    public void error(String log) {
        _errorLogger.error(log);
    }

    @Override
    public void fatal(String log) {
        _fatalLogger.fatal(log);
    }


}
