package com.javafest.Retailor.Service.Imp;

import com.javafest.Retailor.Service.FileService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImp implements FileService {

    private final String uploadDir = "images/";
    @Override
    public List<String> saveFiles(MultipartFile[] files) throws IOException {
        List<String> filePaths = new ArrayList<>();

        // Ensure the upload directory exists
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        for (MultipartFile file : files) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);
            filePaths.add(fileName); // Save relative path
        }

        return filePaths; // Return the list of file paths
    }

    @Override
    public byte[] loadFileAsResource(String filePath) throws IOException {

        if (!filePath.startsWith(uploadDir)) {
            filePath = uploadDir + filePath;  // Prepend "images/" if not already present
        }

        // Resolve the path and load the file
        Path resolvedPath = Paths.get(filePath).normalize();

        // Check if the file exists
        if (!Files.exists(resolvedPath)) {
            throw new IOException("File not found: " + filePath);
        }

        return Files.readAllBytes(resolvedPath);
    }
    @Override
    public void deleteFile(String fileName) throws IOException {
        // Ensure the file name is relative and does not include "images/"
        if (!fileName.startsWith(uploadDir)) {
            fileName = uploadDir + fileName; // Prepend the directory path
        }

        Path filePath = Paths.get(fileName).normalize();

        // Check if the file exists, then delete it
        if (Files.exists(filePath)) {
            Files.delete(filePath);  // Delete the file from the file system
        } else {
            throw new IOException("File not found to delete: " + fileName);
        }
    }
}
