package main.Managers;

import main.Config.IConfig;
import main.Interface.IDownloadManager;
import main.Interface.IExecutor;
import main.Interface.IFileManager;
import main.Interface.ILogger;

/**
 * Created by Oguzhan on 1/21/2015.
 */

public class Executor implements IExecutor {
    private IDownloadManager _downloadManager;
    private IConfig _config;
    private IFileManager _fileManager;
    private ILogger _logger;
    public Executor(IDownloadManager downloadManager, IConfig con, IFileManager fileManager,ILogger logger) {
        _downloadManager = downloadManager;
        _config = con;
        _fileManager = fileManager;
    }

    @Override
    public void Execute() {
        _downloadManager.getLatestVersion(_config.getVersionCheckerUrl());
        if (_fileManager.checkIfLatestOrNot()) {
            if (!_fileManager.checkIfExist(_config.getDownloadDirectory())) {  //Checks if download directory exists or not
                _fileManager.makeDirectory(_config.getDownloadDirectory().toString()); // if not then create directory
            }
            _downloadManager.downloadLatestDriver(); //If everything is set for download action
        }
    }
}
