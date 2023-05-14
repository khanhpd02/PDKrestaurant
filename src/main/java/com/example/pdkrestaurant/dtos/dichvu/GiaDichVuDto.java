package com.example.pdkrestaurant.dtos.dichvu;

import com.example.pdkrestaurant.entities.LoaiBan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiaDichVuDto {
    private String maLoaiBan;//Loại bàn


    private int soChoNgoi;//Số chỗ ngồi của bàn

    private double gia;
}
