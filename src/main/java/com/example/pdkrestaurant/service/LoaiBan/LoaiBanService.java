package com.example.pdkrestaurant.service.LoaiBan;

import com.example.pdkrestaurant.dtos.LoaiBan.LoaiBanDto;
import com.example.pdkrestaurant.entities.LoaiBan;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface LoaiBanService {
    Page<LoaiBan> filter(String search,
                         int page, int size, String sort, String column);

    List<LoaiBan> finAll();

    LoaiBan getLoaiBanByMaLoaiBan(String id);

    void deleteById(String id);
    // TaiKhoan getUserByEmail(String email);

    LoaiBan create(LoaiBanDto dto);

    //TaiKhoan update(String id, UserDto dto, Principal principal);

    LoaiBan update(String id, LoaiBanDto dto);

    LoaiBan changeStatus(String id);
}
