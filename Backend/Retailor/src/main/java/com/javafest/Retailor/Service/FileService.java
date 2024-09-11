package com.javafest.Retailor.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface FileService {
    public List<String> saveFiles(MultipartFile[] files) throws IOException;
    public byte[] loadFileAsResource(String fileName) throws IOException;
    public void deleteFile(String fileName) throws IOException;
}
