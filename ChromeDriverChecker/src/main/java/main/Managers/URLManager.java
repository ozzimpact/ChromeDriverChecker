package main.Managers;

import main.Interface.ILogger;
import main.Interface.IURLManager;

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
            throw new ChromeDriverException("URL could not parse correctly: ",ex.getCause());
        }
    }

    @Override
    public URLConnection openConnection() {
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) _url.openConnection();
            int statusCode = con.getResponseCode();
            if (statusCode != 200) {
                _logger.error("Connection could not be established.Status Code: " + statusCode + "," + "url: " + _url);
                throw new ChromeDriverException("Connection could not be established.Status Code: " + statusCode + "," + "url: " + _url);
            }
        } catch (IOException ex) {
            _logger.error("Check your internet connection.");
            throw new ChromeDriverException("Check your internet connection.", ex.getCause());
        }
        _logger.trace("Open connection return value: " + con.toString());
        return con;
    }
}

