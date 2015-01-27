import Config.Config;
import Config.IConfig;
import Interface.IDecompressor;
import Interface.IDownloadManager;
import Interface.IFileManager;
import Interface.ILogger;
import Managers.Decompressor;
import Managers.DownloadManager;
import Managers.Executor;
import Managers.FileManager;
import org.apache.log4j.Level;

public class Program {
    public static void main(String args[]) {

        String env = System.getProperty("env");
        String configFile = System.getProperty("configFile");

        ILogger logger  = new Managers.Logger();
        logger.setLogLevel(Level.INFO);
        IConfig config = new Config(env, configFile,logger);
        IDecompressor decompressor = new Decompressor(config,logger);
        IFileManager fileManager = new FileManager(config,logger);
        IDownloadManager downloadManager = new DownloadManager(decompressor, fileManager, config,logger);
        Executor executor = new Executor(downloadManager, config,fileManager,logger);

        logger.info("Operating on: " + env);

        executor.Execute();
    }
}
