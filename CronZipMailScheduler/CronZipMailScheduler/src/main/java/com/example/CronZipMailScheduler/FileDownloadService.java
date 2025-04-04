package com.example.CronZipMailScheduler;



import org.springframework.stereotype.Service;
import java.io.*;
import java.net.URL;
import java.nio.file.*;

@Service
public class FileDownloadService {
    private static final String FILE_URL = "https://www.pvpsiddhartha.ac.in/dep_it/lecture%20notes/2-2-23/DBMS/PVP20%20DBMS%20UNIT-1.pdf";
    private static final String DOWNLOAD_PATH = "C:\\Users\\User\\Downloads\\PVP20%20DBMS%20UNIT-1.pdf";

    public String downloadFile() throws IOException {
        URL url = new URL(FILE_URL);
        Path targetPath = Paths.get(DOWNLOAD_PATH);
        Files.createDirectories(targetPath.getParent());
        try (InputStream in = url.openStream()) {
            Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
        return DOWNLOAD_PATH;
    }
}
