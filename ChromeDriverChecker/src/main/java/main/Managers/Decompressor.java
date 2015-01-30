package main.Managers;

import main.Config.IConfig;
import main.Interface.IDecompressor;
import main.Interface.ILogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by oguzhan.demir on 22.01.2015.
 */
public class Decompressor implements IDecompressor {


    private IConfig _config;
    private ILogger _logger;

    public Decompressor(IConfig con, ILogger logger) {
        _config = con;
        _logger = logger;
    }

    @Override
    public void decompress(String zipFilePath, String extractedFilePath) {
        _logger.trace("Decompress input, Zip file path: " + zipFilePath + ", Extracted File Path: " + extractedFilePath);
        byte[] buffer = new byte[1024];
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(extractedFilePath));
            ZipEntry ze = zipInputStream.getNextEntry();
            while (ze != null) {
                String filename = ze.getName();
                File newFile = new File(zipFilePath + _config.getPathSeparator() + filename);

                //This statement is for MAC. MAC needs this permisson to extract file as a executable one.
                if (System.getProperties().getProperty("env").equals("mac"))
                    Runtime.getRuntime().exec("chmod u+x " + zipFilePath + _config.getPathSeparator() + filename);

                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zipInputStream.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                ze = zipInputStream.getNextEntry();
            }
            zipInputStream.closeEntry();
            zipInputStream.close();
        } catch (IOException ex) {
            _logger.error("Something went wrong while decompressing process: " + ex.toString());
            System.exit(1);
        }
        _logger.info("File is decompressed successfully.");
    }
}
