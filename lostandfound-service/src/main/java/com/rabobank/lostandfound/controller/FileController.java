package com.rabobank.lostandfound.controller;

import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("api/v1/lostandfound/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Operation(summary = "Upload & Store Data", description = "A REST API Admin endpoint to upload the lost items with \n" +
            "details from a file. The application should extract and store the following information \n" +
            "from the uploaded file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The file is uploaded"),
            @ApiResponse(responseCode = "404", description ="File Not Found."),
            @ApiResponse(responseCode = "500", description ="Internal Server Error.")
    })
    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Set<LostItemDto>> upload(@Validated @RequestParam("file")MultipartFile file
            ) throws IOException {

        Set<LostItemDto> setItems = new HashSet<>();
        boolean readPdf = fileService.validateFile(file);
        if(readPdf)
            setItems = fileService.uploadFile(file);
        return new ResponseEntity<>(setItems, HttpStatus.OK);
    }
}
