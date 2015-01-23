import FileOperations.IFileManager;

/**
 * Created by Oguzhan on 1/21/2015.
 */

public class Executor {

    private  IDecompressor _decompressor;
    private  IDownloadManager _downloadManager;
    private  IControlUnit _controlUnit;
    private  Constants _constant;
    private  IFileManager _fileManager;

    public Executor() {
        this(new DownloadManager());
    }

    public Executor(IDownloadManager downloadManager) {

        _downloadManager = downloadManager;

    }

    public void Execute() {

        _downloadManager.downloadLatestDriver();
    }
}
