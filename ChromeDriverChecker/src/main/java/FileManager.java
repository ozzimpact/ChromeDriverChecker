import Config.IConfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by oguzhan.demir on 23.01.2015.
 */
public class FileManager implements IFileManager {

    private IConfig _config;
    public FileManager(IConfig con) {
        _config = con;
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
    }

    @Override
    public void copyFileToDir(InputStream inputStream, Path directory, CopyOption copyOption) {
        try {
            Files.copy(inputStream, directory, copyOption);
        } catch (IOException e) {
            e.printStackTrace();
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
    public boolean checkIfLatestOrNot(File folder) {
        String tempFileName;

        //_folder = folder;
        if (this.checkIfExist(folder)) {
            for (File file : this.listFiles(folder)) {
                if (this.checkFileNameIfContains(file, _config.configProperties().getVersion()))
                    return false;
            }
        }

        //This section can vary with environment(OSX, LINUX)
        //Here example use of windows
        //Constants.fileDirAndName = Paths.get(Constants.fileDir + Constants.version + Constants.zipExtension);
        _config.configProperties().setFileDirAndName(Paths.get(_config.configProperties().getFileDir() +
                                                                  _config.configProperties().getVersion() +
                                                                  _config.configProperties().getZipExtension()));


        //Constants.downloadURL = Constants.firstPartOfDownloadLink + Constants.version + Constants.downloadLinkEnvironment;

        _config.configProperties().setDownloadURL(_config.configProperties().getFirstPartOfDownloadLink()+
                                                     _config.configProperties().getVersion()+
                                                     _config.configProperties().getDownloadLinkEnvironment());
        return true;
    }
}
