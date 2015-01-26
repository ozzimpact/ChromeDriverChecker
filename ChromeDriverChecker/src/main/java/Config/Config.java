package Config;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by ty-testmac on 26/01/15.
 */
public class Config implements IConfig {

    private  String version;
    private  String versionCheckerUrl;
    private  String zipExtension;
    private  String firstPartOfDownloadLink;
    private  String actionMessage;
    private  String updateMessage;
    private  String upToDateMessage;
    private  String downloadURL;
    private  Path fileDirAndName;
    private  Path downloadDirectory;
    private  String downloadLinkEnvironment;
    private  String fileDir;
    private  String pathSeparator;


    public String getPathSeparator() {
        return pathSeparator;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionCheckerUrl() {
        return versionCheckerUrl;
    }

    public String getZipExtension() {
        return zipExtension;
    }

   public String getFirstPartOfDownloadLink() {
        return firstPartOfDownloadLink;
    }
    public String getActionMessage() {
        return actionMessage;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public String getUpToDateMessage() {
        return upToDateMessage;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
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

    public Path getDownloadDirectory() {
        return downloadDirectory;
    }

    public void setDownloadDirectory(Path downloadDirectory) {
        this.downloadDirectory = downloadDirectory;
    }

    public String getDownloadLinkEnvironment() {
        return downloadLinkEnvironment;
    }

    public void setDownloadLinkEnvironment(String downloadLinkEnvironment) {
        this.downloadLinkEnvironment = downloadLinkEnvironment;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public Config(String env, String configFile){

        Properties prop = new Properties();
        InputStream inputStream = null;

       try {
           inputStream = new FileInputStream(configFile);

           prop.load(inputStream);

           //this.version = prop.getProperty("driverVersion");
           this.versionCheckerUrl = prop.getProperty("versionCheckerUrl");
           this.zipExtension = prop.getProperty("zipExtension");
           this.firstPartOfDownloadLink = prop.getProperty("firstPartOfDownloadLink");
           //this.actionMessage = prop.getProperty("actionMessage");
           this.updateMessage = prop.getProperty("updateMessage");
           this.upToDateMessage = prop.getProperty("upToDateMessage");
           //this.downloadURL = prop.getProperty("downloadURL");
           //this.fileDirAndName = Paths.get(prop.getProperty("fileDirAndName"));
           this.downloadDirectory = Paths.get(prop.getProperty(env+"DownloadDirectory"));
           this.downloadLinkEnvironment = prop.getProperty(env+"DownloadLinkEnvironment");
           this.fileDir = prop.getProperty(env+"FileDir");
           this.pathSeparator = prop.getProperty(env+"PathSeparator");

       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }finally {
           try {
               inputStream.close();
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

    }


    @Override
    public Config configProperties() {
        return this;
    }
}
