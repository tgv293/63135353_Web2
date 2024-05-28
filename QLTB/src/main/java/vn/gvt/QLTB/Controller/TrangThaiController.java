package vn.gvt.QLTB.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.gvt.QLTB.Models.TrangThai;
import vn.gvt.QLTB.Repositories.TrangThaiRepository;



@RestController
@RequestMapping("/trangthai")
public class TrangThaiController {
	
	@Autowired
	TrangThaiRepository trangThaiRepository;
	
	@GetMapping("")
	public ResponseEntity<List<TrangThai>> getAll()
	{
		return ResponseEntity.ok(trangThaiRepository.findAll());
	}
}
