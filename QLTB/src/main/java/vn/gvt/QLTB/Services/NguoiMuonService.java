package vn.gvt.QLTB.Services;

import vn.gvt.QLTB.Models.NguoiMuon;

public interface NguoiMuonService {

    public NguoiMuon getNMByID(String maNM);

    public NguoiMuon addNM(NguoiMuon nm);
    
}
