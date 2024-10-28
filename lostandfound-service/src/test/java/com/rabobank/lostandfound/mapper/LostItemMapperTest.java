package com.rabobank.lostandfound.mapper;
import com.rabobank.lostandfound.dto.LostItemDto;
import com.rabobank.lostandfound.model.LostItem;
import com.rabobank.lostandfound.mapper.LostItemMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LostItemMapperTest {

        private final LostItemMapper lostItemMapper = Mappers.getMapper(LostItemMapper.class);

        @Test
        public void testMapToLostItemDto() {
            // Given
            LostItem lostItem = new LostItem();
            lostItem.setLostItemEntryId(1L);
            lostItem.setItemName("Laptop");
            lostItem.setQuantity(3);
            lostItem.setPlace("Office");

            // When
            LostItemDto lostItemDto = lostItemMapper.mapToLostItemDto(lostItem);

            // Then
            assertThat(lostItemDto).isNotNull();
            assertThat(lostItemDto.getLostItemEntryId()).isEqualTo(lostItem.getLostItemEntryId());
            assertThat(lostItemDto.getItemName()).isEqualTo(lostItem.getItemName());
            assertThat(lostItemDto.getQuantity()).isEqualTo(lostItem.getQuantity());
            assertThat(lostItemDto.getPlace()).isEqualTo(lostItem.getPlace());
        }

        @Test
        public void testMapToLostItemEntity() {
            // Given
            LostItemDto lostItemDto = new LostItemDto();
            lostItemDto.setLostItemEntryId(1L);
            lostItemDto.setItemName("Laptop");
            lostItemDto.setQuantity(3);
            lostItemDto.setPlace("Office");

            // When
            LostItem lostItem = lostItemMapper.mapToLostItemEntity(lostItemDto);

            // Then
            assertThat(lostItem).isNotNull();
            assertThat(lostItem.getLostItemEntryId()).isEqualTo(lostItemDto.getLostItemEntryId());
            assertThat(lostItem.getItemName()).isEqualTo(lostItemDto.getItemName());
            assertThat(lostItem.getQuantity()).isEqualTo(lostItemDto.getQuantity());
            assertThat(lostItem.getPlace()).isEqualTo(lostItemDto.getPlace());
        }
    }
