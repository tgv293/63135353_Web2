package vn.gvt.QLTB.Report;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.gvt.QLTB.Models.PhieuMuon;

public class PhieuMuonReport {
    public List<Map<String, ?>>  dataPrint(List<PhieuMuon> data){
    	List<Map<String, ?>> listPM = new ArrayList<Map<String,?>>();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
    	for (PhieuMuon pm : data) {
    		Map<String,Object> m = new HashMap<String, Object>();
			m.put("id", pm.getMaPhieuMuon());
			m.put("name", pm.getMaPhong().getMaPhong());
			m.put("lop", pm.getLop().getFullName());
		//	m.put("sinhvien", pm.getSvMuon().getTenSV());
			String tmp=pm.getThoiDiemMuon().format(formatter);
			m.put("timemuon",tmp);
			 tmp=pm.getHanTra().format(formatter);
			m.put("hantra", tmp);
			listPM.add(m);
		}
    	return listPM;
    
    }
	
}

