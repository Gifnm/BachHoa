package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.main.model.DeliveryNote;
import com.spring.main.model.ProductPositioning;
import com.spring.main.model.Store;

public interface DeliveryNoteJPA extends JpaRepository<DeliveryNote, String>{
//	@Query("SELECT o FROM DeliveryNote o WHERE o.store.storeID =:storeID ORDER BY o.timeCreate ASC")
//	List<DeliveryNote> getAllByStore(@Param("storeID") int storeID);
	
	@Query(value = "SELECT * FROM delivery_note WHERE storeID = ?1", nativeQuery = true)
	List<DeliveryNote> getAllByStore(int storeID);

}
