package vn.gvt.QLTB.Services;

import java.util.List;
import java.util.Map;

import vn.gvt.QLTB.Models.Phong;

public interface PhongService {

    public List<Phong> getPhong();
    
    public Phong getPhongByID(String maPhong);
    
    Map<String, Integer> dsTBChoPhepMuon(String maPhong);
    
    public Map<String, Map<String, Integer>> getDsTBChoPhepMuonCuaTatCaPhong();
    
    public Phong addRoom(Phong phong);
    
    public void callSpBaoTriPhong(String maPhong);
    
    public void callSpHoanThanhBaoTriPhong(String maPhong);
    
    public Integer deletePhong(String maPhong);
}
