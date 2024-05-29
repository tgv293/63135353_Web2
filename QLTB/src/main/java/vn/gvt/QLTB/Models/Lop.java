package vn.gvt.QLTB.Models;

import java.util.Collection;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LOP")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lop {
	@Id
	@NotBlank
	@Size(max = 20)
	@Column(name = "MALOP")
	private String maLop;

	@Min(0)
    @Max(45)
	@NotNull
	@Column(name = "SISO")
	private Integer siso;

	@Size(max = 30)
	@NotBlank
	@Column(name = "HOTENGVCN")
	private String hotenGV;

	@Size(max = 15)
	@NotBlank
	@Column(name = "MAGIAOVIEN")
	private String maGV;

	@OneToMany(mappedBy = "lop", fetch = FetchType.EAGER)
	private Collection<PhieuMuon> dspm;

	public String getFullName() {
	    return (maLop != null ? "Lớp: " + maLop : "")
	         + " - " 
	         + (hotenGV != null ? "GVCN: " + hotenGV : "");
	}
}
