package vn.gvt.QLBH_ABC.services;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.gvt.QLBH_ABC.models.KhachHang;

@Service
public interface KhachHangService {
	public List<KhachHang> getAllKhachHang();
	public KhachHang getKhachHangByID(String maKH);
}
