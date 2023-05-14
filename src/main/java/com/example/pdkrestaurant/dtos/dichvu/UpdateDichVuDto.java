package com.example.pdkrestaurant.dtos.dichvu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDichVuDto {
    private String maDichVu;

    private String tenDichVu;

    // nội dung là html
    private String noiDung;

}
