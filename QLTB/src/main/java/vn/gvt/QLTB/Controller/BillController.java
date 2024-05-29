package vn.gvt.QLTB.Controller;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import vn.gvt.QLTB.Models.PhieuMuon;
import vn.gvt.QLTB.Report.PhieuMuonReport;
import vn.gvt.QLTB.Services.CTPhieuMuonService;
import vn.gvt.QLTB.Services.PhieuMuonService;

import org.springframework.ui.ModelMap;

@Controller
public class BillController {
	
	private PhieuMuonService phieuMuonService;
	private CTPhieuMuonService ctPhieuMuonService;
	
	@Autowired
	public BillController(PhieuMuonService phieuMuonService, CTPhieuMuonService ctPhieuMuonService) {
		this.phieuMuonService = phieuMuonService;
		this.ctPhieuMuonService = ctPhieuMuonService;
	}
	
	private String radiomode="tatca";
	private String from=this.getNow();
	private String to=this.getNow();
	
	@ModelAttribute("timeNow")
	public String getNow() {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatDateTime = time.format(formatter);
		return formatDateTime;
	}
	
	@RequestMapping(value = "/bill", method = RequestMethod.GET)
	public String index(ModelMap model, HttpServletRequest request, HttpSession ss) {
		model.addAttribute("navigation", "bill");
		List<PhieuMuon> listCTThietBi = phieuMuonService.getAllPM(getNow(), getNow());
		PagedListHolder pagedListHolder = new PagedListHolder(listCTThietBi);
		model.addAttribute("pagedListHolder", pagedListHolder);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));

		model.addAttribute("timeFrom", getNow());
		model.addAttribute("timeTo", getNow());
		model.addAttribute("statusRd", "tatca");
		model.addAttribute("link", "/bill");
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(5);
		pagedListHolder.setMaxLinkedPages(4);
		String pagedLink = "/bill?p={page}";
		model.addAttribute("pagedLink", pagedLink);
		
		model.addAttribute("statusModal",ss.getAttribute("statusModal"));
		model.addAttribute("listNguoiMuon", ss.getAttribute("listNguoiMuon"));
		model.addAttribute("thongtinPhieuMuon", ss.getAttribute("thongtinPhieuMuon"));
		ss.removeAttribute("statusModal");
		ss.removeAttribute("listNguoiMuon");
		ss.removeAttribute("thongtinPhieuMuon");


		return "bill";
	}
	
	@RequestMapping(value = "/bill/detail_{id}")
	public String ct(ModelMap model, HttpServletRequest request, HttpSession ss,@PathVariable("id") Integer id) {
		List<PhieuMuon> listCTThietBi = phieuMuonService.getAllPM(getNow(), getNow());
		PagedListHolder pagedListHolder = new PagedListHolder(listCTThietBi);
		model.addAttribute("pagedListHolder", pagedListHolder);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		model.addAttribute("navigation", "bill");
		model.addAttribute("timeFrom", getNow());
		model.addAttribute("timeTo", getNow());
		model.addAttribute("statusRd", "tatca");
		model.addAttribute("link", "/bill/index.htm");
		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(5);
		pagedListHolder.setMaxLinkedPages(4);
		String pagedLink = "/bill?p={page}";
		model.addAttribute("pagedLink", pagedLink);
		ss.setAttribute("statusModal","open");
		ss.setAttribute("listNguoiMuon", ctPhieuMuonService.getNguoiMuon_PM(id));
		ss.setAttribute("thongtinPhieuMuon", phieuMuonService.getPhieuMuonByID(id));
		
		return "redirect:/bill";
	}
	
	@RequestMapping(value = "/bill", params = "btnfilter")
	public String filter(ModelMap model, HttpServletRequest request, HttpSession ss,
			@ModelAttribute("rdfilte") String radiobox, @ModelAttribute("from") String from,
			@ModelAttribute("to") String to) {
		model.addAttribute("navigation", "bill");
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));
		List<PhieuMuon> listCTThietBi = null;
		if (radiobox.equals("tatca")) {
			this.radiomode="tatca";
			listCTThietBi = phieuMuonService.getAllPM(from, to);
			this.from=from;
			this.to=to;
		} else {
			this.radiomode="quahan";
			listCTThietBi = phieuMuonService.getPMQuaHan();
		}
		model.addAttribute("timeFrom", from);
		model.addAttribute("link", "bill\\?" + request.getQueryString().replaceAll("p=..", "") + "\t");
		model.addAttribute("timeTo", to);
		model.addAttribute("statusRd", radiobox);
		PagedListHolder pagedListHolder = new PagedListHolder(listCTThietBi);
		model.addAttribute("pagedListHolder", pagedListHolder);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);

		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(10);
		pagedListHolder.setMaxLinkedPages(4);
		String pagedLink = "/bill?p={page}";
		model.addAttribute("pagedLink", pagedLink);

		return "bill";
	}

	
	@RequestMapping(value = "/bill", params = "print")
	public void generateReport(HttpServletRequest sq, HttpServletResponse response) {
		try {
			PhieuMuonReport pm = new PhieuMuonReport();
			List<Map<String, ?>> dataSource; // Change this line
			String reportPath;
			Map<String, Object> parameters = new HashMap<>();

			if (this.radiomode.equals("quahan")) {
				dataSource = pm.dataPrint(phieuMuonService.getPMQuaHan());
				reportPath = "/report/thongkemuonquahan.jrxml";
			} else {
				parameters.put("from", this.from);
				parameters.put("to", this.to);
				dataSource = pm.dataPrint(phieuMuonService.getAllPM(this.from, this.to));
				reportPath = "/report/thongkemuontheongay.jrxml";
			}

			JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
			InputStream input = new ClassPathResource(reportPath).getInputStream();
			JasperReport jasperReport = JasperCompileManager.compileReport(input);
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
