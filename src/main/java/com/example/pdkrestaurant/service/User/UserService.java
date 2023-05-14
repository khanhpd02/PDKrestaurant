package com.example.pdkrestaurant.service.User;

import com.example.pdkrestaurant.dtos.user.RegisterDto;
import com.example.pdkrestaurant.dtos.user.SigupDto;
import com.example.pdkrestaurant.dtos.user.UpdateUserDto;
import com.example.pdkrestaurant.entities.TaiKhoan;
import org.springframework.data.domain.Page;

import java.security.Principal;
import java.util.List;

public interface UserService {

    Page<TaiKhoan> filter(String search,
                      int page, int size, String sort, String column);

    List<TaiKhoan> finAll();

    TaiKhoan getUser(String id);

    void deleteById(String id);
    TaiKhoan getUserByEmail(String email);

    TaiKhoan create(SigupDto dto, Principal principal);

    //TaiKhoan update(String id, UserDto dto, Principal principal);

    TaiKhoan update(String id, UpdateUserDto dto);

    TaiKhoan changeStatus(String id);
    TaiKhoan signup(RegisterDto registerDto);
}
