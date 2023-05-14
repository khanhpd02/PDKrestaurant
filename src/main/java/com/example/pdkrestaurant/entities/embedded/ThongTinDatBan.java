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
 * Time      : 9:39 PM
 * Filename  : ThongTinDatCho
 */
@Getter
@Setter
@NoArgsConstructor

public class ThongTinDatBan {
    public ThongTinDatBan(LoaiBan loaiBan, int soLuongBanCanDat) {
        this.loaiBan = loaiBan;
        this.soLuongBanCanDat = soLuongBanCanDat;
    }

    private LoaiBan loaiBan ;//Loại bàn


    private int soLuongBanCanDat; // Số lượng bàn khách cần đặt
}
