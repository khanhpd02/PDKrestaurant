package com.example.pdkrestaurant.controller;


import com.example.pdkrestaurant.dtos.user.SigupDto;
import com.example.pdkrestaurant.dtos.user.UpdateUserDto;
import com.example.pdkrestaurant.entities.TaiKhoan;
import com.example.pdkrestaurant.service.User.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pdkrestaurant/user")
public class TaiKhoanController {
    public final UserService userService;
    private final int size = 2;
    private final String sort = "asc";
    private final String search = "true";
    private final String column = "name";

    //Lấy Tài khoản có sẵn trong PetLoveApplicaton để create
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Create user")
    @PostMapping("/createAdmin")
    public ResponseEntity<TaiKhoan> create(@Valid @RequestBody SigupDto dto,
                                             Principal principal) {
        return new ResponseEntity<>(userService.create(dto, principal), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Update user")
    @PutMapping("/update/{id}")
    public ResponseEntity<TaiKhoan> update(@PathVariable String id,
                                           @Valid @RequestBody UpdateUserDto dto) {
        return new ResponseEntity<>(userService.update(id,dto), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Get All USER Paging")
    @GetMapping("allPaging")
    public ResponseEntity<Page<TaiKhoan>> allPaging(@RequestParam(defaultValue = "") String search,
                                                    @RequestParam(defaultValue = "0") int page){
        return new ResponseEntity<>(userService.filter(search,page,size,sort,column), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Get All USER")
    @GetMapping("/all")
    public List<TaiKhoan> getall() {

        return userService.finAll();
    }
    @PostMapping("/change-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> changeStatus(@RequestParam String id){
        userService.changeStatus(id);

        TaiKhoan taiKhoan = userService.getUser(id);
        return new ResponseEntity<>(String.format("User %s da duoc thay doi trang thai thanh %s"
                , taiKhoan.getName(), taiKhoan.isTrangThai()), HttpStatus.OK);
    }






}
