package com.example.pdkrestaurant.repositories;

import com.example.pdkrestaurant.entities.DatBan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DatBanRepository extends MongoRepository<DatBan,String> {
    Page<DatBan> findByEmailContainingOrIdContainingAllIgnoreCase(String search, String search1, Pageable pageable);
}
