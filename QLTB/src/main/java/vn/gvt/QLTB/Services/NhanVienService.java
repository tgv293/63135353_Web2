package vn.gvt.QLTB.Services;

import java.util.List;

import vn.gvt.QLTB.Models.NhanVien;

public interface NhanVienService {
	public NhanVien getNhanVienByID(String id);
	
	public List<NhanVien> getListNV();
	
	public Integer addNV(NhanVien nhanvien);
	
	public void resetPassword(String maNV);
	
	public NhanVien editNhanVien(NhanVien nv);
}
