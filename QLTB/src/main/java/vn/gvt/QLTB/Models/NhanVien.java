package vn.gvt.QLTB.Models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "NHANVIEN")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NhanVien {
	@Id
	@Size(max = 20)
	@Column(name = "MANV")
	@NotBlank
	private String maNV;

	@Size(max = 50)
	@NotBlank
	@Column(name = "HOTENNV")
	private String hoTenNV;

	@Size(max = 15)
	@NotBlank
	@Column(name = "CMND")
	private String cmnd;

	@Size(max = 50)
	@NotBlank
	@Column(name = "EMAIL")
	private String email;

	@Size(max = 15)
	@NotBlank
	@Column(name = "SDT")
	private String sdt;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TAIKHOAN", columnDefinition = "TENTAIKHOAN")
	private TaiKhoan taiKhoan;
}
