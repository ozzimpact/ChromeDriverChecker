package Managers;

import Config.IConfig;
import Interface.*;
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
    private ILogger _logger;

    public DownloadManager(IDecompressor decompressor, IFileManager fileManager, IConfig con, ILogger logger) {
        _logger = logger;
        _decompressor = decompressor;
        _fileManager = fileManager;
        _config = con;
    }

    @Override
    public void getLatestVersion(String versionCheckerUrl) {
        _urlManager = new URLManager(versionCheckerUrl,_logger);
        String body = "";
        try {
            _urlManager.openConnection();
            URLConnection con = _urlManager.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            body = IOUtils.toString(in, encoding);
            _config.setVersion(body);

        } catch (IOException ex) {
            _logger.warn("Could not get the latest version: " + ex.toString());
        }
        _logger.info("Latest version checked successfully.");

    }

    @Override
    public void downloadLatestDriver()  {
        try {
            URL website = new URL(_config.getDownloadURL());
            _fileManager.copyFileToDir(website.openStream(), _config.getFileDirAndName(), StandardCopyOption.REPLACE_EXISTING);
            _logger.info("Successfully downloaded.");
            _decompressor.decompress(_config.getDownloadDirectory().toString(), _config.getFileDirAndName().toString());
        } catch (IOException ex) {
            _logger.warn("Could not download the file: " + ex.toString());
        }

    }
}
