package FileOperations;

import java.io.File;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Path;

/**
 * Created by oguzhan.demir on 23.01.2015.
 */
public interface IFileManager {

    File pathToFile(Path path);

    Boolean checkIfExist(Path directory);

    Boolean checkIfExist(File file);

    void makeDirectory(String directoryPath);

    void copyFileToDir(InputStream inputStream, Path directory, CopyOption copyOption);

    Boolean checkFileNameIfContains(File file, String str);

    File[] listFiles(File file);
}
