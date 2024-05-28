package vn.gvt.QLTB.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.gvt.QLTB.Models.PhieuMuon;
import vn.gvt.QLTB.Repositories.PhieuMuonRepository;

import java.util.List;

@Service
public class PhieuMuonServiceImpl implements PhieuMuonService {

    @Autowired
    private PhieuMuonRepository phieuMuonRepository;

    @Override
    public List<PhieuMuon> getLichSuMuon(Integer ID) {
        return phieuMuonRepository.findPhieuMuonByNguoiMuonIDAndThoiDiemTraIsNull(ID);
    }
    
    public PhieuMuon getPhieuMuonByID(Integer ID) {
        return phieuMuonRepository.findPhieuMuonByID(ID);
    }
    
    @Override
    public PhieuMuon save(PhieuMuon phieuMuon) {
        return phieuMuonRepository.save(phieuMuon);
    }
}