package vn.gvt.QLTB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.gvt.QLTB.Models.TrangThai;

public interface TrangThaiRepository extends JpaRepository<TrangThai, Integer> {
	TrangThai findByMaTinhTrang(Integer maTinhTrang);
}
