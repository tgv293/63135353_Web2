package vn.gvt.QLTB.Models;

import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "CTPHIEUMUON")
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CTPhieuMuon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MACHITIETPM")
    private Integer maCTPhieuMuon;
        
    @ManyToOne
    @NotNull
    @JoinColumn(name="MAPHIEUMUON", referencedColumnName = "MAPHIEUMUON")
    private PhieuMuon phieuMuon;
    
    @ManyToOne
    @NotNull
    @JoinColumn(name="MATHIETBI", referencedColumnName = "MATHIETBI")
    private ThietBi thietBi;

    @ManyToOne
    @NotNull
    @JoinColumn(name="NGUOIMUON", referencedColumnName = "MANGUOIMUON")
    private NguoiMuon nguoiMuon;
    
    @ManyToOne
    @NotNull
	@JoinColumn(name = "TRANGTHAI", referencedColumnName = "MATINHTRANG")
	private TrangThai trangThai;
    
}