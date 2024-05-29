package vn.gvt.QLTB.Models;

import java.util.Collection;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "LOAITHIETBI")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoaiThietBi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MALOAI")
	private Integer maLoai;

	@ToString.Exclude
	@OneToMany(mappedBy = "loaiThietBi", fetch = FetchType.EAGER)
	private Collection<ThietBi> dstb;

	@Size(max = 30)
	@NotBlank
	@Column(name = "TENLOAI")
	private String tenLoai;
}
