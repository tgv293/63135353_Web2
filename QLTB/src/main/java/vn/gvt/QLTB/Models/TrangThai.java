package vn.gvt.QLTB.Models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "TRANGTHAI")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrangThai {    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATINHTRANG")
    private int maTinhTrang;
    
    @Size(max = 20)
    @NotBlank
    @Column(name = "TINHTRANG")
    private String tinhTrang;
}