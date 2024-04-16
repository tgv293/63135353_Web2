package vn.gvt.QLBH_ABC.models;

import jakarta.persistence.*;

@Entity // đánh dấu rằng đây là một entity
@Table(name = "customer") // chỉ định tên của bảng trong cơ sở dữ liệu.
public class Customer { // Tên lớp ta viết hoa chữ cái đầu
	// ----cho cột id-----------------------------------------------------------
	@Id // đánh dấu trường id là khóa chính.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// xác định cách khóa chính được sinh ra (ở đây là tự động tăng).
	@Column(name = "id") // ánh xạ cột id của Bảng và thuộc tính id của Entiy
	private int id;

	// ----cho cột name-----------------------------------------------------------
	@Column(name = "name") // cột của Bảng customer
	private String name; // thuộc tính của Entity Customer
	// ----cho cột
	// address-----------------------------------------------------------
	@Column(name = "address")
	private String address;

	// Constructors, getters, and setters
	// Constructors
	public Customer() {
	}

	public Customer(String name, String address) {
		this.name = name;
		this.address = address;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// toString method (optional)
	@Override
	public String toString() {
		return "Customer{" + "id=" + id + ", name='" + name + '\'' + ", address='" + address + '\'' + '}';
	}
}