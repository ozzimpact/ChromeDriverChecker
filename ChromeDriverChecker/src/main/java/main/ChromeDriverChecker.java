package main;

import main.Config.Config;
import main.Config.IConfig;
import main.Interface.IDecompressor;
import main.Interface.IDownloadManager;
import main.Interface.IFileManager;
import main.Interface.ILogger;
import main.Managers.*;

import java.nio.file.Paths;

public class ChromeDriverChecker {
    public ChromeDriverChecker() {

        String env = null;
        if (System.getProperty("os.name").startsWith("Windows"))
            env = "win";
        else if (System.getProperty("os.name").startsWith("Mac"))
            env = "mac";

        String configFile = "config";
        String userHome = System.getProperty("user.home");
        ILogger logger = new Logger();

        IConfig config = new Config(env, configFile, logger);
        config.setDownloadDirectory(Paths.get(userHome + config.getDownloadDirectory()));
        config.setFileDir(userHome + config.getFileDir());
        IDecompressor decompressor = new Decompressor(config, logger);
        IFileManager fileManager = new FileManager(config, logger);
        IDownloadManager downloadManager = new DownloadManager(decompressor, fileManager, config, logger);
        Executor executor = new Executor(downloadManager, config, fileManager, logger);


        logger.info("Operating on: " + env);

        executor.Execute();
    }
}
