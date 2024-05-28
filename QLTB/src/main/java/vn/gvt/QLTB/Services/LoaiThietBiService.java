package vn.gvt.QLTB.Services;

import vn.gvt.QLTB.Models.LoaiThietBi;

import java.util.List;

public interface LoaiThietBiService {
    public List<LoaiThietBi> getTypeTB(String ID);
    
    public List<LoaiThietBi> getDistinctByThietBiIsNull();
    
    public List<LoaiThietBi> getAllLoaiTB();
}