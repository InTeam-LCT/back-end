package com.inteam.hakaton.controller;

import com.inteam.hakaton.model.dto.DocumentTypeEnum;
import com.inteam.hakaton.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static com.inteam.hakaton.util.FileResponseUtil.getResponse;

@RestController
@RequestMapping("/v0/files")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileController {

    private final FileService fileService;

    @Async
    @PostMapping("/{numberSheet}/{skipRow}/{documentType}")
    public CompletableFuture<ResponseEntity<Map<String, String>>> upload(@RequestPart MultipartFile file,
                                                                         @PathVariable Integer numberSheet,
                                                                         @PathVariable DocumentTypeEnum documentType,
                                                                         @PathVariable Integer skipRow) {

        if (file == null || file.getSize() == 0) {
            return CompletableFuture
                    .completedFuture(
                            ResponseEntity
                                    .badRequest()
                                    .body(
                                            Collections.singletonMap("Warning", "Файл пуст или отсутствует")
                                    )
                    );
        }

        return CompletableFuture.supplyAsync(() -> {
            fileService.save(file, numberSheet, documentType, skipRow);
            return ResponseEntity.ok(getResponse(file));
        });
    }

}
