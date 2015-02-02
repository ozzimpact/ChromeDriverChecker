package main.Managers;

import main.Config.IConfig;
import main.Interface.IFileManager;
import main.Interface.ILogger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileManager implements IFileManager {

    private IConfig _config;
    private ILogger _logger;

    public FileManager(IConfig con, ILogger logger) {
        _config = con;
        _logger = logger;
    }

    @Override
    public File pathToFile(Path path) {
        return path.toFile();
    }

    @Override
    public Boolean checkIfExist(Path directory) {
        return Files.exists(directory);
    }

    @Override
    public Boolean checkIfExist(File file) {
        return file.exists();
    }

    @Override
    public void makeDirectory(String directoryPath) {
        new File(directoryPath).mkdirs();
        _logger.info("Directory created.");
    }

    @Override
    public void copyFileToDir(InputStream inputStream, Path directory, CopyOption copyOption) {
        try {
            Files.copy(inputStream, directory, copyOption);
        } catch (IOException ex) {
            _logger.warn("Something went wrong while copying downloaded file to directory: " + ex.toString());
        }
    }

    @Override
    public Boolean checkFileNameIfContains(File file, String str) {
        return file.getName().contains(str);
    }

    @Override
    public File[] listFiles(File file) {
        return file.listFiles();
    }

    @Override
    public boolean checkIfLatestOrNot() {
        String tempFileName;
        File folder = pathToFile(_config.getDownloadDirectory());
        if (this.checkIfExist(folder)) {
            for (File file : this.listFiles(folder)) {
                if (this.checkFileNameIfContains(file, _config.getVersion())) {
                    _logger.info("Driver version is up-to-date.");
                    return false;
                }
            }
        }
        //This section can vary with environment(OSX, LINUX)
        //Here example use of windows
        //Constants.fileDirAndName = Paths.get(Constants.fileDir + Constants.version + Constants.zipExtension);
        _config.setFileDirAndName(Paths.get(_config.getFileDir() +
                _config.getVersion() +
                _config.getZipExtension()));
        _config.setDownloadURL(_config.getFirstPartOfDownloadLink() +
                _config.getVersion() +
                _config.getDownloadLinkEnvironment());

        if (!checkIfExist(_config.getDownloadDirectory()))
            makeDirectory(_config.getDownloadDirectory().toString());
        _logger.info("Driver version will be updated to: " + _config.getVersion());
        return true;
    }
}
