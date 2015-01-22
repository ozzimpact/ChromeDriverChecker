import java.io.File;
import java.nio.file.Paths;

/**
 * Created by oguzhan.demir on 22.01.2015.
 */
public class ControlUnit implements IControlUnit {

    private IDownloadManager _downloadManager;
    private File _folder;
    private Constants _constants;

    @Override
    public boolean checkIfLatestOrNot(File folder, IDownloadManager downloadManager, Constants constants) {
        _downloadManager = downloadManager;
        _constants = constants;
        String tempFileName;
        _downloadManager.getLatestVersion(Constants.versionCheckerUrl, _constants);

        //_folder = folder;
        if (folder.exists()) {
            for (File file : folder.listFiles()) {
                if (file.getName().contains(_constants.getVersion()))
                    return false;
            }
        }

        //This section can vary with environment(OSX, LINUX)
        //Here example use of windows
        _constants.setFileDirAndName(Paths.get(Constants.fileDir + _constants.getVersion() + Constants.zipExtension));
        _constants.setDownloadURL(Constants.firstPartOfDownloadLink + _constants.getVersion() + Constants.downloadLinkEnvironment);

        return true;
    }
}
