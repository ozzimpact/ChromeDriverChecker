package test;

import main.Config.Config;
import main.Config.IConfig;
import main.Interface.IDecompressor;
import main.Interface.IDownloadManager;
import main.Interface.IFileManager;
import main.Interface.ILogger;
import main.Managers.Decompressor;
import main.Managers.DownloadManager;
import main.Managers.Executor;
import main.Managers.FileManager;

/**
 * Created by oguzhan.demir on 30.01.2015.
 */
public class TestManager {


    public static void main(String[] args){

    String env = System.getProperty("env");
    String configFile = System.getProperty("configFile");

    ILogger logger  = new main.Managers.Logger();

    IConfig config = new Config(env, configFile,logger);
    IDecompressor decompressor = new Decompressor(config,logger);
    IFileManager fileManager = new FileManager(config,logger);
    IDownloadManager downloadManager = new DownloadManager(decompressor, fileManager, config,logger);
    Executor executor = new Executor(downloadManager, config,fileManager,logger);


    logger.info("Operating on: " + env);
    }


}
