package com.example.pdkrestaurant.service.dichvu;

import com.example.pdkrestaurant.dtos.dichvu.DichVuDto;
import com.example.pdkrestaurant.dtos.dichvu.GiaDichVuDto;
import com.example.pdkrestaurant.dtos.dichvu.UpdateDichVuDto;
import com.example.pdkrestaurant.entities.DatBan;
import com.example.pdkrestaurant.entities.DichVu;
import com.example.pdkrestaurant.entities.LoaiBan;
import com.example.pdkrestaurant.entities.embedded.GiaDichVu;
import com.example.pdkrestaurant.entities.embedded.ThongTinDatBan;
import com.example.pdkrestaurant.exceptions.InvalidException;
import com.example.pdkrestaurant.exceptions.NotFoundException;
import com.example.pdkrestaurant.repositories.DichVuRepository;
import com.example.pdkrestaurant.repositories.LoaiBanRepository;
import com.example.pdkrestaurant.utils.EnumTrangThaiDatBan;
import com.example.pdkrestaurant.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class DichVuServiceImpl implements DichVuService {
    public final DichVuRepository dichVuRepository;
    public final LoaiBanRepository loaiBanRepository;
    @Override
    public Page<DichVu> filter(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return dichVuRepository.findByMaDichVuContainingOrTenDichVuContainingAllIgnoreCase(search,search, pageable);
    }

    @Override
    public List<DichVu> finAll() {
        return null;
    }

    @Override
    public DichVu findById(String id) {
        return dichVuRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("ko có id dịch Vụ này",id)));
    }


    @Override
    public void deleteById(String id) {

    }

    @Override
    public DichVu create(DichVuDto dto) {
        DichVu dichVu=new DichVu();

        if (ObjectUtils.isEmpty(dto.getMaDichVu())) {
            throw new InvalidException("Mã dịch vụ Không được để trống");
        }
        if (dichVuRepository.existsByMaDichVu(dto.getMaDichVu())) {
            throw new InvalidException(String.format("Mã dich Vu %s đã tồn tại, vui lòng nhập khác:",
                    dto.getMaDichVu()));
        }
        if (ObjectUtils.isEmpty(dto.getTenDichVu())) {
            throw new InvalidException("tên dịch vụ không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getNoiDung())) {
            throw new InvalidException("nội dung bàn Không được để trống");
        }

            LoaiBan loaiBan=loaiBanRepository.findLoaiBanByMaLoaiBan(dto.getMaLoaiBan());
            if (loaiBan==null) throw new InvalidException("Không có loại bàn!!");
//        List<ThongTinDatBan> thongTinDatBans = new ArrayList<>();
            GiaDichVu giaDichVu = new GiaDichVu(loaiBan, dto.getSoChoNgoi(), dto.getGia());
//        thongTinDatBans.add(thongTinDatBan);
            dichVu.getGiaDichVus().add(giaDichVu);

        dichVu.setMaDichVu(dto.getMaDichVu());
        dichVu.setTenDichVu(dto.getTenDichVu());
        dichVu.setNoiDung(dto.getNoiDung());

        return dichVuRepository.save(dichVu);
    }

    @Override
    public DichVu addGiaDichVu(String idDV, GiaDichVuDto dto) {
        if (ObjectUtils.isEmpty(dto.getMaLoaiBan())) {
            throw new InvalidException("Mã Loại bàn Không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getGia())) {
            throw new InvalidException("Giá tiền được để trống");
        }
        DichVu dichVu = dichVuRepository.findById(idDV).orElse(null);
        if (dichVu==null) throw new InvalidException("Không tìm thấy id Dịch Vụ!!");
        LoaiBan loaiBan=loaiBanRepository.findLoaiBanByMaLoaiBan(dto.getMaLoaiBan());
        if (loaiBan==null) throw new InvalidException("Không có loại bàn!!");

        int co=0;
        GiaDichVu giaDichVu=new GiaDichVu(loaiBan,dto.getSoChoNgoi(), dto.getGia());
        for (int o = 0;o<dichVu.getGiaDichVus().size();o++){
            if(Objects.equals(dichVu.getGiaDichVus().get(o).getLoaiBan().getMaLoaiBan(),giaDichVu.getLoaiBan().getMaLoaiBan())){
                if(dichVu.getGiaDichVus().get(o).getSoChoNgoi()==giaDichVu.getSoChoNgoi()) {
                    throw new InvalidException("Giá Dịch vụ này có rồi");
                }
            }
        }
            dichVu.getGiaDichVus().add(giaDichVu);
            dichVuRepository.save(dichVu);



        return dichVu;
    }

    @Override
    public DichVu updateGiaDichVu(String id, GiaDichVuDto dto) {
        if (ObjectUtils.isEmpty(dto.getMaLoaiBan())) {
            throw new InvalidException("Mã Loại bàn Không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getGia())) {
            throw new InvalidException("Giá tiền được để trống");
        }
        DichVu dichVu = dichVuRepository.findById(id).orElse(null);
        if (dichVu==null) throw new InvalidException("Không tìm thấy id Dịch Vụ!!");
        LoaiBan loaiBan=loaiBanRepository.findLoaiBanByMaLoaiBan(dto.getMaLoaiBan());
        if (loaiBan==null) throw new InvalidException("Không có loại bàn!!");

        int co=0;
        GiaDichVu giaDichVu=new GiaDichVu(loaiBan,dto.getSoChoNgoi(), dto.getGia());
        for (int o = 0;o<dichVu.getGiaDichVus().size();o++){
            if(Objects.equals(dichVu.getGiaDichVus().get(o).getLoaiBan().getMaLoaiBan(),giaDichVu.getLoaiBan().getMaLoaiBan())){
                if(dichVu.getGiaDichVus().get(o).getSoChoNgoi()==giaDichVu.getSoChoNgoi()) {
                    dichVu.getGiaDichVus().get(o).setGia(dto.getGia());
                }
            }
        }
        dichVu.getGiaDichVus().add(giaDichVu);
        dichVuRepository.save(dichVu);



        return dichVu;
    }

    @Override
    public DichVu update(String id, UpdateDichVuDto dto) {
        Optional<DichVu> dichVu=dichVuRepository.findById(id);
        if(!dichVu.isPresent())
            throw new NotFoundException(String.format("ID %s Không tồn tại",id));
        if (ObjectUtils.isEmpty(dto.getMaDichVu())) {
            throw new InvalidException("Mã dịch vụ Không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getTenDichVu())) {
            throw new InvalidException("Tên DV không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getNoiDung())) {
            throw new InvalidException("Nội dung không được để trống");
        }
        if (!dichVu.get().getMaDichVu().equals(dto.getMaDichVu())){
            // Nếu mã không giống mã cũ thì kiểm tra mã mới đã tồn tại trong database hay chưa
            if (dichVuRepository.existsByMaDichVu(dto.getMaDichVu())){
                throw new InvalidException(String.format("Mã loại bàn %s đã tồn tại", dto.getMaDichVu()));

            }
        }


        dichVu.get().setMaDichVu(dto.getMaDichVu());
        dichVu.get().setTenDichVu(dto.getTenDichVu());
        dichVu.get().setNoiDung(dto.getNoiDung());
        return dichVuRepository.save(dichVu.get());
    }


    @Override
    public DichVu changeStatus(String id) {
        Optional<DichVu> dichVu = dichVuRepository.findById(id);
        if(!dichVu.isPresent())
            throw new NotFoundException(String.format("Dat Ban có id %s không tồn tại", id));
        dichVu.get().setTrangThai(!dichVu.get().isTrangThai());
        return dichVuRepository.save(dichVu.get());
    }
}
