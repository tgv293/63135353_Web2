package vn.gvt.FormSubmit_Thymeleaf.models;

public class DTOSinhVien {
	private String maSoSV;      // Mã số sinh viên
	private String hoVaTen;     // Họ và Tên
	public String getMaSoSV() {
		return maSoSV;
	}
	public void setMaSoSV(String maSoSV) {
		this.maSoSV = maSoSV;
	}
	public String getHoVaTen() {
		return hoVaTen;
	}
	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}
	public DTOSinhVien(String maSoSV, String hoVaTen) {
		this.maSoSV = maSoSV;
		this.hoVaTen = hoVaTen;
	}

	public DTOSinhVien() {
		super();
	}
	
}