package vn.gvt.QLBH_ABC.services;

import java.util.List;
import org.springframework.stereotype.Service;

import vn.gvt.QLBH_ABC.models.Customer;

@Service
public interface CustomerService {
	List<Customer> findAllCustomer();

	Customer findCustomerByID(int id);

	void addCustomer(Customer customer);

	void deleteCustomer(int id);
}
