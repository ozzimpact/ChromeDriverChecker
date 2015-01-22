/**
 * Created by oguzhan.demir on 22.01.2015.
 */
public interface IDownloadManager {

    void getLatestVersion(String versionCheckUrl,Constants constants);
    void downloadLatestDriver(IControlUnit iControlUnit,IDecompressor decompressor,Constants constants);
}
