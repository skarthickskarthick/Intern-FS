package com.example.CronZipMailScheduler;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.zip.*;

@Service
public class ZipService {
    private static final String ZIP_FILE_PATH = "C:\\Users\\User\\Downloads\\PVP20%20DBMS%20UNIT-1.pdf";

    public String zipFile(String filePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(ZIP_FILE_PATH);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(filePath)) {

            ZipEntry zipEntry = new ZipEntry(new File(filePath).getName());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
        }
        return ZIP_FILE_PATH;
    }
}
