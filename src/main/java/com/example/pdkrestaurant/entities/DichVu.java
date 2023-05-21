package com.example.pdkrestaurant.entities;

import com.example.pdkrestaurant.entities.embedded.GiaDichVu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * User      : thangpx
 * Date      : 4/11/23
 * Time      : 9:35 PM
 * Filename  : DichVu
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dich-vu")
public class DichVu {
    @Id
    private String id;

    private String maDichVu; // mã dịch vụ không được trùng
    private String tenDichVu;
    private String noiDung;// nội dung của dịch Vụ

    // Giá phụ thuộc vào Số Ngồi và loại bàn của khách
    private List<GiaDichVu> giaDichVus = new ArrayList<>();
    private boolean trangThai = true;



}
