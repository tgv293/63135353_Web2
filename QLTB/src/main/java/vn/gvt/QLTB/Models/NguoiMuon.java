package vn.gvt.QLTB.Models;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
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
import lombok.ToString;

@Entity
@Table(name = "NGUOIMUON")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NguoiMuon {
    @Id
    @Size(max = 15)
    @NotBlank
    @Column(name = "MANGUOIMUON")
    private String maNguoiMuon;

    @Size(max = 50)
    @NotBlank
    @Column(name = "TENNGUOIMUON")
    private String tenNguoiMuon;

    @Size(max = 50)
    @NotBlank
    @Column(name = "EMAIL")
    private String email;
    
    @ToString.Exclude
    @OneToMany(mappedBy = "nguoiMuon", fetch = FetchType.EAGER)
    private Collection<CTPhieuMuon> dspm;

	public NguoiMuon(@Size(max = 15) @NotBlank String maNguoiMuon, @Size(max = 50) @NotBlank String tenNguoiMuon,
			@Size(max = 50) @NotBlank String email) {
		super();
		this.maNguoiMuon = maNguoiMuon;
		this.tenNguoiMuon = tenNguoiMuon;
		this.email = email;
	}
    
    
}
