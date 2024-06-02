package vn.gvt.QLTB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.gvt.QLTB.Models.LoaiThietBi;

import java.util.List;
import java.util.Optional;

public interface LoaiThietBiRepository extends JpaRepository<LoaiThietBi, Integer> {
	@Query("select l from LoaiThietBi l where l.maLoai in (select distinct t.loaiThietBi.maLoai from ThietBi t where (t.phong.maPhong = :id or t.phong.maPhong = 'CSVC') and t.tinhTrangTB.maTinhTrang = 1)")
	List<LoaiThietBi> findDistinctByThietBiPhongID(@Param("id") String id);

	@Query("from LoaiThietBi where maLoai in (select distinct loaiThietBi.maLoai from ThietBi where phong is null)")
	List<LoaiThietBi> findDistinctByThietBiIsNull();
	
	Optional<LoaiThietBi> findByTenLoai(String tenLoai);

}
