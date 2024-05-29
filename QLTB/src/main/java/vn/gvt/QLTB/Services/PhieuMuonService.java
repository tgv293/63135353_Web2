package vn.gvt.QLTB.Services;

import java.util.List;

import vn.gvt.QLTB.Models.PhieuMuon;

public interface PhieuMuonService {

	public List<PhieuMuon> getLichSuMuon(Integer ID);
    
    public PhieuMuon getPhieuMuonByID(Integer ID);
    
    public PhieuMuon save(PhieuMuon phieuMuon);
    
    public List<PhieuMuon> getAllPM(String fromDate, String toDate);
    
    public List<PhieuMuon> getPMQuaHan();
}
