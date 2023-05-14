package com.example.pdkrestaurant.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by: IntelliJ IDEA
 * User      : thangpx
 * Date      : 4/11/23
 * Time      : 10:01 PM
 * Filename  : LoaiThuCung
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "loai-ban")
public class LoaiBan {
    @Id
    private String id;

    // mã không được trùng
    private String maLoaiBan;

    private String tenLoaiBan;

    private boolean trangThai = true;
}
