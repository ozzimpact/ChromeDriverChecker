package Managers;

import Interface.IURLManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLManager implements IURLManager {

    private URL _url;

    public URLManager(String url) {
        try {
            _url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public URLConnection openConnection() {
        URLConnection con = null;
        try {
            con = _url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return con;
    }
}
