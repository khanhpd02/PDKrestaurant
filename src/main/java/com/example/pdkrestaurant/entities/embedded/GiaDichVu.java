package com.example.pdkrestaurant.entities.embedded;

import com.example.pdkrestaurant.entities.LoaiBan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by: IntelliJ IDEA
 * User      : thangpx
 * Date      : 4/11/23
 * Time      : 9:47 PM
 * Filename  : GiaDichVu
 */
@Getter
@Setter
@NoArgsConstructor

public class GiaDichVu {


    private LoaiBan loaiBan;//Loại bàn


    private int soChoNgoi;//Số chỗ ngồi của bàn

    private double gia;

    public GiaDichVu(LoaiBan loaiBan, int soChoNgoi, double gia) {
        this.loaiBan = loaiBan;
        this.soChoNgoi = soChoNgoi;
        this.gia = gia;
    }
}
