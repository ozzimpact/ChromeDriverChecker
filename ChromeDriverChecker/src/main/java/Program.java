public class Program {
    public static void main(String args[]) {

        IDecompressor decompressor = new Decompressor();
        IFileManager fileManager = new FileManager();
        IDownloadManager downloadManager= new DownloadManager(decompressor, fileManager);
        Executor executor = new Executor(downloadManager);

        executor.Execute();
    }
}
