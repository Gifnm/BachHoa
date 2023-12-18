package com.spring.main.Service;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
    @Autowired
    ServletContext app;

    public File save(MultipartFile file, String folder) {
        System.out.println(app.getRealPath(folder));
        File dir = new File(app.getRealPath(folder));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // rename the image
        // String s = System.currentTimeMillis() + file.getOriginalFilename();
        String name = file.getOriginalFilename();
        // save file to server
        try {
            File savedFile = new File(dir, name);
            file.transferTo(savedFile);
            System.out.println(savedFile.getAbsolutePath());
            return savedFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
