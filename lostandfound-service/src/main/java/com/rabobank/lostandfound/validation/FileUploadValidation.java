package com.rabobank.lostandfound.validation;

import com.rabobank.lostandfound.exception.FileNotSupportedException;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Component
public class FileUploadValidation {

    public boolean validateFile(MultipartFile file) {

        String contentType = file.getContentType();
        String filename = file.getOriginalFilename();

        if (file.isEmpty()) {
            throw new FileNotSupportedException("Please select a file to upload.", 600, LocalDateTime.now());
        }

        if (contentType == null) {
            throw new FileNotSupportedException("Content type is missing.", 602, LocalDateTime.now());
        }

        return switch (contentType) {
            case "application/pdf" -> true;
            case "text/plain" -> throw new FileNotSupportedException("Only PDF files are allowed.", 601, LocalDateTime.now());
            default -> {
                if (filename != null && !filename.endsWith(".pdf")) {
                    throw new FileNotSupportedException("Only PDF files are allowed.", 601, LocalDateTime.now());
                }
                yield true;
            }
        };
    }
}
