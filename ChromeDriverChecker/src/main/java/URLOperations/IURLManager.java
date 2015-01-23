package URLOperations;

import java.net.URL;
import java.net.URLConnection;

/**
 * Created by oguzhan.demir on 23.01.2015.
 */
public interface IURLManager {

    URLConnection openConnection(URL url);

}
