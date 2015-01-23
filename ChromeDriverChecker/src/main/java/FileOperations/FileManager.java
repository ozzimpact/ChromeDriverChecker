package FileOperations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by oguzhan.demir on 23.01.2015.
 */
public class FileManager implements IFileManager {

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
        new File(directoryPath).mkdir();
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
}
