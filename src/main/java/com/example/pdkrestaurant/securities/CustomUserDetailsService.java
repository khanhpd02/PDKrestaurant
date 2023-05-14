package com.example.pdkrestaurant.securities;

import com.example.pdkrestaurant.entities.TaiKhoan;
import com.example.pdkrestaurant.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Created by: IntelliJ IDEA
 * User      : thangpx
 * Date      : 8/29/22
 * Time      : 15:54
 * Filename  : TaiKhoanDetailsService
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public JwtUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        TaiKhoan user = userRepository.getUser(email).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Tài khoản có email %s không tồn tại", email)));
        return getUserDetails(user);
    }

    private JwtUserDetails getUserDetails(TaiKhoan user) {
        return new JwtUserDetails(
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()),
                user.isTrangThai()
        );
    }
}