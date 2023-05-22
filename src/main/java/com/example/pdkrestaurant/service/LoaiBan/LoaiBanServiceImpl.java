package com.example.pdkrestaurant.service.LoaiBan;

import com.example.pdkrestaurant.dtos.LoaiBan.LoaiBanDto;
import com.example.pdkrestaurant.entities.LoaiBan;
import com.example.pdkrestaurant.exceptions.InvalidException;
import com.example.pdkrestaurant.exceptions.NotFoundException;
import com.example.pdkrestaurant.repositories.LoaiBanRepository;
import com.example.pdkrestaurant.utils.PageUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoaiBanServiceImpl implements LoaiBanService{
    private final LoaiBanRepository   loaiBanRepository;
    @Override
    public Page<LoaiBan> filter(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return loaiBanRepository.findByMaLoaiBanContainingOrTenLoaiBanContainingAllIgnoreCase(search,search, pageable);
    }
    @Override
    public List<LoaiBan> finAll() {
        return null;
    }
    @Override
    public LoaiBan getLoaiBanByMaLoaiBan(String id) {
        return loaiBanRepository.findLoaiBanByMaLoaiBan(id);
    }
    @Override
    public void deleteById(String id) {

    }
    @Override
    public LoaiBan create(LoaiBanDto dto) {
        LoaiBan loaiBan=new LoaiBan();
        if (ObjectUtils.isEmpty(dto.getMaLoaiBan())) {
            throw new InvalidException("Mã loại không được trống");
        }
        if (ObjectUtils.isEmpty(dto.getTenLoaiBan())) {
            throw new InvalidException("Tên Loại bàn không được để trống");
        }
        if (loaiBanRepository.existsByMaLoaiBan(dto.getMaLoaiBan())) {
            throw new InvalidException(String.format("Mã loại bàn đã tồn tại, vui lòng nhập khác:",
                    dto.getMaLoaiBan()));
        }
        loaiBan.setMaLoaiBan(dto.getMaLoaiBan());
        loaiBan.setTenLoaiBan(dto.getTenLoaiBan());
        loaiBan.setTrangThai(true);
        loaiBanRepository.save(loaiBan);
        return loaiBan;
    }

    @Override
    public LoaiBan update(String id, LoaiBanDto dto) {
        Optional<LoaiBan> loaiBan=loaiBanRepository.findById(id);
        if(!loaiBan.isPresent())
            throw new NotFoundException(String.format("ID %s Không tồn tại",id));
        if (ObjectUtils.isEmpty(dto.getMaLoaiBan())) {
            throw new InvalidException("Mã Loại bàn Không được để trống");
        }
        if (ObjectUtils.isEmpty(dto.getTenLoaiBan())) {
            throw new InvalidException("Tên Loại bàn Không được để trống");
        }
        if (!loaiBan.get().getMaLoaiBan().equals(dto.getMaLoaiBan())){
            // Nếu mã không giống mã cũ thì kiểm tra mã mới đã tồn tại trong database hay chưa
            if (loaiBanRepository.existsByMaLoaiBan(dto.getMaLoaiBan())){
                throw new InvalidException(String.format("Mã loại bàn %s đã tồn tại", dto.getMaLoaiBan()));

            }
        }
        loaiBan.get().setMaLoaiBan(dto.getMaLoaiBan());
        loaiBan.get().setTenLoaiBan(dto.getTenLoaiBan());
        return loaiBanRepository.save(loaiBan.get());
    }


    @Override
    public LoaiBan changeStatus(String id) {
        Optional<LoaiBan> loaiBan = loaiBanRepository.findById(id);
        if(!loaiBan.isPresent())
            throw new NotFoundException(String.format("Loại ban có id %s không tồn tại", id));
        loaiBan.get().setTrangThai(!loaiBan.get().isTrangThai());
        return loaiBanRepository.save(loaiBan.get());
    }
}
