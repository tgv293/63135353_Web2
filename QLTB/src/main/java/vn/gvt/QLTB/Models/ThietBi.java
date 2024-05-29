package vn.gvt.QLTB.Models;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "THIETBI")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThietBi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATHIETBI")
    private Integer maThietBi;
    
    @Size(max = 30)
    @NotBlank
    @Column(name = "TENTHIETBI")
    private String tenThietBi;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "LOAITHIETBI", referencedColumnName = "MALOAI")
    private LoaiThietBi loaiThietBi;
    
    @ManyToOne
    @JoinColumn(name = "MAPHONG", referencedColumnName = "MAPHONG")
    private Phong phong;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "TINHTRANGTB", referencedColumnName = "MATINHTRANG")
    private TrangThai tinhTrangTB;

    @ToString.Exclude
    @OneToMany(mappedBy = "thietBi", fetch = FetchType.EAGER)
    private Collection<CTPhieuMuon> listCTPhieuMuon;
    
    public ThietBi(ThietBi other) {
        this.maThietBi = other.maThietBi;
        this.tenThietBi = other.tenThietBi;
        this.loaiThietBi = other.loaiThietBi;
        this.phong = other.phong;
        this.tinhTrangTB = other.tinhTrangTB;
    }
}