package vn.gvt.QLTB.Services;

import vn.gvt.QLTB.Models.LoaiThietBi;

import java.util.List;
import java.util.Optional;

public interface LoaiThietBiService {
    public List<LoaiThietBi> getTypeTB(String ID);
    
    public List<LoaiThietBi> getDistinctByThietBiIsNull();
    
    public List<LoaiThietBi> getAllLoaiTB();
    
    public Integer add(LoaiThietBi loaitb);
    
    public Optional<LoaiThietBi> getLoaiTB_ID(Integer ID);
    
    public Integer deleteTb(LoaiThietBi loaitb);
    
    public Integer editTb(LoaiThietBi loaitb);
}