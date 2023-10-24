package com.spring.main.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.jpa.ProductJPA;
import com.spring.main.model.Product;
import com.spring.main.model.Store;

@Service
public class ProductService {
	@Autowired
	ProductJPA productJPA;
	/*
	 * Muc luc 
	 * 1. Tao san pham moi - (Hinh anh) 
	 * 2. Cap nhat - Tao moi san pham - khong kem hinh anh 
	 * 3. Tim kiem 1 san pham
	 * 4. Xoa san pham 
	 * 5. Thay doi trang thai kinh doanh (status)
	 */

// Duong dan thu muc dung de luu hinh anh tren may chu web
// Tao va thay doi duong dan
	private final String FOLDER_PATH = "C:\\bachhoaimg\\";

	/*
	 * 1. Tao san pham moi 
	 * - Tham so: File hinh anh, Product (object)
	 */
	public String uploadProduct(MultipartFile file, Product product) throws IllegalStateException, IOException {
		String filePath = FOLDER_PATH + file.getOriginalFilename();
		file.transferTo(new File(filePath));
		// Dia chi IP: Dia chi ip cuc bo may chu server
		// Cai dat ip tinh : 192.168.1.5
		product.setImage("http://192.168.1.5:8083/bachhoaimg//" + file.getOriginalFilename());
		productJPA.save(product);
		return "Succes";

	}

	/*
	 * 2. Luu san pham - Tao moi san pham 
	 * - Tham so: Product - Dung khi, can tao moi  san pham, cap nhat thong tin cua san pham
	 */
	private void save(Product product) {
		productJPA.save(product);

	}

	/*
	 * 3. Tim kiem 1 san pham 
	 * - Tham so: productID, storeID
	 */
	public Product getByIDAndStoreID(String ProductID, int storeID) {
		Product product = productJPA.getByIDAndStoreID(ProductID, storeID);
		return product;

	}

	/*
	 * 4. Xoa san pham 
	 * - Tham so: Product 
	 * - Dung khi: Can xoa san pham cua cua hang,
	 * can kiem tra san pham da ton tai trong cac bang khac trong co so du lieu hay
	 * chua neu san pham da ton tai, chi duoc chuyen trang thai san pham thanh false
	 */
	public void delete(Product product) {
		productJPA.delete(product);

	}

	/*
	 * 5. Thay doi trang thai kinh doanh (status) - Tham so: status (true/ false), storeID, productID - Dung khi:
	 */
	public void setStatus(boolean status, int storeID, String productID) {
		productJPA.setStatus(status, storeID, productID);
	}
	/*6. Lay danh sach san pham
	 * - Tham so: storeID
	 * */
	public List<Product> findByStore(Store store){
		return productJPA.findByStore(store);	
	}
}
