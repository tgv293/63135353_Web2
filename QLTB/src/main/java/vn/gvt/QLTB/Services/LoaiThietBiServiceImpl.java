package vn.gvt.QLTB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.gvt.QLTB.Models.LoaiThietBi;
import vn.gvt.QLTB.Repositories.LoaiThietBiRepository;

import java.util.List;

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
}
