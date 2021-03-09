package com.ldp.vigilantBean.controller.extractor;

import org.springframework.web.multipart.MultipartHttpServletRequest;

@FunctionalInterface
public interface EntityExtractor <T> {

    T extractEntity(MultipartHttpServletRequest request);
}
