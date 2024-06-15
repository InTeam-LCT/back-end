package com.inteam.hakaton.service;

import com.inteam.hakaton.model.dto.DocumentTypeEnum;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void save(MultipartFile file, Integer numberSheet, DocumentTypeEnum documentType, Integer skipRow);
}
