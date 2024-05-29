package vn.gvt.QLTB.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.gvt.QLTB.Models.NguoiMuon;
import vn.gvt.QLTB.Models.PhieuMuon;
import vn.gvt.QLTB.Models.ThietBi;
import vn.gvt.QLTB.Services.CTPhieuMuonService;
import vn.gvt.QLTB.Services.LoaiThietBiService;
import vn.gvt.QLTB.Services.LopService;
import vn.gvt.QLTB.Services.NguoiMuonService;
import vn.gvt.QLTB.Services.NhanVienService;
import vn.gvt.QLTB.Services.PhieuMuonService;
import vn.gvt.QLTB.Services.PhongService;
import vn.gvt.QLTB.Services.ThietBiService;

@Controller
public class HomeController {

	private PhieuMuon myPM = new PhieuMuon();
	private List<ThietBi> tbiMuon = new ArrayList<ThietBi>();
	private PhongService phongService;
	private LopService lopService;
	private LoaiThietBiService loaiThietBiService;
	private ThietBiService thietBiService;
	private NguoiMuonService nguoiMuonService;
	private CTPhieuMuonService ctpmService;
	private PhieuMuonService phieuMuonService;
	private NhanVienService nhanVienService;

	@Autowired
	public HomeController(PhongService phongService, LopService lopService, LoaiThietBiService loaiThietBiService,
			ThietBiService thietBiService, NguoiMuonService nguoiMuonService, CTPhieuMuonService ctpmService,
			PhieuMuonService phieuMuonService, NhanVienService nhanVienService) {
		this.phongService = phongService;
		this.lopService = lopService;
		this.loaiThietBiService = loaiThietBiService;
		this.thietBiService = thietBiService;
		this.nguoiMuonService = nguoiMuonService;
		this.ctpmService = ctpmService;
		this.phieuMuonService = phieuMuonService;
		this.nhanVienService = nhanVienService;
	}

	// Lấy thời gian hiện tại
	@ModelAttribute("timeNow")
	public String getnow() {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		String formatDateTime = time.format(formatter);
		return formatDateTime;
	}

	// Lấy thời gian mặc định cho trường hạn trả
	@ModelAttribute("timeDefault")
	public String getHanTra() {
		LocalDateTime time = LocalDateTime.now();
		if (time.getHour() < 12) {
			time = time.withHour(12).withMinute(0);
		} else if (time.getHour() < 17) {
			time = time.withHour(17).withMinute(0);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		String formatDateTime = time.format(formatter);
		return formatDateTime;
	}

	// Hiển thị trang chủ
	@GetMapping("/home")
	public String showphong(ModelMap model, HttpSession ss) {
		model.addAttribute("navigation", "home");
		// Toast
		model.addAttribute("action", ss.getAttribute("action"));
		model.addAttribute("typeToast", ss.getAttribute("typeToast"));
		ss.removeAttribute("action");
		ss.removeAttribute("typeToast");
		model.addAttribute("dsPhong", phongService.getPhong());
		model.addAttribute("listLop", lopService.getListLop());
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));
		model.addAttribute("dsTBChoPhepMuonCuaTatCaPhong", phongService.getDsTBChoPhepMuonCuaTatCaPhong());
		return "room";
	}

	// Hiển thị thông tin phòng
	@GetMapping(value = "/home/room/{id}", params = "linkPhong")
	public String Phong(ModelMap model, @PathVariable("id") String id, HttpSession ss) {
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));
		model.addAttribute("navigation", "home");
		model.addAttribute("dsPhong", phongService.getPhong());
		model.addAttribute("statusmodal", "open");
		model.addAttribute("listLop", lopService.getListLop());
		model.addAttribute("roomclicked", id);
		model.addAttribute("dsTBChoPhepMuonCuaTatCaPhong", phongService.getDsTBChoPhepMuonCuaTatCaPhong());
		ss.removeAttribute("action");
		ss.removeAttribute("typeToast");
		return "room";
	}

	// Lưu thông tin phiếu mượn
	@RequestMapping(value = "/home/room/{id}", params = "btnsave", method = RequestMethod.POST)
	public String savePM(@PathVariable("id") String id, HttpServletRequest sr, ModelMap model, HttpSession ss) {
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));
		model.addAttribute("navigation", "home");
		model.addAttribute("dsTBChoPhepMuonCuaTatCaPhong", phongService.getDsTBChoPhepMuonCuaTatCaPhong());
		PhieuMuon phieumuon = new PhieuMuon();
		phieumuon.setMaPhong(phongService.getPhongByID(id));
		phieumuon.setLop(lopService.getLopByID(sr.getParameter("lop")));
		phieumuon.setThoiDiemMuon(LocalDateTime.now());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		phieumuon.setHanTra(LocalDateTime.parse(sr.getParameter("hanTra").replace("T", " "), formatter));
		if (!ss.getAttribute("info").equals("Admin"))
			phieumuon.setNguoiLap(nhanVienService.getNhanVienByID(ss.getAttribute("infoManv").toString()));
		myPM = phieumuon;
		model.addAttribute("statusmodal2", "open");
		model.addAttribute("dsPhong", phongService.getPhong());
		model.addAttribute("thongtinPhieuMuon", phieumuon);
		model.addAttribute("listTypeTB", loaiThietBiService.getTypeTB(id));
		model.addAttribute("listTB", thietBiService.getListChoPhepMuon(id));

		return "room";
	}

	// Lưu thông tin phiếu mượn và chi tiết phiếu mượn
	@RequestMapping(value = "/home/room/{id}", params = "savebill", method = RequestMethod.POST)
	public String savePMAndCT(@PathVariable("id") String id, HttpServletRequest rq, ModelMap model, HttpSession ss) {
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));
		model.addAttribute("navigation", "home");
		model.addAttribute("dsTBChoPhepMuonCuaTatCaPhong", phongService.getDsTBChoPhepMuonCuaTatCaPhong());
		String maNguoiMuon = rq.getParameter("maNguoiMuon").toUpperCase();
		String tenNguoiMuon = rq.getParameter("tenNguoiMuon");
		String email = rq.getParameter("email");

		if (nguoiMuonService.getNMByID(maNguoiMuon) == null) {
			if (tenNguoiMuon == null || email == null) {
				model.addAttribute("dsPhong", phongService.getPhong());
				model.addAttribute("NMuon", maNguoiMuon);
				model.addAttribute("statusmodal3", "open");
				tbiMuon.clear();
				for (ThietBi tb : thietBiService.getListChoPhepMuon(id)) {
					String maThietBiAsString = tb.getMaThietBi().toString();
					if (rq.getParameter(maThietBiAsString) != null) {
						tbiMuon.add(tb);
					}
				}
				return "room";
			} else {
				NguoiMuon nm = new NguoiMuon(maNguoiMuon, tenNguoiMuon, email);
				nguoiMuonService.addNM(nm);
			}
		} else {
			tbiMuon.clear();
			for (ThietBi tb : thietBiService.getListChoPhepMuon(id)) {
				String maThietBiAsString = tb.getMaThietBi().toString();
				if (rq.getParameter(maThietBiAsString) != null) {
					tbiMuon.add(tb);
				}
			}
		}
		Integer tmp = 0;
		try {
			phieuMuonService.save(myPM);
			tmp = ctpmService.addPM_CT(myPM, tbiMuon, nguoiMuonService.getNMByID(maNguoiMuon));
		} catch (Exception e) {
		}
		if (tmp != 0) {
			model.addAttribute("typeToast", "success");
			model.addAttribute("action", "Mượn Thiết Bị");
		} else {
			model.addAttribute("typeToast", "error");
			model.addAttribute("action", "Mượn Thiết Bị");
		}
		model.addAttribute("dsPhong", phongService.getPhong());
		return "room";
	}

	// Hiển thị thông tin phiếu mượn
	@RequestMapping(value = "/home/bill{id}_{idPhong}", method = RequestMethod.GET)
	public String savePM(@PathVariable("id") Integer id, @PathVariable("idPhong") String idPhong, ModelMap model,
			HttpSession ss) {
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));
		model.addAttribute("navigation", "home");
		model.addAttribute("dsTBChoPhepMuonCuaTatCaPhong", phongService.getDsTBChoPhepMuonCuaTatCaPhong());
		model.addAttribute("statusmodal2", "open");
		model.addAttribute("dsPhong", phongService.getPhong());
		model.addAttribute("listNguoiMuon", ctpmService.getNguoiMuon_PM(id));
		model.addAttribute("thongtinPhieuMuon", phieuMuonService.getPhieuMuonByID(id));
		model.addAttribute("listTypeTB", loaiThietBiService.getTypeTB(idPhong));
		model.addAttribute("listTB", thietBiService.getListChoPhepMuon(idPhong));
		// Toast
		model.addAttribute("action", ss.getAttribute("action"));
		model.addAttribute("typeToast", ss.getAttribute("typeToast"));
		ss.removeAttribute("action");
		ss.removeAttribute("typeToast");
		return "room";
	}

	// Lưu thông tin phiếu mượn và chi tiết phiếu mượn
	@RequestMapping(value = "/home/bill{id}_{idPhong}", params = "savebill", method = RequestMethod.POST)
	public String test1(@PathVariable("id") Integer id, @PathVariable("idPhong") String idPhong, HttpServletRequest rq,
			ModelMap model, HttpSession ss) {
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));
		model.addAttribute("navigation", "home");
		model.addAttribute("dsTBChoPhepMuonCuaTatCaPhong", phongService.getDsTBChoPhepMuonCuaTatCaPhong());
		String maNguoiMuon = rq.getParameter("maNguoiMuon").toUpperCase();
		String tenNguoiMuon = rq.getParameter("tenNguoiMuon");
		String email = rq.getParameter("email");
		if (nguoiMuonService.getNMByID(maNguoiMuon) == null) {
			if (tenNguoiMuon == null || email == null) {
				model.addAttribute("dsPhong", phongService.getPhong());
				model.addAttribute("NMuon", maNguoiMuon);
				model.addAttribute("statusmodal3", "open");
				tbiMuon.clear();
				for (ThietBi tb : thietBiService.getListChoPhepMuon(idPhong)) {
					String maThietBiAsString = tb.getMaThietBi().toString();
					if (rq.getParameter(maThietBiAsString) != null) {
						tbiMuon.add(tb);
					}
				}
				return "room";
			} else {
				NguoiMuon nm = new NguoiMuon(maNguoiMuon, tenNguoiMuon, email);
				nguoiMuonService.addNM(nm);
			}
		} else {
			tbiMuon.clear();
			for (ThietBi tb : thietBiService.getListChoPhepMuon(idPhong)) {
				String maThietBiAsString = tb.getMaThietBi().toString();
				if (rq.getParameter(maThietBiAsString) != null) {
					tbiMuon.add(tb);
				}
			}
		}
		Integer tmp = 0;
		try {
			tmp = ctpmService.addCTPM(phieuMuonService.getPhieuMuonByID(id), tbiMuon,
					nguoiMuonService.getNMByID(maNguoiMuon));
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (tmp != 0) {
			model.addAttribute("typeToast", "success");
			model.addAttribute("action", "Mượn Thiết Bị");
		} else {
			model.addAttribute("typeToast", "error");
			model.addAttribute("action", "Mượn Thiết Bị");
		}
		model.addAttribute("dsPhong", phongService.getPhong());
		return "room";
	}

	// Chuyển hướng yêu cầu GET đến trang thanh toán
	@GetMapping(value = "/home/pay{idPM}_{idTB}_{idCTPM}_{idPhong}")
	public String redirectGetRequestPay(@PathVariable("idPM") Integer idPM, @PathVariable("idTB") Integer idTB,
			@PathVariable("idCTPM") Integer idCTPM, @PathVariable("idPhong") String idPhong) {
		return "redirect:/home/bill" + idPM + "_" + idPhong;
	}

	// Thực hiện thanh toán và trả thiết bị
	@PostMapping(value = "/home/pay{idPM}_{idTB}_{idCTPM}_{idPhong}")
	public String traPhieuMuon(@PathVariable("idPM") Integer idPM, @PathVariable("idTB") Integer idTB,
			@PathVariable("idCTPM") Integer idCTPM, @PathVariable("idPhong") String idPhong, ModelMap model,
			HttpSession ss) {

		ss.setAttribute("action", "Trả Thiết Bị");
		if (ctpmService.traTB(idPM, idTB, idCTPM) != 0) {
			ss.setAttribute("typeToast", "success");
		} else {
			ss.setAttribute("typeToast", "error");
		}
		return "redirect:/home/bill" + idPM + "_" + idPhong;
	}

	// Chuyển hướng yêu cầu GET đến trang báo mất thiết bị
	@GetMapping(value = "/home/loss{idPM}_{idTB}_{idPhong}")
	public String redirectGetRequestLoss(@PathVariable("idPM") Integer idPM, @PathVariable("idTB") Integer idTB,
			@PathVariable("idPhong") String idPhong) {
		return "redirect:/home/bill" + idPM + "_" + idPhong;
	}

	// Báo mất thiết bị
	@PostMapping(value = "/home/loss{idPM}_{idTB}_{idPhong}")
	public String baoMatTB(@PathVariable("idPM") Integer idPM, @PathVariable("idTB") Integer idTB, ModelMap model,
			@PathVariable("idPhong") String idPhong, HttpSession ss) {
		ss.setAttribute("action", "Báo Mất Thiết Bị");
		if (ctpmService.baoMatTB(idPM, idTB) != 0) {
			ss.setAttribute("typeToast", "success");
		} else {
			ss.setAttribute("typeToast", "error");
		}
		return "redirect:/home/bill{idPM}_{idPhong}";
	}
}