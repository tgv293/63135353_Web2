package vn.gvt.QLTB.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRANGTHAI")
public class TrangThai implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MATINHTRANG")
	private int maTinhTrang;
	@Column(name = "TINHTRANG")
	private String tinhTrang;
	public TrangThai(int maTinhTrang, String tinhTrang) {
		super();
		this.maTinhTrang = maTinhTrang;
		this.tinhTrang = tinhTrang;
	}
	
	public TrangThai() {
		super();
	}

	public int getMaTinhTrang() {
		return maTinhTrang;
	}

	public void setMaTinhTrang(int maTinhTrang) {
		this.maTinhTrang = maTinhTrang;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	
	
}
