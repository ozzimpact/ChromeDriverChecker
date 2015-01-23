import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by oguzhan.demir on 22.01.2015.
 */
public class Constants {

    //For all environments
    public static String version;
    public static String versionCheckerUrl = "http://chromedriver.storage.googleapis.com/LATEST_RELEASE";
    public static String zipExtension = ".zip";
    public static String firstPartOfDownloadLink = "http://chromedriver.storage.googleapis.com/";
    public static String actionMessage;
    public static String updateMessage = "Chrome driver is updated to version-";
    public static String upToDateMessage = "Chrome driver is up-to-date.";
    public static String downloadURL;
    public static Path fileDirAndName;

    //For Windows
    public static Path downloadDirectory = Paths.get("C:\\selenide\\chromedriver\\");
    public static String downloadLinkEnvironment = "/chromedriver_win32.zip";
    public static String fileDir = "C:\\selenide\\chromedriver\\chromeDriver-v";


    //For Mac
/*  public static Path downloadDirectory = Paths.get("C:\\Users\\oguzhan.demir\\Desktop\\selenide\\");
    public static String downloadLinkEnvironment = "/chromedriver_win32.zip";
    public static String fileDir = "C:\\Users\\oguzhan.demir\\Desktop\\selenide\\chromeDriver-v";*/


}
