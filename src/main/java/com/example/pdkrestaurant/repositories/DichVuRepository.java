package com.example.pdkrestaurant.repositories;

import com.example.pdkrestaurant.entities.DichVu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DichVuRepository extends MongoRepository<DichVu,String> {
    boolean existsByMaDichVu(String maLoaiBan);

    Page<DichVu> findByTenDichVuContainingOrMaDichVuContainingAllIgnoreCase(String search, String search1, Pageable pageable);

    Page<DichVu> findByMaDichVuContainingOrTenDichVuContainingAllIgnoreCase(String search, String search1, Pageable pageable);
}
