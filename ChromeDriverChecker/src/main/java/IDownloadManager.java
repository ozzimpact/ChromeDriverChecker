/**
 * Created by oguzhan.demir on 22.01.2015.
 */
public interface IDownloadManager {

    void getLatestVersion(String versionCheckUrl);
    void downloadLatestDriver();
}
