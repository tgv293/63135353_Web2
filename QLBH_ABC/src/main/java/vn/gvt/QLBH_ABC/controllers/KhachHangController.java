package vn.gvt.QLBH_ABC.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.gvt.QLBH_ABC.models.KhachHang;
import vn.gvt.QLBH_ABC.services.KhachHangService;

@RestController
public class KhachHangController {
	@Autowired KhachHangService khachHangService;
	@GetMapping("/dsKhachHang")
	public List<KhachHang> getAllKhachHang() {
		return khachHangService.getAllKhachHang();
	}
}
