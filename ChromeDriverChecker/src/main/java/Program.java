import Config.Config;
import Config.IConfig;

public class Program {
    public static void main(String args[]) {

        IConfig con = new Config(System.getProperty("env"),System.getProperty("configFile"));
        IDecompressor decompressor = new Decompressor(con);
        IFileManager fileManager = new FileManager(con);
        IDownloadManager downloadManager= new DownloadManager(decompressor, fileManager,con);
        Executor executor = new Executor(downloadManager,con);



        executor.Execute();
    }
}
