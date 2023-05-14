package com.example.pdkrestaurant.dtos.dichvu;

import com.example.pdkrestaurant.entities.embedded.GiaDichVu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DichVuDto {
    private String maDichVu;


    private String tenDichVu;

    // nội dung là html
    private String noiDung;

    // Giá phụ thuộc vào Số Ngồi và loại bàn của khách
    private String maLoaiBan;
    private double gia;
    private int soChoNgoi;
}
