package main.Config;

import java.nio.file.Path;

/**
 * Created by ty-testmac on 26/01/15.
 */
public interface IConfig {

    public String getPathSeparator();

    public String getVersion();

    public void setVersion(String version);

    public String getVersionCheckerUrl();

    public String getZipExtension();

    public String getFirstPartOfDownloadLink();

    public String getActionMessage();
    public String getUpdateMessage();

    public String getUpToDateMessage();

    public String getDownloadURL();

    public void setActionMessage(String actionMessage);

    public void setDownloadURL(String downloadURL);

    public Path getFileDirAndName();

    public void setFileDirAndName(Path fileDirAndName);

    public Path getDownloadDirectory();

    public void setDownloadDirectory(Path downloadDirectory);

    public String getDownloadLinkEnvironment();

    public void setDownloadLinkEnvironment(String downloadLinkEnvironment) ;

    public String getFileDir();

    public void setFileDir(String fileDir);


}
