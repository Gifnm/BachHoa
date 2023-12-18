package com.spring.main.Service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.main.jpa.InventoryHistoryJPA;
import com.spring.main.model.InventoryHistory;

@Service
public class InventoryHistoryService {
@Autowired
InventoryHistoryJPA inventoryHistoryJPA;
// Tìm lịch sử kiêm rkee theo ngày

public Page<InventoryHistory> findByDate(Timestamp fromDate, Timestamp toDate, Integer storeID, Pageable page){
	return inventoryHistoryJPA.findByDate(fromDate, toDate, storeID, page);
}
}
