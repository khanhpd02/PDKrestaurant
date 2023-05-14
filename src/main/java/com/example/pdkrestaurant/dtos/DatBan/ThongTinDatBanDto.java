package com.example.pdkrestaurant.dtos.DatBan;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ThongTinDatBanDto {
    private String maLoaiBan;
    private int soLuong;

}
