package com.example.pdkrestaurant.service.DatBan;

import com.example.pdkrestaurant.dtos.DatBan.DatBanDto;
import com.example.pdkrestaurant.dtos.DatBan.ThongTinDatBanDto;
import com.example.pdkrestaurant.dtos.DatBan.UpdateDatBanDto;
import com.example.pdkrestaurant.entities.DatBan;
import com.example.pdkrestaurant.entities.DatBan;
import com.example.pdkrestaurant.entities.LoaiBan;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DatBanService {
    Page<DatBan> filter(String search,
                        int page, int size, String sort, String column);

    List<DatBan> finAll();

    DatBan findbyId(String id);

    void deleteById(String id);
    // TaiKhoan getUserByEmail(String email);

    DatBan create(DatBanDto dto);

    //TaiKhoan update(String id, UserDto dto, Principal principal);

    DatBan update(String id, UpdateDatBanDto dto);
    DatBan updateThongTinDatCho(String id, ThongTinDatBanDto dto);
    DatBan addThongTinDatBan (String idDatBan, ThongTinDatBanDto dto);
    DatBan changeStatus(String id);
    DatBan updateTrangThai(String id, int statusNumber);
}
