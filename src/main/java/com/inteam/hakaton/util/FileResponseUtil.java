package com.inteam.hakaton.util;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class FileResponseUtil {

    public static Map<String, String> getResponse(MultipartFile file) {
        Map<String, String> response = new HashMap<>();

        response.put("fileName", file.getOriginalFilename());
        response.put("fileSize", String.valueOf(file.getSize()));
        response.put("fileContentType", file.getContentType());

        return response;
    }
}
