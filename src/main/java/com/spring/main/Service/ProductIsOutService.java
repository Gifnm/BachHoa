package com.spring.main.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.jpa.ProductIsOutJPA;
import com.spring.main.model.ProductIsOut;
import com.spring.main.model.Store;

@Service
public class ProductIsOutService {
	@Autowired
	ProductIsOutJPA productIsOutJPA;
	private final String FOLDER_PATH = "C:\\bachhoaimg\\";

	public boolean insert(MultipartFile imaDate, MultipartFile imgQauntity, ProductIsOut productIsOut) {
		String filePath = FOLDER_PATH + imaDate.getOriginalFilename();
		try {
			imaDate.transferTo(new File(filePath));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		productIsOut.setImageDate("http://192.168.1.5:8083/bachhoaimg//" + imaDate.getOriginalFilename());
		String filePath2 = FOLDER_PATH + imgQauntity.getOriginalFilename();
		try {
			imgQauntity.transferTo(new File(filePath2));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		productIsOut.setImageQuantity("http://192.168.1.5:8083/bachhoaimg//" + imgQauntity.getOriginalFilename());
		productIsOutJPA.save(productIsOut);
		return true;
	}

	public void delete(ProductIsOut productIsOut) {
		productIsOutJPA.delete(productIsOut);
	}

	public List<ProductIsOut> getByStore(Store store) {
		List<ProductIsOut> list = productIsOutJPA.getByStore(store);
		return list;
	}
}
