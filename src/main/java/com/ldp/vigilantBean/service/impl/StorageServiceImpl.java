package com.ldp.vigilantBean.service.impl;

import com.ldp.vigilantBean.service.StorageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
class StorageServiceImpl implements StorageService {

    private static final Logger log =
            LogManager.getLogger(StorageServiceImpl.class.getName());

    @Override
    public boolean store(MultipartFile file,
                         String path,
                         String fileName) {

        if (file == null || file.isEmpty()) return false;

        try {

            file.transferTo(
                    new File(path + System.getProperty("file.separator") + fileName)
            );

            return true;
        } catch (IOException e) {

            log.error("Exception while storing new file: " + file.getOriginalFilename(), e);

            return false;
        }
    }

    @Override
    public boolean store(MultipartFile file, String path) {
        return store(file, path, file.getOriginalFilename());
    }
}
