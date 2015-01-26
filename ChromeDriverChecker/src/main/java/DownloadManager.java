import Config.IConfig;
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
    private IConfig _config;

    public DownloadManager(IDecompressor decompressor, IFileManager fileManager, IConfig con) {
        _decompressor = decompressor;
        _fileManager = fileManager;
        _config = con;
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
    _config.configProperties().setVersion(body);

}catch (IOException ex){
    ex.printStackTrace();
}
    }

    @Override
    public void downloadLatestDriver() {

        if (_fileManager.checkIfLatestOrNot(_fileManager.pathToFile(_config.configProperties().getDownloadDirectory()))) {

            if (!_fileManager.checkIfExist(_config.configProperties().getDownloadDirectory())) {

                _fileManager.makeDirectory(_config.configProperties().getDownloadDirectory().toString());

            }
            try {
                URL website = new URL(_config.configProperties().getDownloadURL());
                _fileManager.copyFileToDir(website.openStream(), _config.configProperties().getFileDirAndName(), StandardCopyOption.REPLACE_EXISTING);
                _decompressor.decompress(_config.configProperties().getDownloadDirectory().toString(), _config.configProperties().getFileDirAndName().toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            _config.configProperties().setActionMessage(_config.configProperties().getUpdateMessage()+_config.configProperties().getVersion());
        }else
            _config.configProperties().setActionMessage(_config.configProperties().getUpToDateMessage());
    }
}
