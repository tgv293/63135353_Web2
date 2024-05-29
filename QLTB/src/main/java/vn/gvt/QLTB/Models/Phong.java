package vn.gvt.QLTB.Models;

import java.util.Collection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PHONG")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phong {
	@Id
	@NotBlank
	@Column(name = "MAPHONG")
	private String maPhong;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "TRANGTHAI", referencedColumnName = "MATINHTRANG")
	private TrangThai trangThai;

	@ToString.Exclude
	@OneToMany(mappedBy = "phong", fetch = FetchType.EAGER)
	private Collection<ThietBi> dstb;
	
	@ToString.Exclude
	@OneToMany(mappedBy = "maPhong", fetch = FetchType.EAGER)
	private Collection<PhieuMuon> dspm;
}
