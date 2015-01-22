import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Created by oguzhan.demir on 22.01.2015.
 */
public class DownloadManager implements IDownloadManager {

    private IControlUnit _controlUnit;
    private IDecompressor _decompressor;
    private Constants _constants;

    @Override
    public void getLatestVersion(String versionCheckerUrl, Constants constants) {
        String body = "";
        _constants = constants;
        try {
            URL url = new URL(versionCheckerUrl);
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
        _constants.setVersion(body);
    }

    @Override
    public void downloadLatestDriver(IControlUnit controlUnit,
                                     IDecompressor decompressor, Constants constants) {

        _decompressor = decompressor;
        _controlUnit = controlUnit;
        _constants = constants;
        if (_controlUnit.checkIfLatestOrNot(Constants.downloadDirectory.toFile(), this, _constants)) {

            if (Files.notExists(Constants.downloadDirectory)) {

                new File(Constants.downloadDirectory.toString()).mkdir();

            }
            try {
                URL website = new URL(_constants.getDownloadURL());
                Files.copy(website.openStream(), _constants.getFileDirAndName(), StandardCopyOption.REPLACE_EXISTING);

                _decompressor.decompress(Constants.downloadDirectory.toString(), constants.getFileDirAndName().toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }


        }
    }
}
