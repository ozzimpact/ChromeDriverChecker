import Config.IConfig;

/**
 * Created by Oguzhan on 1/21/2015.
 */

public class Executor {
    private  IDownloadManager _downloadManager;
    private IConfig _config;
public Executor(IDownloadManager downloadManager, IConfig con) {
        _downloadManager = downloadManager;
        _config = con;
    }

    public void Execute() {



        _downloadManager.getLatestVersion(_config.configProperties().getVersionCheckerUrl());
        _downloadManager.downloadLatestDriver();
        System.out.println(_config.configProperties().getActionMessage());
    }
}
