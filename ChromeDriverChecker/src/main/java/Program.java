import Config.Config;
import Config.IConfig;
import Interface.IDecompressor;
import Interface.IDownloadManager;
import Interface.IFileManager;
import Managers.Decompressor;
import Managers.DownloadManager;
import Managers.Executor;
import Managers.FileManager;

public class Program {
    public static void main(String args[]) {

        IConfig con = new Config(System.getProperty("env"), System.getProperty("configFile"));
        IDecompressor decompressor = new Decompressor(con);
        IFileManager fileManager = new FileManager(con);
        IDownloadManager downloadManager = new DownloadManager(decompressor, fileManager, con);
        Executor executor = new Executor(downloadManager, con);


        executor.Execute();
    }
}
