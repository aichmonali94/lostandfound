package com.rabobank.lostandfound.repository;

import com.rabobank.lostandfound.model.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Long> {
}
