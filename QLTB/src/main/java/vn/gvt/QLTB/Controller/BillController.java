package vn.gvt.QLTB.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import vn.gvt.QLTB.Models.PhieuMuon;

import org.springframework.ui.Model;

@Transactional
@Controller
@RequestMapping("/bill")
public class BillController {

	@ModelAttribute("timeNow")
	public String getNow() {
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatDateTime = time.format(formatter);
		return formatDateTime;
	}
	
//    @GetMapping("/index")
//    public String index(Model model, @RequestParam(defaultValue = "0") int p, HttpSession ss) {
//        ss.setAttribute("navigation", "bill");
//        Pageable pageable = PageRequest.of(p, 5);
//        Page<PhieuMuon> page = this.getAllPM(getNow(), getNow(), pageable);
//
//        model.addAttribute("timeFrom", getNow());
//        model.addAttribute("timeTo", getNow());
//        model.addAttribute("statusRd", "tatca");
//        model.addAttribute("link", "/bill/index.htm");
//        model.addAttribute("pagedListHolder", page);
//        
//        model.addAttribute("statusModal",ss.getAttribute("statusModal"));
//        model.addAttribute("listSVMuon", ss.getAttribute("listSVMuon"));
//        model.addAttribute("thongtinPhieuMuon", ss.getAttribute("thongtinPhieuMuon"));
//        ss.removeAttribute("statusModal");
//        ss.removeAttribute("listSVMuon");
//        ss.removeAttribute("thongtinPhieuMuon");
//
//        return "bill/index";
//    }

    // Your other methods...
}
