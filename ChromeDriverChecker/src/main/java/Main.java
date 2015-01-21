import com.thoughtworks.selenium.webdriven.commands.Open;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Oguzhan on 1/21/2015.
 */

public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();
        manager.downloadLatest();
    }
}
