package com.example.pdkrestaurant.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.pdkrestaurant.entities.embedded.ThongTinDatBan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : thangpx
 * Date      : 4/11/23
 * Time      : 9:37 PM
 * Filename  : DatCho
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dat-ban")
public class DatBan {
    @Id
    private String id;

    // email người đặt chỗ
    private String email;
    private List<ThongTinDatBan> thongTinDatBans = new ArrayList<>();
    // Thời gian Đạặt bàn
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy HH:mm")
    private Date thoiGian;
    // Lưu cuarua khách đi đặt bàn
    private String note;
    private String trangThaiDatBan;// lấy từ enum trạng thái đặt bàn
    private boolean trangThai = true;
}
