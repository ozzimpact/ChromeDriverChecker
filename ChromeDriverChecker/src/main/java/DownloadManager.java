import FileOperations.FileManager;
import FileOperations.IFileManager;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;
import java.nio.file.StandardCopyOption;

/**
 * Created by oguzhan.demir on 22.01.2015.
 */
public class DownloadManager implements IDownloadManager {

    private IControlUnit _controlUnit;
    private IDecompressor _decompressor;
    private Constants _constants;
    private IFileManager _fileManager;

/*    public DownloadManager() {
        this(new ControlUnit(), new Decompressor(), new Constants(), new FileManager());
    }*/

    public DownloadManager() {

        _decompressor = new Decompressor();
        _constants = new Constants();
        _fileManager = new FileManager();
        _controlUnit = new ControlUnit(this,_constants,_fileManager);
    }

    @Override
    public void getLatestVersion(String versionCheckerUrl) {
        String body = "";
        try {
            URL url = new URL(versionCheckerUrl);
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            body = IOUtils.toString(in, encoding);
        } catch (MalformedInputException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        _constants.setVersion(body);
    }

    @Override
    public void downloadLatestDriver() {

        if (_controlUnit.checkIfLatestOrNot(_fileManager.pathToFile(Constants.downloadDirectory))) {

            if (!_fileManager.checkIfExist(Constants.downloadDirectory)) {

                _fileManager.makeDirectory(Constants.downloadDirectory.toString());

            }
            try {
                URL website = new URL(_constants.getDownloadURL());
                _fileManager.copyFileToDir(website.openStream(), _constants.getFileDirAndName(), StandardCopyOption.REPLACE_EXISTING);
                _decompressor.decompress(Constants.downloadDirectory.toString(), _constants.getFileDirAndName().toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }
    }
}
