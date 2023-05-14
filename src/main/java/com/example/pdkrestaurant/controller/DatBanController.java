package com.example.pdkrestaurant.controller;

import com.example.pdkrestaurant.dtos.DatBan.DatBanDto;
import com.example.pdkrestaurant.dtos.DatBan.ThongTinDatBanDto;
import com.example.pdkrestaurant.dtos.DatBan.UpdateDatBanDto;
import com.example.pdkrestaurant.dtos.LoaiBan.LoaiBanDto;
import com.example.pdkrestaurant.dtos.user.UpdateUserDto;
import com.example.pdkrestaurant.entities.DatBan;
import com.example.pdkrestaurant.entities.LoaiBan;
import com.example.pdkrestaurant.entities.TaiKhoan;
import com.example.pdkrestaurant.entities.embedded.ThongTinDatBan;
import com.example.pdkrestaurant.service.DatBan.DatBanService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("pdkrestaurant/datban")
public class DatBanController {
    public final DatBanService datBanService;
    private final int size = 2;
    private final String sort = "asc";
    private final String search = "true";
    private final String column = "email";


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/create")
    public ResponseEntity<DatBan> create(@Valid @RequestBody DatBanDto dto){
        return new ResponseEntity<>(datBanService.create(dto), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/addThongTinDatBan/{id}")
    public ResponseEntity<DatBan> create(@PathVariable String id,
            @Valid @RequestBody ThongTinDatBanDto dto){
        return new ResponseEntity<>(datBanService.addThongTinDatBan(id,dto), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getall")
    public ResponseEntity<Page<DatBan>> getallpaging(@RequestParam(defaultValue = "") String search,
                                                      @RequestParam(defaultValue = "0") int page){
        return new ResponseEntity<>(datBanService.filter(search,page,size,sort,column),   HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<DatBan> update(@PathVariable String id,
                                           @Valid @RequestBody UpdateDatBanDto dto
                                         ) {
        return new ResponseEntity<>(datBanService.update(id,dto), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/update-thongTinDatBan/{id}")
    public ResponseEntity<DatBan> updatethongtindatcho(@PathVariable String id,
                                         @Valid @RequestBody ThongTinDatBanDto dto
    ) {
        return new ResponseEntity<>(datBanService.updateThongTinDatCho(id,dto), HttpStatus.OK);
    }


    @PutMapping("/udt-trang-thai/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<DatBan> updateTrangThai(@PathVariable String id,
                                                  @RequestParam int statusNumber) {
//        khac 1,2,3 : HUY
//        1 : DANG_THUC_HIEN
//        2 : DAT_BAN
//        3 : HOAN_THANH
        return new ResponseEntity<>(datBanService.updateTrangThai(id, statusNumber), HttpStatus.OK);
    }

    @PostMapping("/change-status")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> changeStatus(@RequestParam String id) {
        datBanService.changeStatus(id);
        DatBan datBan = datBanService.findbyId(id);
        return new ResponseEntity<>(String.format("Bàn đã đặt có email  %s đã được đổi sang %s"
                , datBan.getEmail(), datBan.isTrangThai()), HttpStatus.OK);
    }
}
