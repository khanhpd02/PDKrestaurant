package com.example.pdkrestaurant.service.User;

import com.example.pdkrestaurant.dtos.user.RegisterDto;
import com.example.pdkrestaurant.dtos.user.SigupDto;
import com.example.pdkrestaurant.dtos.user.UpdateUserDto;
import com.example.pdkrestaurant.entities.TaiKhoan;
import com.example.pdkrestaurant.exceptions.InvalidException;
import com.example.pdkrestaurant.exceptions.NotFoundException;
import com.example.pdkrestaurant.repositories.UserRepository;
import com.example.pdkrestaurant.utils.EnumRole;
import com.example.pdkrestaurant.utils.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<TaiKhoan> filter(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return userRepository.findByNameContainingOrEmailContainingAllIgnoreCase(search,search, pageable);
    }

    @Override
    public List<TaiKhoan> finAll() {
        return userRepository.getAlls();
    }

    @Override
    public TaiKhoan getUser(String id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("User with id %s does not exist",id)));
    }


    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public TaiKhoan getUserByEmail(String email) {
        return null;
    }

    @Override
    public TaiKhoan create(SigupDto dto, Principal principal) {
        TaiKhoan taiKhoan = new TaiKhoan();

        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên Không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getPassword())) {
            throw new InvalidException("Nhập password đi!");
        }
        if (ObjectUtils.isEmpty(dto.getDienThoai())) {
            throw new InvalidException("Sdt ko dc để trống!");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new InvalidException(String.format("Email đã tồn tại, vui lòng nhập email khác",
                    dto.getEmail()));

        }
            taiKhoan.setName(dto.getName());
            taiKhoan.setDienThoai(dto.getDienThoai());
            taiKhoan.setEmail(dto.getEmail());
            taiKhoan.setPassword(dto.getPassword());
            taiKhoan.setRoles(Arrays.asList(EnumRole.ROLE_ADMIN.name()));

        return userRepository.save(taiKhoan);

    }



    @Override
    public TaiKhoan update(String id, UpdateUserDto dto) {

        TaiKhoan taiKhoan = userRepository.findById(id).orElse(null);
        if (taiKhoan==null)
            throw new NotFoundException("Id này ko tồn tại");

        if (ObjectUtils.isEmpty(dto.getName())) {
            throw new InvalidException("Tên Không được bỏ trống");
        }
        if (ObjectUtils.isEmpty(dto.getPassword())) {
            throw new InvalidException("Mật khẩu không được để trống");
        }


        taiKhoan.setName(dto.getName());
        //taiKhoan.setEmail(dto.getEmail());
        taiKhoan.setDienThoai(dto.getDienThoai());
        taiKhoan.setPassword(dto.getPassword());

        return userRepository.save(taiKhoan);
    }




    @Override
    public TaiKhoan changeStatus(String id) {

        Optional<TaiKhoan> taiKhoan = userRepository.findById(id);
        if(!taiKhoan.isPresent())
            throw new NotFoundException(String.format("Tài khoản có id %s không tồn tại", id));
        taiKhoan.get().setTrangThai(!taiKhoan.get().isTrangThai());
        return userRepository.save(taiKhoan.get());
    }

    @Override
    public TaiKhoan signup(RegisterDto registerDto) {
        TaiKhoan taiKhoan = new TaiKhoan();
        if (ObjectUtils.isEmpty(registerDto.getEmail())) {
            throw new InvalidException("Email không được để trống");
        }
        if (ObjectUtils.isEmpty(registerDto.getPassword())) {
            throw new InvalidException("Mật khẩu không được để trống");
        }
        if (ObjectUtils.isEmpty(registerDto.getName())) {
            throw new InvalidException("Name không được để trống");
        }
        if (ObjectUtils.isEmpty(registerDto.getDienThoai())) {
            throw new InvalidException("So dien thoai không được để trống");
        }
        if(userRepository.existsByEmail(registerDto.getEmail()))
            throw new InvalidException(String.format("User có email %s đã tồn tại", registerDto.getEmail()));

        taiKhoan.setName(registerDto.getName());
        taiKhoan.setEmail(registerDto.getEmail());
        taiKhoan.setRoles(Arrays.asList(EnumRole.ROLE_USER.name()));
        taiKhoan.setTrangThai(true);
        taiKhoan.setPassword(registerDto.getPassword());
        taiKhoan.setDienThoai(registerDto.getDienThoai());
        return userRepository.save(taiKhoan);
    }
}
