package com.example.pdkrestaurant.controller;

import com.example.pdkrestaurant.dtos.DatBan.DatBanDto;
import com.example.pdkrestaurant.dtos.DatBan.ThongTinDatBanDto;
import com.example.pdkrestaurant.dtos.DatBan.UpdateDatBanDto;
import com.example.pdkrestaurant.dtos.dichvu.DichVuDto;
import com.example.pdkrestaurant.dtos.dichvu.GiaDichVuDto;
import com.example.pdkrestaurant.dtos.dichvu.UpdateDichVuDto;
import com.example.pdkrestaurant.entities.DatBan;
import com.example.pdkrestaurant.entities.DichVu;
import com.example.pdkrestaurant.service.dichvu.DichVuService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("pdkrestaurant/dichvu")
public class DichVuController {
    private final int size = 2;
    private final String sort = "asc";
    private final String search = "true";
    private final String column = "maDichVu";
    private final DichVuService dichVuService;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<DichVu> create(@Valid @RequestBody DichVuDto dto){
        return new ResponseEntity<>(dichVuService.create(dto), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/getall")
    public ResponseEntity<Page<DichVu>> getallpaging(@RequestParam(defaultValue = "") String search,
                                                     @RequestParam(defaultValue = "0") int page){
        return new ResponseEntity<>(dichVuService.filter(search,page,size,sort,column),   HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/addGiaDichVu/{id}")
    public ResponseEntity<DichVu> create(@PathVariable String id,
                                         @Valid @RequestBody GiaDichVuDto dto){
        return new ResponseEntity<>(dichVuService.addGiaDichVu(id,dto), HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<DichVu> update(@PathVariable String id,
                                         @Valid @RequestBody UpdateDichVuDto dto
    ) {
        //update mã dịch vụ tên dịch vụ và nội dung
        return new ResponseEntity<>(dichVuService.update(id,dto), HttpStatus.OK);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PutMapping("/update-giaDichVu/{id}")
    public ResponseEntity<DichVu> updatethongtindatcho(@PathVariable String id,
                                                       @Valid @RequestBody GiaDichVuDto dto
    ) {
        return new ResponseEntity<>(dichVuService.updateGiaDichVu(id,dto), HttpStatus.OK);
    }

    @PostMapping("/change-status")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String> changeStatus(@RequestParam String id) {
        dichVuService.changeStatus(id);
        DichVu dichVu = dichVuService.findById(id);
        return new ResponseEntity<>(String.format("dich vu co ma  %s da duoc doi sang %s"
                , dichVu.getMaDichVu(), dichVu.isTrangThai()), HttpStatus.OK);
    }
}
