package vn.gvt.QLTB.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.gvt.QLTB.Models.NhanVien;
import vn.gvt.QLTB.Repositories.NhanVienRepository;

@Service
public class NhanVienServiceImpl implements NhanVienService{
	
	@Autowired
	private NhanVienRepository nhanVienRepository;

	@Override
    public NhanVien getNhanVienByID(String id) {
        return nhanVienRepository.findById(id).orElse(null);
    }

	@Override
    public List<NhanVien> getListNV() {
        return nhanVienRepository.findAll();
    }
	
	@Override
	public Integer addNV(NhanVien nhanvien) {
        try {
            nhanVienRepository.save(nhanvien);
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }
    }
	
	@Override
	public NhanVien editNhanVien(NhanVien nv) {
        NhanVien existingNv = nhanVienRepository.findById(nv.getMaNV()).orElse(null);
        if (existingNv != null) {
            existingNv.setHoTenNV(nv.getHoTenNV());
            existingNv.setEmail(nv.getEmail());
            existingNv.setCmnd(nv.getCmnd());
            existingNv.setSdt(nv.getSdt());
            return nhanVienRepository.save(existingNv);
        }
        return null;
    }
	
	@Override
	public void resetPassword(String maNV) {
        nhanVienRepository.spResetPass(maNV);
    }
}
