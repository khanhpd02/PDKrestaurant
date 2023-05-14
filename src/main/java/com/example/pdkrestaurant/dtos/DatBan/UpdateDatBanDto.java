package com.example.pdkrestaurant.dtos.DatBan;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDatBanDto {
    private String email;
    private String note;
//    private String maLoaiBan;//Lấy mã Loại bàn từ Loại Bàn
//    private int soLuong;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm")
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date thoigian;
}
