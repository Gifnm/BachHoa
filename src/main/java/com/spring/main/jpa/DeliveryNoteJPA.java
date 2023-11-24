package com.spring.main.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.spring.main.model.DeliveryNote;
import com.spring.main.model.ProductPositioning;
import com.spring.main.model.Store;

public interface DeliveryNoteJPA extends JpaRepository<DeliveryNote, String> {
	// @Query("SELECT o FROM DeliveryNote o WHERE o.store.storeID =:storeID ORDER BY
	// o.timeCreate ASC")
	// List<DeliveryNote> getAllByStore(@Param("storeID") int storeID);
	@Query(value = "SELECT * FROM delivery_note WHERE storeID = ?1 and timeCompleted is null ORDER BY timeCreate ASC", nativeQuery = true)
	List<DeliveryNote> getAllByStore(int storeID);

	@Query("Update FROM DeliveryNote o set o.timeCompleted =:timeCompleted where o.id =:id")
	void setFinish(@Param("timeCompleted") Date timeCompleted, @Param("id") String id);

	@Query(value = "SELECT dn.id, dn.timeCreate, dn.timeCompleted, SUM(p.importPrice * ddn.quantity), dn.employeeID , e.employeeName, CASE "
			+
			"        WHEN SUM(ddn.count) = 0 THEN 'Chờ nhập' " +
			"        ELSE 'Đang kiểm' " +
			"    END AS status, COUNT(dn.id) "
			+
			"FROM employees e join delivery_note dn on e.employeeID = dn.employeeID " +
			"JOIN detailed_delivery_note ddn ON dn.id = ddn.id " +
			"JOIN products p ON ddn.productID = p.productID " +
			"WHERE dn.storeID = ?1 " +
			"GROUP BY dn.id " +
			"ORDER BY dn.timeCreate DESC", nativeQuery = true)
	List<Object[]> getDeliveryNotesWithTotalAmount(int storeID);

	@Query(value = "select dn.id, dn.timeCreate, dn.timeCompleted, SUM(ph.totalAmount), dn.employeeID , e.employeeName , 'Hoàn tất', COUNT(dn.id) "
			+
			"FROM employees e join delivery_note dn on e.employeeID = dn.employeeID " +
			"JOIN purchase_history ph on dn.id = ph.id " +
			"JOIN products p on ph.productID = p.productID " +
			"WHERE dn.storeID = ?1 " +
			"GROUP BY dn.id " +
			"ORDER BY dn.timeCreate DESC;", nativeQuery = true)
	List<Object[]> getDeliveryNotesWithPurchaseHistory(int storeID);
}
