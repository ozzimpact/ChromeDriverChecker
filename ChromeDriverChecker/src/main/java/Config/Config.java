package Config;

import Interface.ILogger;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by ty-testmac on 26/01/15.
 */
public class Config implements IConfig {

    private String version;
    private String versionCheckerUrl;
    private String zipExtension;
    private String firstPartOfDownloadLink;
    private String actionMessage;
    private String updateMessage;
    private String upToDateMessage;
    private String downloadURL;
    private Path fileDirAndName;
    private Path downloadDirectory;
    private String downloadLinkEnvironment;
    private String fileDir;
    private String pathSeparator;


    @Override
    public String getPathSeparator() {
        return pathSeparator;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getVersionCheckerUrl() {
        return versionCheckerUrl;
    }

    @Override
    public String getZipExtension() {
        return zipExtension;
    }

    @Override
    public String getFirstPartOfDownloadLink() {
        return firstPartOfDownloadLink;
    }

    @Override
    public String getActionMessage() {
        return actionMessage;
    }

    @Override
    public String getUpdateMessage() {
        return updateMessage;
    }

    @Override
    public String getUpToDateMessage() {
        return upToDateMessage;
    }

    @Override
    public String getDownloadURL() {
        return downloadURL;
    }

    @Override
    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
    }

    @Override
    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    @Override
    public Path getFileDirAndName() {
        return fileDirAndName;
    }

    @Override
    public void setFileDirAndName(Path fileDirAndName) {
        this.fileDirAndName = fileDirAndName;
    }

    @Override
    public Path getDownloadDirectory() {
        return downloadDirectory;
    }

    @Override
    public void setDownloadDirectory(Path downloadDirectory) {
        this.downloadDirectory = downloadDirectory;
    }

    @Override
    public String getDownloadLinkEnvironment() {
        return downloadLinkEnvironment;
    }

    @Override
    public void setDownloadLinkEnvironment(String downloadLinkEnvironment) {
        this.downloadLinkEnvironment = downloadLinkEnvironment;
    }

    @Override
    public String getFileDir() {
        return fileDir;
    }

    @Override
    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public Config(String env, String configFile, ILogger logger) {

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
            this.downloadDirectory = Paths.get(prop.getProperty(env + "DownloadDirectory"));
            this.downloadLinkEnvironment = prop.getProperty(env + "DownloadLinkEnvironment");
            this.fileDir = prop.getProperty(env + "FileDir");
            this.pathSeparator = prop.getProperty(env + "PathSeparator");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
