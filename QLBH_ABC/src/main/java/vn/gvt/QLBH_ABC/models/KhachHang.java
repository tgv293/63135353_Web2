package vn.gvt.QLBH_ABC.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "khachhang") 
public class KhachHang {
	@Id
	@Column(name = "MAKH")    // tên cột, trong bảng , phải viết giống 100%
	private String maKH;         // được mapping sang trường thông tin là MaKH, trong class này
	@Column(name="HOTEN" )
	private String hoTen;
	@Column(name="DCHI" )
	private String diaChi;
	@Column(name="SODT" )
	private String soDT;
	@Column(name="NGSINH" )
	private Date ngaySinh;
	@Column(name="NGDK" )
	private Date ngayDangKy;
	@Column(name="DOANHSO" )
	private double doanhSo;
	
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSoDT() {
		return soDT;
	}
	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public Date getNgayDangKy() {
		return ngayDangKy;
	}
	public void setNgayDangKy(Date ngayDangKy) {
		this.ngayDangKy = ngayDangKy;
	}
	public double getDoanhSo() {
		return doanhSo;
	}
	public void setDoanhSo(double doanhSo) {
		this.doanhSo = doanhSo;
	}
	
}

