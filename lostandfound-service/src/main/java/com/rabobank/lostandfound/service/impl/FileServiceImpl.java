package com.rabobank.lostandfound.service.impl;

import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.exception.FileNotSupportedException;
import com.rabobank.lostandfound.mapper.LostItemMapper;
import com.rabobank.lostandfound.model.LostItem;
import com.rabobank.lostandfound.repository.LostItemRepository;
import com.rabobank.lostandfound.service.FileService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private LostItemRepository lostItemRepo;

    @Autowired
    private LostItemDto lostItemDto;



    @Override
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

    @Override
    public Set<LostItemDto> uploadFile(MultipartFile file) throws IOException {

        Set<LostItemDto> setItems = new HashSet<>();
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            Arrays.stream(text.split("\n"))
                    .map(line -> line.split(","))
                    .filter(details -> details.length == 3)
                    .forEach(details -> {
                        String[] parts = Arrays.stream(details).map(s -> s.split(": ")[1])
                                .toArray(String[]::new);
                        String itemName = parts[0].trim();
                        String quantityStr = parts[1].trim();
                        String place = parts[2].trim();
                        try {
                            LostItem item = new LostItem();
                            item.setItemName(itemName);
                            item.setQuantity(Integer.parseInt(quantityStr));
                            item.setPlace(place);
                            lostItemRepo.save(item);

                            LostItemDto lostItemDto = LostItemMapper.INSTANCE.mapToLostItemDto(item);
                            setItems.add(lostItemDto);

                        } catch (NumberFormatException e) {
                            throw new RuntimeException("Invalid quantity format in line: " + String.join(",", details), e);
                        }
                    });
        }catch (IOException e) {
            throw new IOException("Failed to process PDF file", e);
        }

        return setItems;
    }
}
