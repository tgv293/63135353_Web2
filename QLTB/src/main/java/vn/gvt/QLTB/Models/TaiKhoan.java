package vn.gvt.QLTB.Models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TAIKHOAN")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaiKhoan {
	@Id
	@NotBlank
	@Size(max = 30)
	@Column(name = "TENTAIKHOAN")
	private String id;

	@Size(max = 100)
	@NotBlank
	@Column(name = "MATKHAU")
	private String pass;

	@Size(max = 20)
	@NotBlank
	@Column(name = "LOAITAIKHOAN")
	private String typeacc;

	@OneToOne(mappedBy = "taiKhoan")
	private NhanVien nhanvien;
	
	public TaiKhoan(String id) {
	    this.id = id;
	}
}
