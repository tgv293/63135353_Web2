package vn.gvt.QLTB.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.gvt.QLTB.Models.NguoiMuon;

public interface NguoiMuonRepository extends JpaRepository<NguoiMuon, String> {
    NguoiMuon findByMaNguoiMuon(String maNguoiMuon);
}