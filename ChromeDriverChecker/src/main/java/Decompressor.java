import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by oguzhan.demir on 22.01.2015.
 */
public class Decompressor implements IDecompressor{


    @Override
    public void decompress(String zipFilePath, String extractedFilePath) {
        byte[] buffer = new byte[1024];
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(extractedFilePath.toString()));
            ZipEntry ze = zipInputStream.getNextEntry();
            while (ze != null) {
                String filename = ze.getName();
                File newFile = new File(zipFilePath + "\\" + filename);

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
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}