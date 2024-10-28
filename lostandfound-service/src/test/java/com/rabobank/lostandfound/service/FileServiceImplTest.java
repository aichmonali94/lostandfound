package com.rabobank.lostandfound.service;

import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.exception.FileNotSupportedException;
import com.rabobank.lostandfound.repository.LostItemRepository;
import com.rabobank.lostandfound.service.impl.FileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FileServiceImplTest {

    @InjectMocks
    private FileServiceImpl fileService;

    @Mock
    private LostItemRepository lostItemRepo;

    @Mock
    private LostItemDto lostItemDto;

    @Mock
    private MultipartFile file;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void validateFile_Success() {
        when(file.getContentType()).thenReturn("application/pdf");
        when(file.getOriginalFilename()).thenReturn("ItemName.pdf");
        when(file.isEmpty()).thenReturn(false);

        assertTrue(fileService.validateFile(file));
    }

    @Test
    public void validateFile_EmptyFile() {
        when(file.isEmpty()).thenReturn(true);

        FileNotSupportedException exception = assertThrows(FileNotSupportedException.class, () -> {
            fileService.validateFile(file);
        });

        assertEquals("Please select a file to upload.", exception.getMessage());
    }

    @Test
    public void validateFile_MissingContentType() {
        when(file.getContentType()).thenReturn(null);
        when(file.isEmpty()).thenReturn(false);

        FileNotSupportedException exception = assertThrows(FileNotSupportedException.class, () -> {
            fileService.validateFile(file);
        });

        assertEquals("Content type is missing.", exception.getMessage());
    }

    @Test
    public void validateFile_InvalidContentType() {
        when(file.getContentType()).thenReturn("text/plain");
        when(file.getOriginalFilename()).thenReturn("test.txt");
        when(file.isEmpty()).thenReturn(false);

        FileNotSupportedException exception = assertThrows(FileNotSupportedException.class, () -> {
            fileService.validateFile(file);
        });

        assertEquals("Only PDF files are allowed.", exception.getMessage());
    }
}
