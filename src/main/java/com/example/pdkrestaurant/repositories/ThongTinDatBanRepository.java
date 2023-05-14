package com.example.pdkrestaurant.repositories;

import com.example.pdkrestaurant.entities.embedded.ThongTinDatBan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThongTinDatBanRepository extends MongoRepository<ThongTinDatBan,String> {
}
