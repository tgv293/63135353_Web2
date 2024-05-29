package vn.gvt.QLTB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.gvt.QLTB.Models.ThietBi;
import vn.gvt.QLTB.Repositories.ThietBiRepository;
import vn.gvt.QLTB.Repositories.TrangThaiRepository;

import java.util.List;

@Service
public class ThietBiServiceImpl implements ThietBiService {

    
    private ThietBiRepository thietBiRepository;
    private TrangThaiRepository trangThaiRepository;
    
    @Autowired
	public ThietBiServiceImpl(ThietBiRepository thietBiRepository, TrangThaiRepository trangThaiRepository) {
		this.thietBiRepository = thietBiRepository;
		this.trangThaiRepository = trangThaiRepository;
	}

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
    
    @Override
    public List<ThietBi> getListTB() {
        return thietBiRepository.findAll();
    }
    
    @Override
    public ThietBi add(ThietBi cttb) {
        try {
        	cttb.setPhong(null);
        	cttb.setTinhTrangTB(trangThaiRepository.findByMaTinhTrang(1));
            return thietBiRepository.save(cttb);
        } catch (Exception e) {
        	System.out.println(e);
            return null;
        }
    }
    
    @Override
    public int deleteTb(Integer id) {
        try {
            return thietBiRepository.deleteByMaTBi(id);
        } catch (Exception e) {
            return 0;
        }
    }
    
    @Override
    public List<ThietBi> findByMaPhong(String maPhong) {
        return thietBiRepository.findByPhong_MaPhong(maPhong);
    }

    @Override
    public ThietBi update(ThietBi thietBi) {
        return thietBiRepository.save(thietBi);
    }
}
