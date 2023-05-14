package com.example.pdkrestaurant.dtos.DatBan;


import com.example.pdkrestaurant.entities.embedded.ThongTinDatBan;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatBanDto {
    private String email;
    private String note;
    private String maLoaiBan;//Lấy mã Loại bàn từ Loại Bàn
    private int soLuong;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm")
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date thoigian;



}
