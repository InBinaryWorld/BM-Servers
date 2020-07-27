package dev.szafraniak.bmresource.services;

import dev.szafraniak.bmresource.config.BaseEnvironment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class FileService {

    public boolean removeInvoice(String fileReference) {
        return removeFile(Path.of(BaseEnvironment.INVOICE_BASE_PATH, fileReference).toString());
    }

    public boolean removeFile(String filePath) {
        Path path = Path.of(BaseEnvironment.RESOURCE_BASE_PATH, filePath);
        File file = path.toFile();
        return file.delete();
    }

    private boolean exist(String filePath) {
        Path path = Path.of(BaseEnvironment.RESOURCE_BASE_PATH, filePath);
        File file = path.toFile();
        return file.exists();
    }

    public boolean createFile(String filePath) {
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
