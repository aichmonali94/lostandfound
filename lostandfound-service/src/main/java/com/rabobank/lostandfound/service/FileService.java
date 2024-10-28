package com.rabobank.lostandfound.service;

import com.rabobank.lostandfound.dto.LostItemDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

public interface FileService {

    Set<LostItemDto> uploadFile(MultipartFile file) throws IOException;
    boolean validateFile(MultipartFile file);
}
