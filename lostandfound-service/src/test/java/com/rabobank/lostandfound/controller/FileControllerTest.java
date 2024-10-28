package com.rabobank.lostandfound.controller;

import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.service.FileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class FileControllerTest {

   /* @InjectMocks
    private ClaimDetailsController claimDetailsController;

    @Mock
    private FileService fileService;

    //@Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(claimDetailsController).build();
    }

    @Test
    public void upload_Success() throws Exception {
        Set<LostItemDto> expectedItems = new HashSet<>();
        expectedItems.add(new LostItemDto());

        MultipartFile mockFile = createMockFile("ItemName.pdf");

        when(fileService.validateFile(mockFile)).thenReturn(true);
        when(fileService.uploadFile(mockFile)).thenReturn(expectedItems);

        mockMvc.perform(multipart("/api/v1/lostandfound/claimdetails/upload")
                        .file("ItemName.pdf", mockFile.getBytes())
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1))); // Adjust based on expected size

        verify(fileService).validateFile(mockFile);
        verify(fileService).uploadFile(mockFile);
    }

    @Test
    public void upload_InvalidFileType() throws Exception {
        MultipartFile mockFile = createMockFile("ItemName.docx"); // Invalid type

        when(fileService.validateFile(mockFile)).thenReturn(false);

        mockMvc.perform(multipart("/api/v1/lostandfound/claimdetails/upload")
                        .file("file", mockFile.getBytes())
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());

        verify(fileService).validateFile(mockFile);
    }

    @Test
    public void upload_FileProcessingException() throws Exception {
        MultipartFile mockFile = createMockFile("ItemName.pdf");

        when(fileService.validateFile(mockFile)).thenReturn(true);
        when(fileService.uploadFile(mockFile)).thenThrow(new IOException("File processing error"));

        mockMvc.perform(multipart("/api/v1/lostandfound/claimdetails/upload")
                        .file("file", mockFile.getBytes())
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isInternalServerError()); // Expecting internal server error

        verify(fileService).validateFile(mockFile);
        verify(fileService).uploadFile(mockFile);
    }

    private MultipartFile createMockFile(String filename) throws IOException {
        File file = new File("src/test/resources/" + filename);
        FileInputStream input = new FileInputStream(file);
        return new MockMultipartFile("file", filename, MediaType.APPLICATION_PDF_VALUE, input);
    }*/
}
