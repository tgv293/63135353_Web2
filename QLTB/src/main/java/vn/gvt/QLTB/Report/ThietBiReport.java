package vn.gvt.QLTB.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.gvt.QLTB.Models.ThietBi;


public class ThietBiReport {
    public List<Map<String, ?>>  dataPrint(List<ThietBi> data){
    	List<Map<String, ?>> listPM = new ArrayList<Map<String,?>>();
    	for (ThietBi pm : data) {
    		Map<String,Object> m = new HashMap<String, Object>();
			m.put("maTBi", pm.getMaThietBi());
			m.put("loaiTBi.tenLoai", pm.getLoaiThietBi().getTenLoai());
			m.put("tinhTrangTB",  pm.getTinhTrangTB());
			listPM.add(m);
		}
    	return listPM;
    
    }
	
}

