package vn.gvt.QLTB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.gvt.QLTB.Models.ThietBi;
import vn.gvt.QLTB.Repositories.ThietBiRepository;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ThietBiServiceImpl implements ThietBiService {

    @Autowired
    private ThietBiRepository thietBiRepository;

    @Override
    public List<ThietBi> getListChoPhepMuon(String ID) {
        return thietBiRepository.findChoPhepMuon(ID);
    }
    
    @Override
    public ThietBi timTBChuaSD_Phong_LoaiTB(String phong, Integer loaiTB) {
        return thietBiRepository.findThietBiChuaSD(phong, loaiTB);
    }
    
    @Override
    public List<ThietBi> getThietBiWithNullPhong() {
        return thietBiRepository.findByPhongIsNull();
    }
    
    @Override
    public int addTBToRoom(String maPhong, Integer matb) {
        try {
            return thietBiRepository.updatePhongForThietBi(maPhong, matb);
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
    
    @Override
    public void callSpBaoTriThietBi(Integer maTB) {
        thietBiRepository.callSpBaoTriThietBi(maTB);
    }
    
    @Override
    public void callSpBaoMatThietBi(Integer maTB) {
        thietBiRepository.callSpBaoMatThietBi(maTB);
    }
    
    @Override
    public void callSpHoanThanhBTThietBi(Integer maTB) {
        thietBiRepository.callSpHoanThanhBTThietBi(maTB);
    }
}
