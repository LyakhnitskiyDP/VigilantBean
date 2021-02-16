package com.ldp.vigilantBean.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    boolean store(MultipartFile file, String path);

    boolean store(MultipartFile file, String path, String fileName);

}
