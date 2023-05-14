package com.example.pdkrestaurant.controller;

import com.example.pdkrestaurant.dtos.LoaiBan.LoaiBanDto;
import com.example.pdkrestaurant.entities.LoaiBan;
import com.example.pdkrestaurant.service.LoaiBan.LoaiBanService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("pdkrestaurant/loaiban")
public class LoaiBanController {
    private final  LoaiBanService loaiBanService;
    private final int size = 2;
    private final String sort = "asc";
    private final String search = "true";
    private final String column = "maLoaiBan";

    @PreAuthorize("hasAnyRole ('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<LoaiBan> create(@Valid @RequestBody LoaiBanDto dto){
        return new ResponseEntity<>(loaiBanService.create(dto), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/getall")
    public ResponseEntity<Page<LoaiBan>> getallpaging(@RequestParam(defaultValue = "") String search,
                                                @RequestParam(defaultValue = "0") int page){
        return new ResponseEntity<>(loaiBanService.filter(search,page,size,sort,column),   HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/update/{id}")
    public ResponseEntity<LoaiBan> update(@PathVariable String id,
                                          @Valid @RequestBody LoaiBanDto dto){
        return new ResponseEntity<>(loaiBanService.update(id,dto),HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("change-status")
    public ResponseEntity<String> changestatus(@RequestParam String id){
        LoaiBan loaiBan=loaiBanService.changeStatus(id);

        return new ResponseEntity<>(String.format("Loại bàn %s da duoc thay doi trang thai thanh %s"
                , loaiBan.getMaLoaiBan(), loaiBan.isTrangThai()), HttpStatus.OK);
    }
}
