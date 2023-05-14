package com.example.pdkrestaurant.service.DatBan;

import com.example.pdkrestaurant.dtos.DatBan.DatBanDto;
import com.example.pdkrestaurant.dtos.DatBan.ThongTinDatBanDto;
import com.example.pdkrestaurant.dtos.DatBan.UpdateDatBanDto;
import com.example.pdkrestaurant.entities.DatBan;
import com.example.pdkrestaurant.entities.LoaiBan;
import com.example.pdkrestaurant.entities.TaiKhoan;
import com.example.pdkrestaurant.entities.embedded.ThongTinDatBan;
import com.example.pdkrestaurant.exceptions.InvalidException;
import com.example.pdkrestaurant.exceptions.NotFoundException;
import com.example.pdkrestaurant.repositories.DatBanRepository;
import com.example.pdkrestaurant.repositories.LoaiBanRepository;
import com.example.pdkrestaurant.repositories.ThongTinDatBanRepository;
import com.example.pdkrestaurant.utils.EnumTrangThaiDatBan;
import com.example.pdkrestaurant.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
@AllArgsConstructor
public class DatBanServiceImpl implements DatBanService {
    private final DatBanRepository datBanRepository;
    private final LoaiBanRepository loaiBanRepository;
    private final ThongTinDatBanRepository thongTinDatBanRepository;
    @Override
    public Page<DatBan> filter(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return datBanRepository.findByEmailContainingOrIdContainingAllIgnoreCase(search,search, pageable);
    }

    @Override
    public List<DatBan> finAll() {
        return null;
    }

    @Override
    public DatBan findbyId(String id) {
        return datBanRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("ko có mã đặt bàn này",id)));
    }


    @Override
    public void deleteById(String id) {

    }

    @Override
    public DatBan create(DatBanDto dto) {

        DatBan datBan=new DatBan();

        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email Không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getThoigian())) {
            throw new InvalidException("Thời gian không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getMaLoaiBan())) {
            throw new InvalidException("Mã Loại bàn Không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getSoLuong())) {
            throw new InvalidException("Số lượng không được để trống");
        }

        LoaiBan loaiBan=loaiBanRepository.findLoaiBanByMaLoaiBan(dto.getMaLoaiBan());
        if (loaiBan==null) throw new InvalidException("Không có loại bàn!!");
//        List<ThongTinDatBan> thongTinDatBans = new ArrayList<>();
        ThongTinDatBan thongTinDatBan = new ThongTinDatBan(loaiBan, dto.getSoLuong());
//        thongTinDatBans.add(thongTinDatBan);



        // thi t lay ma loai ban roi truyen vao casi thong tin dat ban

//        List<ThongTinDatBan> thongTinDatBans = new ArrayList<>();
//        ThongTinDatBan thongTinDatBan = new ThongTinDatBan(loaiBan,dto.getSoLuong());
//        thongTinDatBans.add(thongTinDatBan );

//No id property found on class class com.example.pdkrestaurant.entities.embedded.ThongTinDatBan
        // Loi nay la do m chua tao cai dat ban nen k co id de no lay
        // m phai save dat ban truoc
        // sau do moi them thong tin dat ban vo
//        datBan.setThongTinDatBans(thongTinDatBans);
        datBan.setEmail(dto.getEmail());
        datBan.setNote(dto.getNote());
        datBan.setThoiGian(dto.getThoigian());
        datBan.setTrangThaiDatBan(EnumTrangThaiDatBan.DAT_BAN.name());
        datBan.setTrangThai(true);
        datBanRepository.save(datBan);
        datBan.getThongTinDatBans().add(thongTinDatBan);
        datBanRepository.save(datBan);
        return datBanRepository.save(datBan);
    }

    public DatBan addThongTinDatBan (String idDatBan, ThongTinDatBanDto dto) {
         DatBan datBan = datBanRepository.findById(idDatBan).orElse(null);
         if (datBan==null) throw new InvalidException("Không tìm thấy id đặt bàn!!");
         LoaiBan loaiBan=loaiBanRepository.findLoaiBanByMaLoaiBan(dto.getMaLoaiBan());
         if (loaiBan==null) throw new InvalidException("Không có loại bàn!!");
        if (ObjectUtils.isEmpty(dto.getMaLoaiBan())) {
            throw new InvalidException("Mã Loại bàn Không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getSoLuong())) {
            throw new InvalidException("Số lượng bàn Không được để trống");
        }
//        List<ThongTinDatBan> thongTinDatBans = new ArrayList<>();


        // ... Điền thông tin cho đối tượng DatBan
        /*LoaiBan loaiBanCanTim = loaiBan; // Giá trị cần kiểm tra
        int co=0;
        for (ThongTinDatBan tt : datBan.getThongTinDatBans()) {
            if (tt.getLoaiBan().getMaLoaiBan() == loaiBan.getMaLoaiBan()) { // Kiểm tra thuộc tính loaiBan của từng đối tượng
                tt.setSoLuongBanCanDat(dto.getSoLuong()+tt.getSoLuongBanCanDat());
                int vitri =datBan.getThongTinDatBans().indexOf(tt);
                int tong= datBan.getThongTinDatBans().get(vitri).getSoLuongBanCanDat()+dto.getSoLuong();
                datBan.getThongTinDatBans().set(vitri,new ThongTinDatBan(loaiBan,tong));
                datBanRepository.save(datBan);
                co=co+1;
            }
        }*/
//        if (co==0)
//        {
        int co=0;
        for (int o = 0;o<datBan.getThongTinDatBans().size();o++){
            if(Objects.equals(datBan.getThongTinDatBans().get(o).getLoaiBan().getMaLoaiBan(), loaiBan.getMaLoaiBan())){
                int slOld=datBan.getThongTinDatBans().get(o).getSoLuongBanCanDat();
                datBan.getThongTinDatBans().get(o).setSoLuongBanCanDat(slOld+ dto.getSoLuong());
                datBanRepository.save(datBan);
                co++;
            }
        }
        if(co==0) {
            ThongTinDatBan thongTinDatBan = new ThongTinDatBan(loaiBan, dto.getSoLuong());
            datBan.getThongTinDatBans().add(thongTinDatBan);
            datBanRepository.save(datBan);
        }


//        ThongTinDatBan thongTinDatBan = new ThongTinDatBan(loaiBan, dto.getSoLuong());
//         if(datBan.getThongTinDatBans().contains(thongTinDatBan)!=true){
//             int vitri=datBan.getThongTinDatBans().indexOf(thongTinDatBan);
//             int tong= datBan.getThongTinDatBans().get(vitri).getSoLuongBanCanDat()+dto.getSoLuong();
//             datBan.getThongTinDatBans().set(vitri,new ThongTinDatBan(loaiBan,tong));
//             datBanRepository.save(datBan);
//         }else {
////      thongTinDatBans.add(thongTinDatBan);
//             datBan.getThongTinDatBans().add(thongTinDatBan);
//             datBanRepository.save(datBan);
//         }



        return datBan;
    }

    @Override
    public DatBan update(String id, UpdateDatBanDto dto) {
        Optional<DatBan> datBan=datBanRepository.findById(id);
        if(!datBan.isPresent())
            throw new NotFoundException(String.format("ID %s Không tồn tại",id));
        if (ObjectUtils.isEmpty(dto.getEmail())) {
            throw new InvalidException("Email Không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getThoigian())) {
            throw new InvalidException("Thời gian không được để trống");
        }


        datBan.get().setEmail(dto.getEmail());
        datBan.get().setNote(dto.getNote());
        datBan.get().setThoiGian(dto.getThoigian());
        return datBanRepository.save(datBan.get());
    }

    @Override
    public DatBan updateThongTinDatCho(String id, ThongTinDatBanDto dto) {
        DatBan datBan = datBanRepository.findById(id).orElse(null);
        if (datBan==null) throw new InvalidException("Không tìm thấy id đặt bàn!!");
        LoaiBan loaiBan=loaiBanRepository.findLoaiBanByMaLoaiBan(dto.getMaLoaiBan());
        if (loaiBan==null) throw new InvalidException("Không có loại bàn!!");
        if (ObjectUtils.isEmpty(dto.getMaLoaiBan())) {
            throw new InvalidException("Mã Loại bàn Không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getSoLuong())) {
            throw new InvalidException("Số lượng bàn Không được để trống");
        }
//        List<ThongTinDatBan> thongTinDatBans = new ArrayList<>();


        // ... Điền thông tin cho đối tượng DatBan
        /*LoaiBan loaiBanCanTim = loaiBan; // Giá trị cần kiểm tra
        int co=0;
        for (ThongTinDatBan tt : datBan.getThongTinDatBans()) {
            if (tt.getLoaiBan().getMaLoaiBan() == loaiBan.getMaLoaiBan()) { // Kiểm tra thuộc tính loaiBan của từng đối tượng
                tt.setSoLuongBanCanDat(dto.getSoLuong()+tt.getSoLuongBanCanDat());
                int vitri =datBan.getThongTinDatBans().indexOf(tt);
                int tong= datBan.getThongTinDatBans().get(vitri).getSoLuongBanCanDat()+dto.getSoLuong();
                datBan.getThongTinDatBans().set(vitri,new ThongTinDatBan(loaiBan,tong));
                datBanRepository.save(datBan);
                co=co+1;
            }
        }*/
//        if (co==0)
//        {
        int co=0;
        for (int o = 0;o<datBan.getThongTinDatBans().size();o++){
            if(Objects.equals(datBan.getThongTinDatBans().get(o).getLoaiBan().getMaLoaiBan(), loaiBan.getMaLoaiBan())){
                //int slOld=datBan.getThongTinDatBans().get(o).getSoLuongBanCanDat();
                datBan.getThongTinDatBans().get(o).setSoLuongBanCanDat(dto.getSoLuong());
                datBanRepository.save(datBan);
                co++;
            }
        }
        if(co==0) {
            ThongTinDatBan thongTinDatBan = new ThongTinDatBan(loaiBan, dto.getSoLuong());
            datBan.getThongTinDatBans().add(thongTinDatBan);
            datBanRepository.save(datBan);
        }
        return datBan;
    }

    @Override
    public DatBan changeStatus(String id) {
        Optional<DatBan> datBan = datBanRepository.findById(id);
        if(!datBan.isPresent())
            throw new NotFoundException(String.format("Dat Ban có id %s không tồn tại", id));
        datBan.get().setTrangThai(!datBan.get().isTrangThai());
        return datBanRepository.save(datBan.get());
    }

    @Override
    public DatBan updateTrangThai(String id, int statusNumber) {
        Optional<DatBan> datBan = datBanRepository.findById(id);
        if (!datBan.isPresent())
            throw new NotFoundException(String.format("Bàn đã đặt có id là %s không tồn tại", id));
        switch (statusNumber){
            case 1:
                datBan.get().setTrangThaiDatBan(EnumTrangThaiDatBan.DANG_THUC_HIEN.name());
                break;
            case 2:
                datBan.get().setTrangThaiDatBan(EnumTrangThaiDatBan.DAT_BAN.name());
                break;
            case 3:
                datBan.get().setTrangThaiDatBan(EnumTrangThaiDatBan.HOAN_THANH.name());
                break;
            default:
                datBan.get().setTrangThaiDatBan(EnumTrangThaiDatBan.HUY.name());
        }
        return datBanRepository.save(datBan.get());
    }
}
