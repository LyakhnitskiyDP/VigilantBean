package com.ldp.vigilantBean.controller.entityExtractor;

import org.springframework.web.multipart.MultipartHttpServletRequest;

@FunctionalInterface
public interface EntityExtractor <T> {

    T extractEntity(MultipartHttpServletRequest request);
}
