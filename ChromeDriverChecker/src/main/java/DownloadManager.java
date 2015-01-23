import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.StandardCopyOption;

/**
 * Created by oguzhan.demir on 22.01.2015.
 */
public class DownloadManager implements IDownloadManager {


    private IDecompressor _decompressor;
    private IFileManager _fileManager;
    private IURLManager _urlManager;

    public DownloadManager(IDecompressor decompressor, IFileManager fileManager) {
        _decompressor = decompressor;
        _fileManager = fileManager;
    }

    @Override
    public void getLatestVersion(String versionCheckerUrl) {

        _urlManager = new URLManager(versionCheckerUrl);

        String body = "";
try {
    // URL url = new URL(versionCheckerUrl);
    _urlManager.openConnection();
    URLConnection con = _urlManager.openConnection();
    InputStream in = con.getInputStream();
    String encoding = con.getContentEncoding();
    encoding = encoding == null ? "UTF-8" : encoding;
    body = IOUtils.toString(in, encoding);

    Constants.version = body;
}catch (IOException ex){
    ex.printStackTrace();
}
    }

    @Override
    public void downloadLatestDriver() {

        if (_fileManager.checkIfLatestOrNot(_fileManager.pathToFile(Constants.downloadDirectory))) {

            if (!_fileManager.checkIfExist(Constants.downloadDirectory)) {

                _fileManager.makeDirectory(Constants.downloadDirectory.toString());

            }
            try {
                URL website = new URL(Constants.downloadURL);
                _fileManager.copyFileToDir(website.openStream(), Constants.fileDirAndName, StandardCopyOption.REPLACE_EXISTING);
                _decompressor.decompress(Constants.downloadDirectory.toString(), Constants.fileDirAndName.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Constants.actionMessage = Constants.updateMessage+Constants.version;
        }else
            Constants.actionMessage = Constants.upToDateMessage;
    }
}
