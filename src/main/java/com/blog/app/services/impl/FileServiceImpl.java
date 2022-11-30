package com.blog.app.services.impl;

import com.blog.app.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile multipartFile) throws IOException {

        // file name
        var name = multipartFile.getOriginalFilename();

        //changing file name
        var randomID = UUID.randomUUID().toString();

        assert name != null;
        var fileName = randomID.concat(name.substring(name.lastIndexOf(".")));

        //full file path
        var filePath = path + File.separator + fileName;

        //create folder if not created
        var file = new File(path);
        if (!file.exists())
            file.mkdir();

        //file copy
        Files.copy(multipartFile.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        return new FileInputStream(fullPath);
    }



}
