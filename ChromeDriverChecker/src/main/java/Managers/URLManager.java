package Managers;

import Interface.ILogger;
import Interface.IURLManager;

import java.io.IOException;
import java.net.HttpURLConnection;
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
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) _url.openConnection();
            int statusCode = con.getResponseCode();
            if (statusCode != 200) {
                _logger.error("Connection could not be established.Status Code: " + statusCode + "," + _url);
                System.exit(1);
            }
        } catch (IOException ex) {
            _logger.error("Check your internet connection.");
            System.exit(1);
        }
        _logger.trace("Open connection return value: " + con.toString());
        return con;
    }
}

