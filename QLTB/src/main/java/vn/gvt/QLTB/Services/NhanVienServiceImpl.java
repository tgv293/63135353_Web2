package vn.gvt.QLTB.Services;

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
}
