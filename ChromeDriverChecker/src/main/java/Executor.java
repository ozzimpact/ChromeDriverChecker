/**
 * Created by Oguzhan on 1/21/2015.
 */

public class Executor {

    private static IDecompressor _decompressor;
    private static IDownloadManager _downloadManager;
    private static IControlUnit _controlUnit;
    private static Constants _constant;
    public static void main(String args[]){

        Executor executor = new Executor();
        _decompressor = new Decompressor();
        _downloadManager = new DownloadManager();
        _controlUnit = new ControlUnit();
        _constant = new Constants();
        executor.Execute(_downloadManager,_controlUnit,_decompressor,_constant);
    }

    public void Execute(IDownloadManager downloadManager,IControlUnit controlUnit,IDecompressor decompressor,Constants constants){

        _constant = constants;
        downloadManager.downloadLatestDriver(controlUnit,decompressor,_constant);
    }



}
