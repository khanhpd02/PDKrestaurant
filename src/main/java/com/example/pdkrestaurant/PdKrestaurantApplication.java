package com.example.pdkrestaurant;

import com.example.pdkrestaurant.entities.TaiKhoan;
import com.example.pdkrestaurant.repositories.UserRepository;
import com.example.pdkrestaurant.utils.EnumRole;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
@AllArgsConstructor
@SpringBootApplication
public class PdKrestaurantApplication implements CommandLineRunner {
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(PdKrestaurantApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count()==0){
            // Tạo acount Admin có sẵn
            TaiKhoan taiKhoan = new TaiKhoan("admin01", "admin01@gmail.com", "1", "012346789",
                    Arrays.asList(EnumRole.ROLE_ADMIN.name()));
            userRepository.save(taiKhoan);
        }

    }
}
