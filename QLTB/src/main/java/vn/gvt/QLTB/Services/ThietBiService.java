package vn.gvt.QLTB.Services;

import vn.gvt.QLTB.Models.ThietBi;

import java.util.List;

public interface ThietBiService {

    public List<ThietBi> getListChoPhepMuon(String ID);
    
    public ThietBi timTBChuaSD_Phong_LoaiTB(String phong, Integer loaiTB);
    
    public List<ThietBi> getThietBiWithNullPhong();
    
    public int addTBToRoom(String maPhong, Integer matb);
    
    public void callSpBaoTriThietBi(Integer maTB);
    
    public void callSpBaoMatThietBi(Integer maTB);
    
    public void callSpHoanThanhBTThietBi(Integer maTB);
    
    public List<ThietBi> getListTB();
    
    public ThietBi add(ThietBi cttb);
    
    public int deleteTb(Integer id);
    
    public List<ThietBi> findByMaPhong(String maPhong);

    public ThietBi update(ThietBi thietBi);

}