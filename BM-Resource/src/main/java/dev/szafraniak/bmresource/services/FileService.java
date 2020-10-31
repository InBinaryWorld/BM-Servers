package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.config.BaseEnvironment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class FileService {

    public static boolean removeInvoice(String fileReference) {
        String path = getInvoicePath(fileReference);
        return removeFile(path);
    }

    public static boolean removeFile(String path) {
        File file = Path.of(path).toFile();
        return file.delete();
    }

    public static String getInvoicePath(String fileName) {
        return Path.of(BaseEnvironment.RESOURCE_BASE_PATH,
                BaseEnvironment.INVOICE_BASE_PATH, fileName).toString();
    }

    private static boolean exist(String filePath) {
        Path path = Path.of(BaseEnvironment.RESOURCE_BASE_PATH, filePath);
        File file = path.toFile();
        return file.exists();
    }

    public static boolean createFile(String filePath) {
        Path path = Path.of(BaseEnvironment.RESOURCE_BASE_PATH, filePath);
        File file = path.toFile();
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
