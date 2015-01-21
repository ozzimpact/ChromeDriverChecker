import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Oguzhan on 1/21/2015.
 */
public class Manager {


    String version = "";
    Path PATH = Paths.get("C:\\drivers\\");
    Path PATH2 = null;
    String downloadUrl = null;
    byte[] buffer = new byte[1024];


    public void setPath(String version){
        PATH2 = Paths.get("C:\\drivers\\" + version + "v-chrome.zip");
        downloadUrl = "http://chromedriver.storage.googleapis.com/" + version + "/chromedriver_win32.zip";
    }
    public void downloadLatest() {
        if (checkIfLatestOrNot(PATH.toFile())) {

            if (Files.notExists(PATH)) {
                try {

                    Files.createDirectory(PATH);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            try {

                URL website = new URL(downloadUrl);
                Files.copy(website.openStream(), PATH2, StandardCopyOption.REPLACE_EXISTING);
                ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(PATH2.toString()));
                ZipEntry ze = zipInputStream.getNextEntry();
                while (ze != null) {
                    String filename = ze.getName();
                    File newFile = new File(PATH.toString() + "\\" + filename);

                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zipInputStream.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    ze = zipInputStream.getNextEntry();
                }
                zipInputStream.closeEntry();
                zipInputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }
    }

    private boolean checkIfLatestOrNot(File folder) {
        String tempFileName;
        getLatestVersion();
        for (File file : folder.listFiles()) {
            if (file.getName().contains(version))
                return false;
        }
        setPath(version);
        return true;
    }

    private void getLatestVersion() {
        String body = "";
        try {
            URL url = new URL("http://chromedriver.storage.googleapis.com/LATEST_RELEASE");
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            body = IOUtils.toString(in, encoding);
        } catch (MalformedInputException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        version = body;
    }

}
