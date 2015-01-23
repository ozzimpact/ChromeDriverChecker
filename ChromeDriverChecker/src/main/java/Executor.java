/**
 * Created by Oguzhan on 1/21/2015.
 */

public class Executor {
    private  IDownloadManager _downloadManager;

    public Executor(IDownloadManager downloadManager) {
        _downloadManager = downloadManager;
    }

    public void Execute() {

        _downloadManager.getLatestVersion(Constants.versionCheckerUrl);
        _downloadManager.downloadLatestDriver();
        System.out.println(Constants.actionMessage);
    }
}
