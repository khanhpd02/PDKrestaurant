package com.example.pdkrestaurant.repositories;

import com.example.pdkrestaurant.entities.LoaiBan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LoaiBanRepository extends MongoRepository<LoaiBan,String> {
    boolean existsByMaLoaiBan(String maLoaiBan);
    Page<LoaiBan> findByMaLoaiBanContainingOrTenLoaiBanContainingAllIgnoreCase(String search, String search1, Pageable pageable);

    LoaiBan findLoaiBanByMaLoaiBan(String id);
}
