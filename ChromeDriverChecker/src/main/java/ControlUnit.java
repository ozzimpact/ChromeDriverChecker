import FileOperations.IFileManager;

import java.io.File;
import java.nio.file.Paths;

/**
 * Created by oguzhan.demir on 22.01.2015.
 */
public class ControlUnit implements IControlUnit {

    private IDownloadManager _downloadManager;
    private Constants _constants;
    private IFileManager _fileManager;
/*
    public ControlUnit(){
        this(new DownloadManager(),new Constants(),new FileManager());
    }*/
    public ControlUnit(IDownloadManager downloadManager,Constants constants,IFileManager fileManager ){
        _downloadManager = downloadManager;
        _constants = constants;
        _fileManager = fileManager;
    }


    @Override
    public boolean checkIfLatestOrNot(File folder) {
        String tempFileName;
        _downloadManager.getLatestVersion(_constants.versionCheckerUrl);

        //_folder = folder;
        if (_fileManager.checkIfExist(folder)) {
            for (File file : _fileManager.listFiles(folder)) {
                if (_fileManager.checkFileNameIfContains(file, _constants.getVersion()))
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
