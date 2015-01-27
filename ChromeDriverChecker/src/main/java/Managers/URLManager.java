package Managers;

import Interface.ILogger;
import Interface.IURLManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLManager implements IURLManager {

    private URL _url;
    private ILogger _logger;

    public URLManager(String url, ILogger logger) {
        _logger = logger;
        try {
            _url = new URL(url);
        } catch (MalformedURLException ex) {
            _logger.warn("URL could not parse correctly: " + ex.toString());
        }
    }

    @Override
    public URLConnection openConnection() {
        URLConnection con = null;
        try {
            con = _url.openConnection();
        } catch (IOException ex) {
            _logger.warn("Could not establish connection: " + ex.toString());
        }
        return con;
    }
}
