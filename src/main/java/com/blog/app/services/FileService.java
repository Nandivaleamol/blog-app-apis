package com.blog.app.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String uploadImage(String path, MultipartFile multipartFile) throws IOException;

    InputStream getResource(String path, String fileName) throws FileNotFoundException;
}
