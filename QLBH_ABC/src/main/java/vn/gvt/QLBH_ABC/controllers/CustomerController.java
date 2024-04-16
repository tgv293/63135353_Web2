package vn.gvt.QLBH_ABC.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.gvt.QLBH_ABC.models.Customer;
import vn.gvt.QLBH_ABC.services.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired CustomerService customerService;
	@GetMapping("/all")
	public String getAll(Model model) {
		List<Customer> dsKH = customerService.findAllCustomer();
		model.addAttribute("dsKhachHang", dsKH);
		return "danhsachKH";
	}
}
