package vn.gvt.QLBH_ABC.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.gvt.QLBH_ABC.models.KhachHang;
import vn.gvt.QLBH_ABC.repositories.KhachHangRepository;

@Service
public class KhachHangServiceImpl implements KhachHangService {
	
	@Autowired KhachHangRepository khachHangRepository;
	
	@Override
	public List<KhachHang> getAllKhachHang() {
		return khachHangRepository.findAll();
	}
	
	@Override
	public KhachHang getKhachHangByID(String maKH) {
		return khachHangRepository.getById(maKH);
	}
}
