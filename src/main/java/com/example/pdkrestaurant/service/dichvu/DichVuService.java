package com.example.pdkrestaurant.service.dichvu;

import com.example.pdkrestaurant.dtos.DatBan.DatBanDto;

import com.example.pdkrestaurant.dtos.DatBan.ThongTinDatBanDto;
import com.example.pdkrestaurant.dtos.dichvu.DichVuDto;
import com.example.pdkrestaurant.dtos.dichvu.GiaDichVuDto;
import com.example.pdkrestaurant.dtos.dichvu.UpdateDichVuDto;
import com.example.pdkrestaurant.entities.DatBan;
import com.example.pdkrestaurant.entities.DichVu;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DichVuService {
    Page<DichVu> filter(String search,
                        int page, int size, String sort, String column);

    List<DichVu> finAll();

    DichVu findById(String id);

    void deleteById(String id);
    // TaiKhoan getUserByEmail(String email);

    DichVu create(DichVuDto dto);

    //TaiKhoan update(String id, UserDto dto, Principal principal);
    DichVu addGiaDichVu (String idDV, GiaDichVuDto dto);
    DichVu updateGiaDichVu(String id, GiaDichVuDto dto);
    DichVu update(String id, UpdateDichVuDto dto);

    DichVu changeStatus(String id);
}
