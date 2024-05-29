package vn.gvt.QLTB.Services;

import java.util.List;

import vn.gvt.QLTB.Models.Lop;

public interface LopService {

    public Lop getLopByID(String maLop);
    
    public List<Lop> getListLop();
    
    public List<Lop> getAllListLop();
    
    public boolean add(Lop lop);
}
