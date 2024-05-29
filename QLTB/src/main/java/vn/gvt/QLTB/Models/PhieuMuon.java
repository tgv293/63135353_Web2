package vn.gvt.QLTB.Models;

import java.time.LocalDateTime;
import java.util.Collection;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "PHIEUMUON")
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhieuMuon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAPHIEUMUON")
	private Integer maPhieuMuon;

	@ManyToOne
	@Size(max = 20)
	@NotNull
	@JoinColumn(name = "MAPHONG", referencedColumnName = "MAPHONG")
	private Phong maPhong;

	@ManyToOne
	@Size(max = 20)
	@NotNull
	@JoinColumn(name = "LOP", referencedColumnName = "MALOP")
	private Lop lop;

	@PastOrPresent
	@NotNull
	@Column(name = "TDMUON")
	private LocalDateTime thoiDiemMuon;

	@Future
	@NotNull
	@Column(name = "HANTRA")
	private LocalDateTime hanTra;

	@Column(name = "TDTRA", nullable = true)
	private LocalDateTime thoiDiemTra;

	@ManyToOne
	@JoinColumn(name = "NGUOILAP", referencedColumnName = "MANV")
	private NhanVien nguoiLap;

	@ToString.Exclude
	@OneToMany(mappedBy = "phieuMuon", fetch = FetchType.EAGER)
	private Collection<CTPhieuMuon> listCTPhieuMuon;
}
