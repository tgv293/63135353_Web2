package vn.gvt.QLBH_ABC.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.gvt.QLBH_ABC.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	// Xây dựng thêm các phương thức khác
}
