package com.spring.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.protobuf.Timestamp;
import com.spring.main.jpa.InventoryHistoryJPA;
import com.spring.main.model.InventoryHistory;

@Service
public class InventoryHistoryService {
	// Tao doi tuong JPA tuong tac voi co so du lieu
	@Autowired
	private InventoryHistoryJPA inventoryHistoryJPA;

	/**
	 * Service Them moi lich su kiem ke - InventoryHistory
	 * 
	 * @param inventoryHistory
	 */
	public Boolean insert(InventoryHistory inventoryHistory) {
		try {
			inventoryHistoryJPA.save(inventoryHistory);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}

	}

	/**
	 * Service Lay danh sach lich su kiem ke theo san pham Tra ve List danh sach doi
	 * tuong InventoryHistory
	 * 
	 * @param productID - Ma san pham
	 * @param storeID   Ma cua hang
	 */
	public List<InventoryHistory> getByProductIDAndStoreID(String productID, int storeID) {
		try {
			List<InventoryHistory> list = inventoryHistoryJPA.getByProductIDAndstoreID(productID, storeID);
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	public List<InventoryHistory> getByStoreIDAndDate(int storeID, Timestamp date) {
		try {
			List<InventoryHistory> list = inventoryHistoryJPA.getByStoreIDAndDate(storeID, date);
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

}
