import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by oguzhan.demir on 22.01.2015.
 */
public class Constants {

    private String version;
    public static String versionCheckerUrl = "http://chromedriver.storage.googleapis.com/LATEST_RELEASE";
    public static String zipExtension = ".zip";
    public static String firstPartOfDownloadLink = "http://chromedriver.storage.googleapis.com/";

    //For Windows
    public static Path downloadDirectory = Paths.get("C:\\Users\\oguzhan.demir\\Desktop\\selenide\\");
    private String downloadURL;
    public static String downloadLinkEnvironment = "/chromedriver_win32.zip";
    private Path fileDirAndName;
    public static String fileDir = "C:\\Users\\oguzhan.demir\\Desktop\\selenide\\chromeDriver-v";

    //For Mac
/*  public static Path downloadDirectory = Paths.get("C:\\Users\\oguzhan.demir\\Desktop\\selenide\\");
    private String downloadURL;
    public static String downloadLinkEnvironment = "/chromedriver_win32.zip";
    private Path fileDirAndName;
    public static String fileDir = "C:\\Users\\oguzhan.demir\\Desktop\\selenide\\chromeDriver-v";*/


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public Path getFileDirAndName() {
        return fileDirAndName;
    }

    public void setFileDirAndName(Path fileDirAndName) {
        this.fileDirAndName = fileDirAndName;
    }


}
