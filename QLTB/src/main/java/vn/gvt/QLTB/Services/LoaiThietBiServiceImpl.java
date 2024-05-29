package vn.gvt.QLTB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.gvt.QLTB.Models.LoaiThietBi;
import vn.gvt.QLTB.Repositories.LoaiThietBiRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiThietBiServiceImpl implements LoaiThietBiService {

    @Autowired
    private LoaiThietBiRepository loaiThietBiRepository;

    @Override
    public List<LoaiThietBi> getTypeTB(String ID) {
        return loaiThietBiRepository.findDistinctByThietBiPhongID(ID);
    }
    
    @Override
    public List<LoaiThietBi> getDistinctByThietBiIsNull() {
        return loaiThietBiRepository.findDistinctByThietBiIsNull();
    }
    
    @Override
    public List<LoaiThietBi> getAllLoaiTB() {
        return loaiThietBiRepository.findAll();
    }
    
    @Override
    public Integer add(LoaiThietBi loaitb) {
        try {
            loaiThietBiRepository.save(loaitb);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }
    
    
    @Override
    public Optional<LoaiThietBi> getLoaiTB_ID(Integer ID) {
        return loaiThietBiRepository.findById(ID);
    }
    
    @Override
    public Integer deleteTb(LoaiThietBi loaitb) {
        try {
            loaiThietBiRepository.delete(loaitb);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }
    
    @Override
    public Integer editTb(LoaiThietBi loaitb) {
        try {
            loaiThietBiRepository.save(loaitb);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }
}
