package vn.gvt.QLTB.Controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import vn.gvt.QLTB.Models.Phong;
import vn.gvt.QLTB.Models.ThietBi;
import vn.gvt.QLTB.Services.LoaiThietBiService;
import vn.gvt.QLTB.Services.PhongService;
import vn.gvt.QLTB.Services.TrangThaiService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class RoomController {
	
	private PhongService phongService;
	private LoaiThietBiService loaiTBService;
	private TrangThaiService trangThaiService;
	
	@Autowired
	public RoomController(PhongService phongService, LoaiThietBiService loaiTBService,
			TrangThaiService trangThaiService) {
		this.phongService = phongService;
		this.loaiTBService = loaiTBService;
		this.trangThaiService = trangThaiService;
	}
	
	// Thêm phòng
	@RequestMapping("/room/add")
	public String addRoom(ModelMap model, @ModelAttribute("Phong") Phong phong, @RequestParam("idroom") String idroom, HttpSession ss ) {
		Phong room= new Phong();
		room.setMaPhong(idroom);
		room.setTrangThai(trangThaiService.findByMaTinhTrang(1));
		if (phongService.addRoom(room) != null) {
			ss.setAttribute("message","success");
			ss.setAttribute("action","Thêm Phòng");
		}
		else {
			ss.setAttribute("message","error");
			ss.setAttribute("action","Thêm Phòng");
		}
		model.addAttribute("listPhong", phongService.getPhong());
		model.addAttribute("listLoaiTB", loaiTBService.getAllLoaiTB());
		return "redirect:/device_detail";
	}
	
	// Gọi stored procedure bảo trì phòng
	@RequestMapping("/room/maintenance_id={id}")
	public String callMaintenanceProcedure(@PathVariable("id") String id, Model model, @ModelAttribute("Phong") Phong phong) {
		phongService.callSpBaoTriPhong(id);
		model.addAttribute("listPhong", phongService.getPhong());
		return "redirect:/device_detail";
	}
	
	// Gọi stored procedure hoàn tất bảo trì phòng
	@RequestMapping("/room/maintenance_id={id}_completed")
	public String completeMaintenanceProcedure(@PathVariable("id") String id, Model model, @ModelAttribute("Phong") Phong phong) {
		phongService.callSpHoanThanhBaoTriPhong(id);
		model.addAttribute("listPhong", phongService.getPhong());
		return "redirect:/device_detail";
	}
	
	// Xóa phòng
	@RequestMapping(value = "/room/delete_id={id}")
	public String deleteRoom(ModelMap model, @ModelAttribute("Phong") Phong phong, @PathVariable("id") String id, HttpSession ss) {
		int tmp = phongService.deletePhong(id);
		ss.setAttribute("action", "Xóa Phòng");
		if (tmp != 0) {
			ss.setAttribute("message", "success");
		} else {
			ss.setAttribute("message", "error");
		}
		model.addAttribute("listPhong", phongService.getPhong());
		return "redirect:/device_detail";
	}
	
	// Tạo báo cáo
	@GetMapping("/room/{id}/report")
	public void generateReport(@PathVariable("id") String id, HttpServletResponse response) {
		try {
			Phong phongFromService = phongService.getPhongByID(id);
			List<ThietBi> dataSource = new ArrayList<>(phongFromService.getDstb());
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
			InputStream input = new ClassPathResource("/report/thongkethietbitheophong.jrxml").getInputStream();
			JasperReport jasperReport = JasperCompileManager.compileReport(input);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("phong", id);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
			response.setContentType(MediaType.APPLICATION_PDF_VALUE);
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
